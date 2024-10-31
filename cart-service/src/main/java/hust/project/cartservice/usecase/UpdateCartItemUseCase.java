package hust.project.cartservice.usecase;

import hust.project.cartservice.constants.ErrorCode;
import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.entity.dto.request.UpdateCartItemRequest;
import hust.project.cartservice.exception.AppException;
import hust.project.cartservice.port.ICartItemPort;
import hust.project.cartservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateCartItemUseCase {
    private final ICartItemPort cartItemPort;

    public List<CartItemEntity> updateCartItems(List<UpdateCartItemRequest> requests) {
        List<Long> productIds = requests.stream().map(UpdateCartItemRequest::getProductId).toList();

        Long customerId = AuthenticationUtils.getCurrentUserId();

        List<CartItemEntity> cartItems = cartItemPort.getCartItemsByCustomerIdAndProductIds(customerId, productIds);

        if (cartItems.size() != requests.size()) {
            log.error("[UpdateCartItemUseCase] Cart items not found");
            throw new AppException(ErrorCode.UPDATE_CART_ITEM_FAILED);
        }

        var mapProductIdToRequest = requests.stream()
                .collect(Collectors.toMap(UpdateCartItemRequest::getProductId, Function.identity()));


        cartItems.forEach(cartItem -> {
            UpdateCartItemRequest request = mapProductIdToRequest.get(cartItem.getProductId());
            cartItem.setQuantity(request.getQuantity());
        });

        return cartItemPort.saveAll(cartItems);
    }
}
