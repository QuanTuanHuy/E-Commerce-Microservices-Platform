package hust.project.cartservice.usecase;

import hust.project.cartservice.constants.ErrorCode;
import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.entity.dto.request.CreateCartItemRequest;
import hust.project.cartservice.entity.dto.response.ProductThumbnailResponse;
import hust.project.cartservice.exception.AppException;
import hust.project.cartservice.port.ICartItemPort;
import hust.project.cartservice.service.IProductService;
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
public class CreateCartItemUseCase {
    private final ICartItemPort cartItemPort;
    private final IProductService productService;

    public List<CartItemEntity> createCartItems(List<CreateCartItemRequest> requests) {
        // check cart item is not exist
        List<Long> productIds = requests.stream().map(CreateCartItemRequest::getProductId).toList();
        List<ProductThumbnailResponse> products = productService.getProductsByIds(productIds);

        if (products.size() != productIds.size()) {
            log.error("[CreateCartItemUseCase] product not found");
            throw new AppException(ErrorCode.CREATE_CART_ITEM_FAILED);
        }

        // check all product is available
        Long customerId = AuthenticationUtils.getCurrentUserId();
        log.info("[CreateCartItemUseCase] customerId: {}", customerId);
        List<CartItemEntity> existCartItems = cartItemPort.getCartItemsByCustomerIdAndProductIds(customerId, productIds);
        if (!existCartItems.isEmpty()) {
            log.error("[CreateCartItemUseCase] cart item is exist");
            throw new AppException(ErrorCode.CREATE_CART_ITEM_FAILED);
        }

        // create cart items
        List<CartItemEntity> cartItems = requests.stream()
                .map(request -> CartItemEntity.builder()
                        .customerId(customerId)
                        .productId(request.getProductId())
                        .quantity(request.getQuantity())
                        .build())
                .toList();


        return cartItemPort.saveAll(cartItems);
    }
}
