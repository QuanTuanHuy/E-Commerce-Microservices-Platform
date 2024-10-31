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

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteCartItemUseCase {
    private final ICartItemPort cartItemPort;

    public void deleteCartItems(DeleteCartItemRequest request) {
        Long customerId = AuthenticationUtils.getCurrentUserId();

        List<CartItemEntity> cartItems = cartItemPort.getCartItemsByCustomerIdAndProductIds(customerId, request.getProductIds());
        if (cartItems.size() != request.getProductIds().size()) {
            log.error("[DeleteCartItemUseCase] Cart items not found");
            throw new AppException(ErrorCode.DELETE_CART_ITEM_FAILED);
        }

        cartItemPort.deleteCartItemsByCustomerIdAndProductIds(customerId, request.getProductIds());
    }
}
