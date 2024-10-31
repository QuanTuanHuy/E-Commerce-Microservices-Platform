package hust.project.cartservice.repository.adapter;

import hust.project.cartservice.constants.ErrorCode;
import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.exception.AppException;
import hust.project.cartservice.mapper.CartItemMapper;
import hust.project.cartservice.model.CartItemModel;
import hust.project.cartservice.port.ICartItemPort;
import hust.project.cartservice.repository.ICartItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemAdapter implements ICartItemPort {
    private final ICartItemRepository cartItemRepository;


    @Override
    public List<CartItemEntity> saveAll(List<CartItemEntity> cartItemEntities) {
        try {
            List<CartItemModel> cartItemModels = CartItemMapper.getInstance().toModelsFromEntities(cartItemEntities);
            return CartItemMapper.getInstance().toEntitiesFromModels(cartItemRepository.saveAll(cartItemModels));
        } catch (Exception e) {
            log.error("[CartItemAdapter] save cart item failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_CART_ITEM_FAILED);
        }
    }

    @Override
    public CartItemEntity getCartItemByCustomerIdAndProductId(Long customerId, Long productId) {
        return CartItemMapper.getInstance().toEntityFromModel(cartItemRepository.findByCustomerIdAndProductId(customerId, productId)
                .orElseThrow(() -> {
                    log.error("[CartItemAdapter] cart item not found by customerId: {} and productId: {}", customerId, productId);
                    return new AppException(ErrorCode.GET_CART_ITEM_FAILED);
                }));
    }

    @Override
    public List<CartItemEntity> getCartItemsByCustomerId(Long customerId) {
        return CartItemMapper.getInstance().toEntitiesFromModels(
                cartItemRepository.findByCustomerIdOrderByCreatedAtDesc(customerId));
    }

    @Override
    public List<CartItemEntity> getCartItemsByCustomerIdAndProductIds(Long customerId, List<Long> productIds) {
        return CartItemMapper.getInstance().toEntitiesFromModels(
                cartItemRepository.findByCustomerIdAndProductIdIn(customerId, productIds));
    }

    @Override
    public void deleteCartItemsByCustomerIdAndProductIds(Long customerId, List<Long> productIds) {
        cartItemRepository.deleteByCustomerIdAndProductIdIn(customerId, productIds);
    }
}
