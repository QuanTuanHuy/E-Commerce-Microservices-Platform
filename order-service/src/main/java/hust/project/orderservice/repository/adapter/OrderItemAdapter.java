package hust.project.orderservice.repository.adapter;

import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.entity.OrderItemEntity;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.mapper.OrderItemMapper;
import hust.project.orderservice.model.OrderItemModel;
import hust.project.orderservice.port.IOrderItemPort;
import hust.project.orderservice.repository.IOrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemAdapter implements IOrderItemPort {
    private final IOrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemEntity> saveAll(List<OrderItemEntity> orderItemEntities) {
        try {
            List<OrderItemModel> orderItemModels = OrderItemMapper.INSTANCE.toModelsFromEntities(orderItemEntities);
            return OrderItemMapper.INSTANCE.toEntitiesFromModels(orderItemRepository.saveAll(orderItemModels));
        } catch (Exception e) {
            log.error("[OrderItemAdapter] save orderItem failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_ITEM_FAILED);
        }
    }

    @Override
    public List<OrderItemEntity> getOrderItemsByOrderId(Long orderId) {
        return OrderItemMapper.INSTANCE.toEntitiesFromModels(orderItemRepository.findByOrderId(orderId));
    }

    @Override
    public List<OrderItemEntity> getOrderItemsByOrderIds(List<Long> orderIds) {
        return OrderItemMapper.INSTANCE.toEntitiesFromModels(orderItemRepository.findByOrderIdIn(orderIds));
    }

//    @Override
//    public void deleteOrderItemsByOrderId(Long orderId) {
//        try {
//            orderItemRepository.deleteByOrderId(orderId);
//        } catch (Exception e) {
//            log.error("[OrderItemAdapter] delete orderItem failed, err: {}", e.getMessage());
//            throw new AppException(ErrorCode.DELETE_ORDER_ITEM_FAILED);
//        }
//    }
}
