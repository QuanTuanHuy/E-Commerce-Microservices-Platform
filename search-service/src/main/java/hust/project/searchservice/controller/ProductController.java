package hust.project.searchservice.controller;

import hust.project.searchservice.entity.dto.request.GetProductRequest;
import hust.project.searchservice.entity.dto.response.Resource;
import hust.project.searchservice.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/elastic/products")
public class ProductController {
    private final IProductService productService;

//    @PostMapping
//    public ResponseEntity<Resource> createProduct(
//            @RequestBody CreateProductRequest request
//    ) {
//        return ResponseEntity.ok(new Resource(productService.createProduct(request)));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailProduct(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new Resource(productService.getDetailProduct(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "sort_type", defaultValue = "created_at") String sortType
    ) {
        var filter = GetProductRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .keyword(keyword)
                .brand(brand)
                .category(category)
                .minPrice(minPrice).maxPrice(maxPrice)
                .sortType(sortType)
                .build();
        return ResponseEntity.ok(new Resource(productService.getAllProducts(filter)));
    }

    @GetMapping("/auto_complete")
    public ResponseEntity<Resource> autoCompleteProductName(
            @RequestParam(name = "keyword") String keyword
    ) {
        return ResponseEntity.ok(new Resource(productService.autoCompleteProductName(keyword)));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Resource> updateProduct(
//            @PathVariable Long id,
//            @RequestBody UpdateProductRequest request
//    ) {
//        return ResponseEntity.ok(new Resource(productService.updateProduct(id, request)));
//    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<Resource> deleteProductById(
//            @PathVariable Long id
//    ) {
//        productService.deleteProduct(id);
//        return ResponseEntity.ok(new Resource(null));
//    }
}
