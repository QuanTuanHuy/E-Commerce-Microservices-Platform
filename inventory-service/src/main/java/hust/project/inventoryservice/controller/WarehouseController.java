package hust.project.inventoryservice.controller;

import hust.project.inventoryservice.entity.dto.request.CreateWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateWarehouseRequest;
import hust.project.inventoryservice.entity.dto.response.Resource;
import hust.project.inventoryservice.service.IWarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IWarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Resource> createWarehouse(@RequestBody @Valid CreateWarehouseRequest request) {
        return ResponseEntity.ok(new Resource(warehouseService.createWarehouse(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailWarehouse(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(warehouseService.getDetailWarehouse(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllWarehouses(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "name" ,required = false) String name,
            @RequestParam(name = "province_name" ,required = false) String provinceName,
            @RequestParam(name = "district_name" ,required = false) String districtName,
            @RequestParam(name = "ward_name", required = false) String wardName
    ) {
        var filter = GetWarehouseRequest.builder()
                .page(page).pageSize(pageSize)
                .name(name)
                .provinceName(provinceName)
                .districtName(districtName)
                .wardName(wardName)
                .build();

        return ResponseEntity.ok(new Resource(warehouseService.getAllWarehouses(filter)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateWarehouse(
            @PathVariable Long id,
            @RequestBody @Valid UpdateWarehouseRequest request
    ) {
        return ResponseEntity.ok(new Resource(warehouseService.updateWarehouse(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
