package hust.project.cartservice.usecase;

import hust.project.cartservice.constants.ErrorCode;
import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.entity.dto.request.DeleteCartItemRequest;
import hust.project.cartservice.exception.AppException;
import hust.project.cartservice.port.ICartItemPort;
import hust.project.cartservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteCartItemUseCase {
    private final ICartItemPort cartItemPort;

    public void adjustOrDeleteCartItems(List<DeleteCartItemRequest> requests) {
        Long customerId = AuthenticationUtils.getCurrentUserId();

        List<Long> productIds = requests.stream().map(DeleteCartItemRequest::getProductId).distinct().toList();
        if (productIds.size() != requests.size()) {
            log.error("[DeleteCartItemUseCase] Duplicate product id in request");
            throw new AppException(ErrorCode.DELETE_CART_ITEM_FAILED);
        }

        List<CartItemEntity> cartItems = cartItemPort.getCartItemsByCustomerIdAndProductIds(customerId, productIds);

        var mapIdToCartItem = cartItems.stream()
                .collect(Collectors.toMap(CartItemEntity::getProductId, Function.identity()));

        List<CartItemEntity> adjustedCartItems = new ArrayList<>();
        List<Long> deletedProductIds = new ArrayList<>();

        requests.forEach(request -> {
            CartItemEntity cartItem = mapIdToCartItem.getOrDefault(request.getProductId(), null);
            if (!ObjectUtils.isEmpty(cartItem)) {
                if (cartItem.getQuantity() > request.getQuantity()) {
                    cartItem.setQuantity(cartItem.getQuantity() - request.getQuantity());
                    adjustedCartItems.add(cartItem);
                } else {
                    deletedProductIds.add(request.getProductId());
                }
            }
        });

        if (!CollectionUtils.isEmpty(deletedProductIds)) {
            cartItemPort.deleteCartItemsByCustomerIdAndProductIds(customerId, deletedProductIds);
        }
        if (!CollectionUtils.isEmpty(adjustedCartItems)) {
            cartItemPort.saveAll(adjustedCartItems);
        }
    }
}
