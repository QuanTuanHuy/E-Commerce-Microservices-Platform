package hust.project.addressservice.controller;

import hust.project.addressservice.entity.dto.request.CreateProvinceRequest;
import hust.project.addressservice.entity.dto.request.GetProvinceRequest;
import hust.project.addressservice.entity.dto.request.UpdateProvinceRequest;
import hust.project.addressservice.entity.dto.response.Resource;
import hust.project.addressservice.service.IProvinceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provinces")
public class ProvinceController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IProvinceService provinceService;

    @PostMapping
    public ResponseEntity<Resource> createProvince(@RequestBody @Valid CreateProvinceRequest request) {
        return ResponseEntity.ok(new Resource(provinceService.createProvince(request)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllProvinces(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "name", required = false) String name
    ) {
        var filter = GetProvinceRequest.builder()
                .page(page).pageSize(pageSize)
                .name(name)
                .build();
        return ResponseEntity.ok(new Resource(provinceService.getAllProvinces(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailProvince(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(provinceService.getDetailProvince(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateProvince(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProvinceRequest request
    ) {
        return ResponseEntity.ok(new Resource(provinceService.updateProvince(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteProvince(@PathVariable Long id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
