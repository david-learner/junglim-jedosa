package com.jedosa.junglim.order.service;

import com.jedosa.junglim.account.AccountRepository;
import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.article.domain.Pagination;
import com.jedosa.junglim.exception.NoAccountException;
import com.jedosa.junglim.order.domain.DeliveryInfo;
import com.jedosa.junglim.order.domain.DeliveryType;
import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.domain.OrderItem;
import com.jedosa.junglim.order.dto.*;
import com.jedosa.junglim.order.repository.*;
import com.jedosa.junglim.payment.domain.Payment;
import com.jedosa.junglim.payment.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemRepositoryCustom orderItemRepositoryCustom;
    private final OrderRepositoryCustom orderRepositoryCustom;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final JdbcTemplate jdbcTemplate;

    public OrderService(OrderItemRepository orderItemRepository, OrderItemRepositoryCustom orderItemRepositoryCustom, OrderRepositoryCustom orderRepositoryCustom, OrderRepository orderRepository, AccountRepository accountRepository, PaymentRepository paymentRepository, JdbcTemplate jdbcTemplate) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemRepositoryCustom = orderItemRepositoryCustom;
        this.orderRepositoryCustom = orderRepositoryCustom;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long saveOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemDto.toOrderItem();
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return savedOrderItem.getId();
    }

    public List<OrderItemDto> getCartItems(Long ordererId) {
        List<OrderItem> orderItems = orderItemRepository.
                findByOrdererIdAndIsDeletedAndIsInCart(ordererId, Boolean.FALSE, Boolean.TRUE);
        return orderItems.stream().map(OrderItemDto::new).collect(Collectors.toList());
    }

    // ????????? ??????????????? ????????? OrderItems??? ????????????
    public List<OrderItemDto> getOrderItems(Long orderId) {
        List<OrderItem> orderItems = orderItemRepositoryCustom.search(OrderItemSearchCondition.of(orderId));
        return orderItems.stream().map(OrderItemDto::new).collect(Collectors.toList());
    }

    public void deleteOrderItemsInCart(List<Long> ids) {
        List<OrderItem> orderItems = orderItemRepository.findAllById(ids);
        orderItems.forEach(OrderItem::deleted);
        orderItemRepository.saveAll(orderItems);
    }

    public BigDecimal getDeliveryFee() {
        String deliveryFee = jdbcTemplate.queryForObject("SELECT property_value FROM property WHERE property_name = 'delivery_fee'", String.class);
        return BigDecimal.valueOf(Long.parseLong(deliveryFee));
    }

    public Order generateOrder(OrderItemIdsDto orderItemIds, Long ordererId) {
        // ????????? ?????? ????????????
        Account orderer = accountRepository.findById(ordererId).orElseThrow(NoAccountException::new);
        //  Order??? orderer, orderItems ?????? ??????
        Order order = Order.of(orderer);
        // todo ????????? ?????? ???????????? ????????? ??????
        // ????????? save?????? orderId??? ???????????? orderItems??? orderId??? ????????? ??? ??????
//        Order savedOrder = orderRepository.save(order);
        List<OrderItem> orderItems = orderItemRepository.findAllById(orderItemIds.getOrderItemIds());

        order.addOrderItems(orderItems);
        return orderRepository.save(order);
//        savedOrder.addOrderItems(orderItems);
//        return orderRepository.save(savedOrder);
    }

    public Order updateOrder(OrderDto orderDto, Long orderId) {
        Payment savedPayment = paymentRepository.save(orderDto.toPayment());
        // getOrder
        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
        order(order);
        // ?????? ??? ?????? ????????? ??????????????? ????????? ??????
        return orderRepository.save(order.toUpdatedOrder(orderDto.toDeliveryInfo(), savedPayment));
    }

    // ?????? ????????? ??????????????? ??????
    private void order(Order order) {
        order.ordered();
        order.removeOrderItemsFromCart();
    }

    public Boolean isOrdered(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return order.isOrdered();
    }

    public ResponseRecentOrders getRecentOrders() {
        List<RecentOrder> orders = orderRepositoryCustom
                .findByLimit(5).stream().map(RecentOrder::new).collect(Collectors.toList());
        return new ResponseRecentOrders(orders);
    }

    // page??? order??? ?????? ???????????? ??????. ?????? ?????? ?????????
    public OrdersDto getOrdersWithPagination(OrderSearchCondition condition, Integer blockSize) {
        Integer page = condition.getPage();
        Pageable pageable = PageRequest.of(page, blockSize);
        Page<Order> ordersPage = orderRepositoryCustom.search(condition, pageable);
        List<Order> orders = ordersPage.getContent();
        Pagination pagination = new Pagination(ordersPage);

        List<ResponseAdminOrderListItemDto> orderDtos = new ArrayList<>();
        long topBoardArticleNumber = Pagination.calculateTopBoardArticleNumber(page, ordersPage.getTotalElements());
        for (int index = 1; index <= orders.size(); index++) {
            Order order = orders.get(index - 1);
            ResponseAdminOrderListItemDto orderDto = new ResponseAdminOrderListItemDto(order);
            orderDtos.add(orderDto);
            orderDto.setBoardItemSequence(topBoardArticleNumber);
            topBoardArticleNumber--;
        }

        return new OrdersDto(orderDtos, pagination);
    }

    public ResponseOrderDetailDto getOrderDetail(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        return new ResponseOrderDetailDto(order);
    }

    public ResponseOrderFormDto getOrderForm(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        return new ResponseOrderFormDto(order);
    }
}
