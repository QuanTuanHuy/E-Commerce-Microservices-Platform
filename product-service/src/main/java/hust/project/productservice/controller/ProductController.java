package hust.project.productservice.controller;

import hust.project.productservice.entity.dto.request.*;
import hust.project.productservice.entity.dto.response.Resource;
import hust.project.productservice.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<Resource> createProduct(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(new Resource(productService.createProduct(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailProduct(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(productService.getDetailProduct(id)));
    }

    @GetMapping("/by_ids")
    public ResponseEntity<Resource> getProductsByIds(
            @RequestParam(name = "ids") List<Long> ids
    ) {
        return ResponseEntity.ok(new Resource(productService.getProductsByIds(ids)));
    }

    @GetMapping("/variants")
    public ResponseEntity<Resource> getProductVariants(
            @RequestParam(name = "parent_id") Long parentId
    ) {
        return ResponseEntity.ok(new Resource(productService.getProductVariants(parentId)));
    }

    @GetMapping("/by_brand_id")
    public ResponseEntity<Resource> getProductsByBrandId(
            @RequestParam(name = "brand_ids") List<Long> brandIds
    ) {
        return ResponseEntity.ok(new Resource(productService.getProductByBrandIds(brandIds)));
    }

    @GetMapping("/by_category_id")
    public ResponseEntity<Resource> getProductsByCategoryId(
            @RequestParam(name = "category_ids") List<Long> categoryIds
    ) {
        return ResponseEntity.ok(new Resource(productService.getProductsByCategoryIds(categoryIds)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllProducts(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "product_name", required = false) String productName,
            @RequestParam(value = "product_slug", required = false) String productSlug,
            @RequestParam(value = "price_from", required = false) Double priceFrom,
            @RequestParam(value = "price_to", required = false) Double priceTo,
            @RequestParam(value = "brand_ids", required = false) List<Long> brandIds,
            @RequestParam(value = "category_ids", required = false) List<Long> categoryIds,
            @RequestParam(value = "is_published", defaultValue = "true") Boolean isPublished
            ) {
        var filter = GetProductRequest.builder()
                .page(page).pageSize(pageSize)
                .productSlug(productSlug).productName(productName)
                .priceFrom(priceFrom).priceTo(priceTo)
                .categoryIds(categoryIds).brandIds(brandIds)
                .isPublished(isPublished)
                .build();

        return ResponseEntity.ok(new Resource(productService.getAllProducts(filter)));
    }

    @GetMapping("/thumbnails")
    public ResponseEntity<Resource> getAllProductThumbnails(
            @RequestParam(name = "product_ids") List<Long> productIds,
            @RequestParam(name = "slug", required = false) String slug,
            @RequestParam(name = "name", required = false) String name
    ) {
        var filter = GetProductListRequest.builder()
                .productIds(productIds)
                .slug(slug).name(name)
                .build();

        return ResponseEntity.ok(new Resource(productService.getAllProductThumbnails(filter)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new Resource(null));
    }

    @PutMapping("/update_quantity")
    public ResponseEntity<Resource> updateProductQuantity(
            @RequestBody List<UpdateProductQuantityRequest> requests
    ) {
        productService.updateProductQuantity(requests);
        return ResponseEntity.ok(new Resource(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request
    ) {
        return ResponseEntity.ok(new Resource(productService.updateProduct(id, request)));
    }
}
