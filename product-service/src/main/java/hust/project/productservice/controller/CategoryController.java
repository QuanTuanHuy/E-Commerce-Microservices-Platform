package hust.project.productservice.controller;

import hust.project.productservice.entity.dto.request.*;
import hust.project.productservice.entity.dto.response.Resource;
import hust.project.productservice.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Resource> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(new Resource(categoryService.createCategory(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailCategory(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(categoryService.getDetailCategory(id)));
    }

    @GetMapping("/by_ids")
    public ResponseEntity<Resource> getCategoriesByIds(
            @RequestParam(name = "ids") List<Long> ids
    ) {
        return ResponseEntity.ok(new Resource(categoryService.getCategoriesByIds(ids)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllCategories(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(value = "slug", required = false) String slug,
            @RequestParam(value = "is_published", defaultValue = "true") Boolean isPublished,
            @RequestParam(value = "parent_id", required = false) Long parentId
    ) {
        var filter = GetCategoryRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .name(name)
                .slug(slug)
                .isPublished(isPublished)
                .parentId(parentId)
                .build();

        return ResponseEntity.ok(new Resource(categoryService.getAllCategories(filter)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryRequest request
    ) {
        return ResponseEntity.ok(new Resource(categoryService.updateCategory(id, request)));
    }
}
