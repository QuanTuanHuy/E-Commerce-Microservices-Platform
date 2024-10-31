package hust.project.cartservice.port;

import hust.project.cartservice.entity.CartItemEntity;

import java.util.List;

public interface ICartItemPort {
    List<CartItemEntity> saveAll(List<CartItemEntity> cartItemEntities);

    CartItemEntity getCartItemByCustomerIdAndProductId(Long customerId, Long productId);

    List<CartItemEntity> getCartItemsByCustomerId(Long customerId);

    List<CartItemEntity> getCartItemsByCustomerIdAndProductIds(Long customerId, List<Long> productIds);

    void deleteCartItemsByCustomerIdAndProductIds(Long customerId, List<Long> productIds);
}
