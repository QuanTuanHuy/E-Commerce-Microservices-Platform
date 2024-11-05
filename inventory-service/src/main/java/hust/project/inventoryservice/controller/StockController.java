package hust.project.inventoryservice.controller;

import hust.project.inventoryservice.entity.dto.request.CreateStockRequest;
import hust.project.inventoryservice.entity.dto.request.GetStockRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;
import hust.project.inventoryservice.entity.dto.response.Resource;
import hust.project.inventoryservice.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final IStockService stockService;

    @PostMapping
    public ResponseEntity<Resource> createStocks(@RequestBody List<CreateStockRequest> requests) {
        return ResponseEntity.ok(new Resource(stockService.createStocks(requests)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllStocks(
            @RequestParam(name = "warehouse_id") Long warehouseId,
            @RequestParam(name = "product_name", required = false) String productName,
            @RequestParam(name = "product_slug", required = false) String productSlug
    ) {
        var filter = GetStockRequest.builder()
                .warehouseId(warehouseId)
                .productName(productName)
                .productSlug(productSlug)
                .build();

        return ResponseEntity.ok(new Resource(stockService.getAllStocks(filter)));
    }

    @PutMapping
    public ResponseEntity<Resource> updateProductQuantityInStocks(
            @RequestBody List<UpdateStockQuantityRequest> requests
    ) {
        return ResponseEntity.ok(new Resource(stockService.updateProductQuantityInStocks(requests)));
    }

}
