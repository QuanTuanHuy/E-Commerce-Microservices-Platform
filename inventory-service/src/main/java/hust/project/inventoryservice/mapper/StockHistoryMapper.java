package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.model.StockHistoryModel;
import hust.project.inventoryservice.model.StockModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class StockHistoryMapper {
    public static final StockHistoryMapper INSTANCE = Mappers.getMapper(StockHistoryMapper.class);

    public abstract StockHistoryEntity toEntityFromModel(StockModel model);

    public abstract StockHistoryModel toModelFromEntity(StockHistoryEntity entity);
}
