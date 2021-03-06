package com.jedosa.junglim.admin.order;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.infrastructure.annotation.Admin;
import com.jedosa.junglim.order.dto.OrdersDto;
import com.jedosa.junglim.order.dto.ResponseOrderDetailDto;
import com.jedosa.junglim.order.repository.OrderSearchCondition;
import com.jedosa.junglim.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderManagementController {

    private static final Logger log = LoggerFactory.getLogger(OrderManagementController.class);
    private OrderService orderService;

    public OrderManagementController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/orders")
    public String orderList(@RequestParam(required = false, defaultValue = "0") Integer page,
                                  @RequestParam(name = "block-size", required = false, defaultValue = "10")
                                          Integer blockSize,
                                  @Admin SessionAccountDto sessionAccountDto,
                                  Model model) {
        log.info("page: '{}', block size: '{}'", page, blockSize);
        OrderSearchCondition condition = new OrderSearchCondition(page);
        OrdersDto ordersDto = orderService.getOrdersWithPagination(condition, blockSize);
        model.addAttribute("ordersDto", ordersDto);
        model.addAttribute("pagination", ordersDto.getPagination());
        return "/admin/order/order-list";
    }

    /**
     * 관리자 주문관리에서 주문 상세정보를 보여준다
     *
     * @param id
     * @param sessionAccountDto
     * @param model
     * @return
     */
    @GetMapping("/admin/orders/{id}")
    public String orderDetail(@PathVariable Long id, @Admin SessionAccountDto sessionAccountDto,
                              Model model) {
        ResponseOrderDetailDto order = orderService.getOrderDetail(id);
        model.addAttribute("order", order);
        return "/admin/order/order-detail";
    }
}
