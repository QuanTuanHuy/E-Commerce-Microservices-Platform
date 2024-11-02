package hust.project.addressservice.controller;

import hust.project.addressservice.entity.dto.request.*;
import hust.project.addressservice.entity.dto.response.Resource;
import hust.project.addressservice.service.IDistrictService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/districts")
public class DistrictController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IDistrictService districtService;

    @PostMapping
    public ResponseEntity<Resource> createDistrict(@RequestBody @Valid CreateDistrictRequest request) {
        return ResponseEntity.ok(new Resource(districtService.createDistrict(request)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllDistricts(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "province_id", required = false) Long provinceId
    ) {
        var filter = GetDistrictRequest.builder()
                .page(page).pageSize(pageSize)
                .name(name)
                .provinceId(provinceId)
                .build();
        return ResponseEntity.ok(new Resource(districtService.getAllDistricts(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailDistrict(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(districtService.getDetailDistrict(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateDistrict(
            @PathVariable Long id,
            @RequestBody @Valid UpdateDistrictRequest request
    ) {
        return ResponseEntity.ok(new Resource(districtService.updateDistrict(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
