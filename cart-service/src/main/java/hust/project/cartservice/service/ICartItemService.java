package hust.project.cartservice.service;

import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.entity.dto.request.CreateCartItemRequest;
import hust.project.cartservice.entity.dto.request.DeleteCartItemRequest;
import hust.project.cartservice.entity.dto.request.UpdateCartItemRequest;

import java.util.List;

public interface ICartItemService {
    List<CartItemEntity> createCartItems(List<CreateCartItemRequest> requests);

    List<CartItemEntity> getAllCartItems();

    List<CartItemEntity> updateCartItems(List<UpdateCartItemRequest> requests);

    void deleteCartItems(DeleteCartItemRequest request);
}
