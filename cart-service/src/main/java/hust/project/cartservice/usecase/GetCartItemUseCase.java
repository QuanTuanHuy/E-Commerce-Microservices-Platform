package hust.project.cartservice.usecase;

import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.port.ICartItemPort;
import hust.project.cartservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCartItemUseCase {
    private final ICartItemPort cartItemPort;

    public List<CartItemEntity> getAllCartItems() {
        Long customerId = AuthenticationUtils.getCurrentUserId();
        return cartItemPort.getCartItemsByCustomerId(customerId);
    }
}
