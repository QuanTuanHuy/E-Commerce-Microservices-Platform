package hust.project.orderservice.mapper;

import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class OrderMapper {
    public static final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    public abstract OrderEntity toEntityFromModel(OrderModel model);

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "shippingAddress", ignore = true)
    public abstract OrderEntity toEntityFromRequest(CreateOrderRequest request);

    public abstract OrderModel toModelFromEntity(OrderEntity entity);

    public abstract List<OrderEntity> toEntitiesFromModels(List<OrderModel> models);
}
