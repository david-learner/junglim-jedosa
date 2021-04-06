package com.jedosa.junglim.payment.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.infrastructure.annotation.Login;
import com.jedosa.junglim.payment.dto.PaymentDto;
import com.jedosa.junglim.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class PaymentController {

    private static final Logger log =  LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //    @PostMapping("/payments")
//    public ResponseEntity<Void> generatePayment(@ModelAttribute PaymentDto paymentDto, @Login SessionAccountDto sessionAccountDto) {
//        paymentService.generatePayment(paymentDto);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/payments/complete")
    public String paymentComplete(@Login SessionAccountDto sessionAccountDto) {
        return "/payment/payment-complete";
    }

    // 결제되어야 할 금액과 실제 결제 금액을 비교하여 정상 결제인지 검증한다
    @GetMapping("payments/card-payment-validation")
    public ResponseEntity<Void> checkPaymentValidation(
            @RequestParam("impuid") String impUid,
            @RequestParam("orderuid") String orderUid,
            @RequestParam("applynum") String applyNum,
            @RequestParam("orderId") Long orderId,
            @Login SessionAccountDto sessionAccountDto) throws JsonProcessingException {
        log.info("아임포트 결제 고유번호::'{}'", impUid);
        log.info("카드사 승인번호::'{}'", applyNum);
        log.info("주문서 고유번호::'{}'", orderUid); // OrderId와 다름

        paymentService.isValidPayment(impUid, orderUid, applyNum, orderId);
        return ResponseEntity.ok().build();
    }
}
