package hust.project.cartservice.service.impl;

import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.entity.dto.request.CreateCartItemRequest;
import hust.project.cartservice.entity.dto.request.DeleteCartItemRequest;
import hust.project.cartservice.entity.dto.request.UpdateCartItemRequest;
import hust.project.cartservice.service.ICartItemService;
import hust.project.cartservice.usecase.CreateCartItemUseCase;
import hust.project.cartservice.usecase.DeleteCartItemUseCase;
import hust.project.cartservice.usecase.GetCartItemUseCase;
import hust.project.cartservice.usecase.UpdateCartItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {
    private final CreateCartItemUseCase createCartItemUseCase;
    private final GetCartItemUseCase getCartItemUseCase;
    private final UpdateCartItemUseCase updateCartItemUseCase;
    private final DeleteCartItemUseCase deleteCartItemUseCase;

    @Override
    public List<CartItemEntity> createCartItems(List<CreateCartItemRequest> requests) {
        return createCartItemUseCase.createCartItems(requests);
    }

    @Override
    public List<CartItemEntity> getAllCartItems() {
        return getCartItemUseCase.getAllCartItems();
    }

    @Override
    public List<CartItemEntity> updateCartItems(List<UpdateCartItemRequest> requests) {
        return updateCartItemUseCase.updateCartItems(requests);
    }

    @Override
    public void deleteCartItems(DeleteCartItemRequest request) {
        deleteCartItemUseCase.deleteCartItems(request);
    }
}
