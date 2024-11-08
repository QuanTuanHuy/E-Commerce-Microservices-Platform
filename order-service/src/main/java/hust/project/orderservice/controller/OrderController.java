package hust.project.orderservice.controller;

import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.response.Resource;
import hust.project.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
            @RequestParam(name = "created_from", required = false) Instant createdFrom,
            @RequestParam(name = "created_to", required = false) Instant createdTo,
            @RequestParam(name = "product_name", required = false) String productName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,
            @RequestParam(name = "province_name", required = false) String provinceName,
            @RequestParam(name = "district_name", required = false) String districtName,
            @RequestParam(name = "ward_name", required = false) String wardName
    ) {
        var filter = GetOrderRequest.builder()
                .page(page).pageSize(pageSize)
                .orderStatuses(orderStatuses)
                .createdFrom(createdFrom).createdTo(createdTo)
                .productName(productName)
                .email(email)
                .phoneNumber(phoneNumber)
                .provinceName(provinceName)
                .districtName(districtName)
                .wardName(wardName)
                .build();
        return ResponseEntity.ok(new Resource(orderService.getAllOrders(filter)));
    }
}
