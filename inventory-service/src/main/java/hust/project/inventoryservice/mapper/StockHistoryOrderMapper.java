package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.StockHistoryOrderEntity;
import hust.project.inventoryservice.model.StockHistoryOrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class StockHistoryOrderMapper {
    public static final StockHistoryOrderMapper INSTANCE = Mappers.getMapper(StockHistoryOrderMapper.class);

    public abstract StockHistoryOrderEntity toEntityFromModel(StockHistoryOrderModel model);

    public abstract StockHistoryOrderModel toModelFromEntity(StockHistoryOrderEntity entity);
}
