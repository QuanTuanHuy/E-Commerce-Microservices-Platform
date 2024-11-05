package hust.project.inventoryservice.service.impl;

import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.entity.dto.request.GetStockHistoryRequest;
import hust.project.inventoryservice.entity.dto.response.PageInfo;
import hust.project.inventoryservice.service.IStockHistoryService;
import hust.project.inventoryservice.usecase.GetStockHistoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockHistoryService implements IStockHistoryService {
    private final GetStockHistoryUseCase getStockHistoryUseCase;

    @Override
    public Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter) {
        return getStockHistoryUseCase.getAllStockHistories(filter);
    }
}
