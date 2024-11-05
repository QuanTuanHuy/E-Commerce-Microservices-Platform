package hust.project.inventoryservice.service;

import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.entity.dto.request.GetStockHistoryRequest;
import hust.project.inventoryservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IStockHistoryService {
    Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter);
}
