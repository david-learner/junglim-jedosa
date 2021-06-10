package com.jedosa.junglim.order.web;

import com.jedosa.junglim.account.AccountService;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.exception.AlreadyOrderedException;
import com.jedosa.junglim.infrastructure.annotation.Login;
import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.dto.*;
import com.jedosa.junglim.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final AccountService accountService;

    public OrderController(OrderService orderService, AccountService accountService) {
        this.orderService = orderService;
        this.accountService = accountService;
    }

    @GetMapping("/cart")
    public String cart(Model model, @Login SessionAccountDto sessionAccountDto) {
        model.addAttribute("orderItems", orderService.getCartItems(sessionAccountDto.getId()));
        return "/order/cart";
    }

    // todo 카트로 넘어갈 때 데이터를 어떻게 받아와서 OrderItem을 만들어줄 것인가?
    // 너무 많이 고민하지 말자, 이 사이트는 제본집 도메인이다. 그러니 제본집에 특화된 객체와 DTO면  충분하다
    // 많은 것을 다뤄야 할수록 간단하게 맵핑할 것인가? 아니면 모든 케이스를 다 만들 것인가?

    /**
     * 상품 주문요청시 카트에 담길 OrderItem 생성
     */
    @PostMapping("/cart")
    public ResponseEntity<Void> addCart(@ModelAttribute OrderItemDto orderItemDto, Model model) {
        Long orderItemId = orderService.saveOrderItem(orderItemDto);
        model.addAttribute("orderItemId", orderItemId);
        return ResponseEntity.ok().build();
    }

    // Delete Method는 Ajax에서 Body를 사용할 수 없어서 삭제할 id들을 한 번에 보낼 수 없다
    // 따라서 Post Method를 사용하기로 결정
    @PostMapping("/cart/orderitems")
    public ResponseEntity<Void> deleteOrderItems(@RequestBody OrderItemIdsDto orderItemIds) {
        orderService.deleteOrderItemsInCart(orderItemIds.getOrderItemIds());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/orders/{id}")
    public String orderForm(@PathVariable Long id, Model model, @Login SessionAccountDto sessionAccountDto) {
        // todo 이미 결제된 주문 응답
        if(orderService.isOrdered(id)) {
            throw new AlreadyOrderedException();
        }

        // todo 본인 주문이 맞는지
        // todo 결제시 주문의 상태가 주문됨으로 변경되는지

        // 주문서 응답 DTO 조립
        model.addAttribute("orderDto", orderService.getOrderForm(id));
        return "/order/order";
    }

    @PostMapping("/orders")
    public ResponseEntity<Void> generateOrder(@RequestBody OrderItemIdsDto orderItemIds, @Login SessionAccountDto sessionAccountDto) {
        // generate Order With OrderItemIds and OrdererId
        Order order = orderService.generateOrder(orderItemIds, sessionAccountDto.getId());
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).build();
    }
    
    // 주문 내용 업데이트 (결제 정보, 배송 정보)
    @PatchMapping("/orders/{id}/order")
    public ResponseEntity<Void> updateOrder(@RequestBody OrderDto orderDto, @PathVariable Long id, @Login SessionAccountDto sessionAccountDto) {
        orderService.updateOrder(orderDto, id);
        return ResponseEntity.ok().build();
    }
}
