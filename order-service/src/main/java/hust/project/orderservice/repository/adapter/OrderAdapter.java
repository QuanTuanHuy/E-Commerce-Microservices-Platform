package hust.project.orderservice.repository.adapter;

import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.mapper.OrderMapper;
import hust.project.orderservice.model.OrderModel;
import hust.project.orderservice.port.IOrderPort;
import hust.project.orderservice.repository.IOrderRepository;
import hust.project.orderservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public Pair<PageInfo, List<OrderEntity>> getMyOrders(Long userId, GetMyOrderRequest filter) {
        if (!StringUtils.hasText(filter.getProductName())) {
            Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by("id").descending());
            Page<OrderModel> result;
            if (CollectionUtils.isEmpty(filter.getOrderStatuses())) {
                result = orderRepository.findByCustomerId(userId, pageable);
            } else {
                result = orderRepository.findByCustomerIdAndOrderStatusIn(userId, filter.getOrderStatuses(), pageable);
            }

            var pageInfo = PageInfoUtils.getPageInfo(result);

            return Pair.of(pageInfo, OrderMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
        }

        var result = orderRepository.getMyOrders(userId, filter);

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
    public List<OrderEntity> getExistedOrdersByCustomerIdAndProductIds(Long customerId, List<Long> productIds) {
        return OrderMapper.INSTANCE.toEntitiesFromModels(orderRepository
                .getExistedOrdersByCustomerIdAndProductIds(customerId, productIds));
    }
}
