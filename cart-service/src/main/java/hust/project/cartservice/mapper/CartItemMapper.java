package hust.project.cartservice.mapper;

import hust.project.cartservice.entity.CartItemEntity;
import hust.project.cartservice.model.CartItemModel;


import java.util.ArrayList;
import java.util.List;

public class CartItemMapper {
    private static CartItemMapper INSTANCE;

    private CartItemMapper() {
    }

    public static CartItemMapper getInstance() {
        if ( INSTANCE == null ) {
            INSTANCE = new CartItemMapper();
        }
        return INSTANCE;
    }

    public CartItemEntity toEntityFromModel(CartItemModel model) {
        if ( model == null ) {
            return null;
        }

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCustomerId( model.getCustomerId() );
        cartItemEntity.setProductId( model.getProductId() );
        cartItemEntity.setQuantity( model.getQuantity() );

        return cartItemEntity;
    }

    public CartItemModel toModelFromEntity(CartItemEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setCustomerId( entity.getCustomerId() );
        cartItemModel.setProductId( entity.getProductId() );
        cartItemModel.setQuantity( entity.getQuantity() );

        return cartItemModel;
    }

    public List<CartItemEntity> toEntitiesFromModels(List<CartItemModel> models) {
        if ( models == null ) {
            return null;
        }

        List<CartItemEntity> list = new ArrayList<CartItemEntity>( models.size() );
        for ( CartItemModel cartItemModel : models ) {
            list.add( toEntityFromModel( cartItemModel ) );
        }

        return list;
    }

    public List<CartItemModel> toModelsFromEntities(List<CartItemEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CartItemModel> list = new ArrayList<CartItemModel>( entities.size() );
        for ( CartItemEntity cartItemEntity : entities ) {
            list.add( toModelFromEntity( cartItemEntity ) );
        }

        return list;
    }
}
