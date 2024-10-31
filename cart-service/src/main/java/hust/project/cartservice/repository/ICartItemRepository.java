package hust.project.cartservice.repository;

import hust.project.cartservice.model.CartItemId;
import hust.project.cartservice.model.CartItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItemModel, CartItemId> {
    Optional<CartItemModel> findByCustomerIdAndProductId(Long customerId, Long productId);

    List<CartItemModel> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    List<CartItemModel> findByCustomerIdAndProductIdIn(Long customerId, List<Long> productIds);

    void deleteByCustomerIdAndProductId(Long customerId, Long productId);

    void deleteByCustomerIdAndProductIdIn(Long customerId, List<Long> productIds);
}
