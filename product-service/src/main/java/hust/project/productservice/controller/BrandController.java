package hust.project.productservice.controller;

import hust.project.productservice.entity.dto.request.CreateBrandRequest;
import hust.project.productservice.entity.dto.request.GetBrandRequest;
import hust.project.productservice.entity.dto.request.UpdateBrandRequest;
import hust.project.productservice.entity.dto.response.Resource;
import hust.project.productservice.service.IBrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    private final IBrandService brandService;

    @PostMapping
    public ResponseEntity<Resource> createBrand(@Valid @RequestBody CreateBrandRequest request) {
        return ResponseEntity.ok(new Resource(brandService.createBrand(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailBrand(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(brandService.getDetailBrand(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllBrands(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(value = "slug", required = false) String slug,
            @RequestParam(value = "is_published", defaultValue = "true") Boolean isPublished
    ) {
        var filter = GetBrandRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .name(name)
                .slug(slug)
                .isPublished(isPublished)
                .build();

        return ResponseEntity.ok(new Resource(brandService.getAllBrands(filter)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBrandRequest request
    ) {
        return ResponseEntity.ok(new Resource(brandService.updateBrand(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
