package hust.project.cartservice.service;

import hust.project.cartservice.entity.dto.response.ProductThumbnailResponse;

import java.util.List;

public interface IProductService {
    List<ProductThumbnailResponse> getProductsByIds(List<Long> productIds);
}
