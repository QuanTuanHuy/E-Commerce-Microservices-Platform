package hust.project.orderservice.mapper;

import hust.project.orderservice.entity.OrderItemEntity;
import hust.project.orderservice.entity.dto.request.CreateOrderItemRequest;
import hust.project.orderservice.model.OrderItemModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class OrderItemMapper {
    public static final OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    public abstract OrderItemEntity toEntityFromModel(OrderItemModel model);

    public abstract OrderItemEntity toEntityFromRequest(Long orderId, CreateOrderItemRequest request);

    public abstract OrderItemModel toModelFromEntity(OrderItemEntity entity);

    public abstract List<OrderItemModel> toModelsFromEntities(List<OrderItemEntity> entities);

    public abstract List<OrderItemEntity> toEntitiesFromModels(List<OrderItemModel> models);
}
