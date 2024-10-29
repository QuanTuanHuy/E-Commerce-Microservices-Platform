package hust.project.productservice.service;

import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.dto.request.CreateCategoryRequest;
import hust.project.productservice.entity.dto.request.GetCategoryRequest;
import hust.project.productservice.entity.dto.request.UpdateCategoryRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ICategoryService {
    CategoryEntity createCategory(CreateCategoryRequest request);

    CategoryEntity getDetailCategory(Long id);

    Pair<PageInfo, List<CategoryEntity>> getAllCategories(GetCategoryRequest filter);

    CategoryEntity updateCategory(Long id, UpdateCategoryRequest request);
}

