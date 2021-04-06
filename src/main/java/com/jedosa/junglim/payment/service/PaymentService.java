package com.jedosa.junglim.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.repository.OrderRepository;
import com.jedosa.junglim.payment.repository.PaymentRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private RestTemplate restTemplate;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public void isValidPayment(String impUid, String orderUid, String applyNum, Long orderId) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String accessToken = getAccessToken();
        String paymentJsonResponse = getIamportPaymentResponse(accessToken, impUid);
        // 결제상태
        ObjectNode responseBody = (ObjectNode) om.readTree(paymentJsonResponse);
        ObjectNode responseObjectInJson = (ObjectNode) responseBody.get("response");
        String paymentStatus = responseObjectInJson.get("status").asText();
        if (!paymentStatus.equals("paid")) {
            throw new IllegalStateException("결제되지 않았습니다. 다시 시도해주세요.");
        }
        // 결제금액
        BigDecimal paidAmount = BigDecimal.valueOf(responseObjectInJson.get("amount").asLong());
        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
        if (!order.isValidAmount(paidAmount)) {
            throw new IllegalStateException("결제금액이 일치하지 않습니다. 다시 시도해주세요.");
        }
        ;
    }

    private String getIamportPaymentResponse(String accessToken, String impUid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        String urlForFindingPaymentHistoryByImpUid = "https://api.iamport.kr/payments/" + impUid;
        ResponseEntity<String> result =
                restTemplate.exchange(
                        urlForFindingPaymentHistoryByImpUid,
                        HttpMethod.GET, entity, String.class, (Object) null);
        return result.getBody();
    }

    private String getAccessToken() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("imp_key", "6162816316076518");
        formData.add("imp_secret", "DYmlXb7BnJSeUxA59FNLbTDVqqnE6vMKt85rkc3HWWWQuxVDcJonD1i3rFIybuiFDn7YAaGw6CnA9jel");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "https://api.iamport.kr/users/getToken", request, String.class);

        ObjectMapper om = new ObjectMapper();
        ObjectNode responseBody = (ObjectNode) om.readTree(response.getBody());
        ObjectNode responseObjectInJson = (ObjectNode) responseBody.get("response");
        return responseObjectInJson.get("access_token").asText();
    }
}
