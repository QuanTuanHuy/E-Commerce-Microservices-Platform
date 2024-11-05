package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.entity.dto.request.GetStockHistoryRequest;
import hust.project.inventoryservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IStockHistoryPort {
    void saveAll(List<StockHistoryEntity> stockHistoryEntities);

    Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter);
}
