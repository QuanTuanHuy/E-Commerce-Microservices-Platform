package hust.project.orderservice.repository.adapter;

import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.GetShippingAddressRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.mapper.ShippingAddressMapper;
import hust.project.orderservice.model.ShippingAddressModel;
import hust.project.orderservice.port.IShippingAddressPort;
import hust.project.orderservice.repository.IShippingAddressRepository;
import hust.project.orderservice.repository.specification.ShippingAddressSpecification;
import hust.project.orderservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingAddressAdapter implements IShippingAddressPort {
    private final IShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddressEntity save(ShippingAddressEntity entity) {
        try {
            ShippingAddressModel model = ShippingAddressMapper.INSTANCE.toModelFromEntity(entity);
            return ShippingAddressMapper.INSTANCE.toEntityFromModel(shippingAddressRepository.save(model));
        } catch (Exception e) {
            log.error("[ShippingAddressAdapter] save shipping address failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_SHIPPING_ADDRESS_FAILED);
        }
    }

    @Override
    public ShippingAddressEntity getShippingAddressById(Long id) {
        return ShippingAddressMapper.INSTANCE.toEntityFromModel(shippingAddressRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[ShippingAddressAdapter] get shipping address by id failed, id: {}", id);
                    return new AppException(ErrorCode.GET_SHIPPING_ADDRESS_FAILED);
                }
        ));
    }

    @Override
    public Pair<PageInfo, List<ShippingAddressEntity>> getAllShippingAddresses(GetShippingAddressRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());

        var result = shippingAddressRepository.findAll(
                ShippingAddressSpecification.getAllShippingAddresses(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, ShippingAddressMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public List<ShippingAddressEntity> getShippingAddressesByIds(List<Long> ids) {
        return ShippingAddressMapper.INSTANCE.toEntitiesFromModels(shippingAddressRepository.findByIdIn(ids));
    }

    @Override
    public void deleteShippingAddress(Long id) {
        try {
            shippingAddressRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[ShippingAddressAdapter] delete shipping address failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_SHIPPING_ADDRESS_FAILED);
        }
    }
}
