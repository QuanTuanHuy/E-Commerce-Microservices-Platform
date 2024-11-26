package hust.project.orderservice.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.request.UpdateOrderStatusRequest;
import hust.project.orderservice.entity.dto.response.Resource;
import hust.project.orderservice.service.IOrderService;
import hust.project.orderservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<Resource> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(new Resource(orderService.createOrder(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailOrder(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(orderService.getDetailOrder(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllOrders(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "order_statuses", required = false) List<String> orderStatuses,
            @RequestParam(name = "created_from", required = false) @JsonFormat(pattern = "yyyy-MM-dd") Date createdFrom,
            @RequestParam(name = "created_to", required = false) @JsonFormat(pattern = "yyyy-MM-dd") Date createdTo,
            @RequestParam(name = "product_name", required = false) String productName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,
            @RequestParam(name = "province_name", required = false) String provinceName,
            @RequestParam(name = "district_name", required = false) String districtName,
            @RequestParam(name = "ward_name", required = false) String wardName
    ) {
        Instant createdFromInstant = createdFrom != null ? createdFrom.toInstant() : null;
        Instant createdToInstant = createdTo != null ? createdTo.toInstant() : null;

        var filter = GetOrderRequest.builder()
                .page(page).pageSize(pageSize)
                .orderStatuses(orderStatuses)
                .createdFrom(createdFromInstant).createdTo(createdToInstant)
                .productName(productName)
                .email(email)
                .phoneNumber(phoneNumber)
                .provinceName(provinceName)
                .districtName(districtName)
                .wardName(wardName)
                .build();
        return ResponseEntity.ok(new Resource(orderService.getAllOrders(filter)));
    }

    @GetMapping("/my_orders")
    public ResponseEntity<Resource> getMyOrders(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "order_statuses", required = false) List<String> orderStatuses,
            @RequestParam(name = "product_name", required = false) String productName
    ) {
        var filter = GetMyOrderRequest.builder()
                .page(page).pageSize(pageSize)
                .orderStatuses(orderStatuses)
                .productName(productName)
                .build();
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseEntity.ok(new Resource(orderService.getMyOrders(userId, filter)));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Resource> updateOrderStatusOps(
            @PathVariable Long id,
            @RequestBody UpdateOrderStatusRequest request
    ) {
        return ResponseEntity.ok(new Resource(orderService.updateOrderStatus(id, request)));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Resource> cancelOrderWeb(@PathVariable Long id) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        orderService.cancelOrderWeb(id, userId);
        return ResponseEntity.ok(new Resource(null));
    }
}
