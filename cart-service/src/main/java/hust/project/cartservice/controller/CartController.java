package hust.project.cartservice.controller;

import hust.project.cartservice.entity.dto.request.CreateCartItemRequest;
import hust.project.cartservice.entity.dto.request.DeleteCartItemRequest;
import hust.project.cartservice.entity.dto.request.UpdateCartItemRequest;
import hust.project.cartservice.entity.dto.response.Resource;
import hust.project.cartservice.service.ICartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart_items")
public class CartController {
    private final ICartItemService cartItemService;

    @PostMapping
    public ResponseEntity<Resource> createCartItems(
            @RequestBody List<CreateCartItemRequest> requests
    ) {
        return ResponseEntity.ok(new Resource(cartItemService.createCartItems(requests)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllCartItems() {
        return ResponseEntity.ok(new Resource(cartItemService.getAllCartItems()));
    }

    @PutMapping
    public ResponseEntity<Resource> updateCartItems(
            @RequestBody List<UpdateCartItemRequest> requests
    ) {
        return ResponseEntity.ok(new Resource(cartItemService.updateCartItems(requests)));
    }

    @DeleteMapping
    public ResponseEntity<Resource> deleteCartItems(
            @RequestParam(name = "product_ids") List<Long> productIds
    ) {
        cartItemService.deleteCartItems(DeleteCartItemRequest.builder()
                        .productIds(productIds)
                .build());
        return ResponseEntity.ok(new Resource(null));
    }
}