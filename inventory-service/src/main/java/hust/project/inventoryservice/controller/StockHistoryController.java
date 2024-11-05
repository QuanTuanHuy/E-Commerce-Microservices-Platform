package hust.project.inventoryservice.controller;

import hust.project.inventoryservice.entity.dto.request.GetStockHistoryRequest;
import hust.project.inventoryservice.entity.dto.response.Resource;
import hust.project.inventoryservice.service.IStockHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock_histories")
public class StockHistoryController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IStockHistoryService stockHistoryService;

    @GetMapping
    public ResponseEntity<Resource> getAllStockHistories(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "warehouse_id", required = false) Long warehouseId,
            @RequestParam(name = "product_id", required = false) Long productId
    ) {
        var filter = GetStockHistoryRequest.builder()
                .page(page).pageSize(pageSize)
                .productId(productId)
                .warehouseId(warehouseId)
                .build();

        return ResponseEntity.ok(new Resource(stockHistoryService.getAllStockHistories(filter)));
    }

}
