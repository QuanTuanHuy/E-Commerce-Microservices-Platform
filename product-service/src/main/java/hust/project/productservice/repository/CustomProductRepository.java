package hust.project.productservice.repository;

import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.model.ProductModel;
import org.springframework.data.util.Pair;

import java.util.List;

public interface CustomProductRepository {
    Pair<PageInfo, List<ProductModel>> getAllProducts(GetProductRequest filter);
}
