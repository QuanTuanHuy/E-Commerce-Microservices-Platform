package hust.project.orderservice.repository.adapter;

import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.mapper.OrderMapper;
import hust.project.orderservice.model.OrderModel;
import hust.project.orderservice.port.IOrderPort;
import hust.project.orderservice.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderAdapter implements IOrderPort {
    private final IOrderRepository orderRepository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        try {
            OrderModel orderModel = OrderMapper.INSTANCE.toModelFromEntity(orderEntity);
            return OrderMapper.INSTANCE.toEntityFromModel(orderRepository.save(orderModel));
        } catch (Exception e) {
            log.error("[OrderAdapter] save order failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter) {
        var result = orderRepository.getAllOrders(filter);

        return Pair.of(result.getFirst(), OrderMapper.INSTANCE.toEntitiesFromModels(result.getSecond()));
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return OrderMapper.INSTANCE.toEntityFromModel(orderRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[OrderAdapter] get order failed, order id: {}", id);
                    return new AppException(ErrorCode.GET_ORDER_FAILED);
                }
        ));
    }

    @Override
    public void deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[OrderAdapter] delete order failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_ORDER_FAILED);
        }
    }
}
