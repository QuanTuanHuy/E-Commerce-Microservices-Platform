package hust.project.promotionservice.controller;

import hust.project.promotionservice.entity.dto.request.*;
import hust.project.promotionservice.entity.dto.response.Resource;
import hust.project.promotionservice.service.IPromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotions")
@Slf4j
public class PromotionController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IPromotionService promotionService;

    @PostMapping
    public ResponseEntity<Resource> createPromotion(
            @RequestBody CreatePromotionRequest request
    ) {
        return ResponseEntity.ok(new Resource(promotionService.createPromotion(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailPromotion(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(promotionService.getDetailPromotion(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllPromotion(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "coupon_code", required = false) String couponCode,
            @RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        log.info("startDate: {}", startDate);
        log.info("endDate: {}", endDate);
        var filter = GetPromotionRequest.builder()
                .page(page).pageSize(pageSize)
                .name(name).couponCode(couponCode)
                .startDate(startDate.toInstant()).endDate(endDate.toInstant())
                .build();

        return ResponseEntity.ok(new Resource(promotionService.getAllPromotions(filter)));
    }


    @PostMapping("/verify")
    public ResponseEntity<Resource> verifyPromotion(
            @RequestBody VerifyPromotionRequest request
    ) {
        return ResponseEntity.ok(new Resource(promotionService.verifyPromotion(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updatePromotion(
            @PathVariable Long id,
            @RequestBody UpdatePromotionRequest request
    ) {
        return ResponseEntity.ok(new Resource(promotionService.updatePromotion(id, request)));
    }

    @PutMapping("/{id}/usage")
    public ResponseEntity<Resource> updatePromotionUsage(
            @PathVariable Long id,
            @RequestBody UpdatePromotionUsageRequest request
    ) {
        promotionService.updatePromotionUsage(id, request);
        return ResponseEntity.ok(new Resource(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
