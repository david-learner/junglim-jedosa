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

    // 주문서 상품목록에 노출될 OrderItems를 불러온다
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
        // 주문자 정보 가져오기
        Account orderer = accountRepository.findById(ordererId).orElseThrow(NoAccountException::new);
        //  Order에 orderer, orderItems 정보 추가
        Order order = Order.of(orderer);
        // todo 주문서 정보 업데이트 되는지 확인
        // 여기서 save해야 orderId가 생성되어 orderItems에 orderId를 전달할 수 있다
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
        // 배송 및 결제 정보가 업데이트된 주문서 저장
        return orderRepository.save(order.toUpdatedOrder(orderDto.toDeliveryInfo(), savedPayment));
    }

    // 주문 상태를 주문됨으로 변경
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

    // page랑 order랑 같이 가져와야 한다. 담을 객체 만들기
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
