package hust.project.ratingservice.controller;

import hust.project.ratingservice.entity.dto.request.CreateRatingRequest;
import hust.project.ratingservice.entity.dto.request.GetRatingRequest;
import hust.project.ratingservice.entity.dto.response.Resource;
import hust.project.ratingservice.service.IRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
@RequiredArgsConstructor
public class RatingController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IRatingService ratingService;

    @PostMapping
    public ResponseEntity<Resource> createRating(@RequestBody CreateRatingRequest request) {
        return ResponseEntity.ok(new Resource(ratingService.createRating(request)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllRatings(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "product_ids") List<Long> productId,
            @RequestParam(name = "user_id", required = false) Long customerId,
            @RequestParam(name = "created_from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdFrom,
            @RequestParam(name = "created_to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdTo
    ) {
        var filter = GetRatingRequest.builder()
                .page(page).pageSize(pageSize).productIds(productId).customerId(customerId)
                .createdFrom(createdFrom != null ? createdFrom.toInstant() : null)
                .createdTo(createdTo != null ? createdTo.toInstant() : null)
                .build();

        return ResponseEntity.ok(new Resource(ratingService.getAllRatings(filter)));
    }

    @GetMapping("/average")
    public ResponseEntity<Resource> getAverageRatingStar(
            @RequestParam(name = "product_ids") List<Long> productIds
    ) {
        return ResponseEntity.ok(new Resource(ratingService.getAverageRatingStar(productIds)));
    }
}
