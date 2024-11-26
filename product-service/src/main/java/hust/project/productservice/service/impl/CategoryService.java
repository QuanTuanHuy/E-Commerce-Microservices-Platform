package hust.project.productservice.service.impl;

import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.dto.request.CreateCategoryRequest;
import hust.project.productservice.entity.dto.request.GetCategoryRequest;
import hust.project.productservice.entity.dto.request.UpdateCategoryRequest;
import hust.project.productservice.entity.dto.response.CategoryGetModel;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.service.ICategoryService;
import hust.project.productservice.usecase.CreateCategoryUseCase;
import hust.project.productservice.usecase.GetCategoryUseCase;
import hust.project.productservice.usecase.UpdateCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    @Override
    public CategoryEntity createCategory(CreateCategoryRequest request) {
        return createCategoryUseCase.createCategory(request);
    }

    @Override
    public CategoryEntity getDetailCategory(Long id) {
        return getCategoryUseCase.getDetailCategory(id);
    }

    @Override
    public Pair<PageInfo, List<CategoryEntity>> getAllCategories(GetCategoryRequest filter) {
        return getCategoryUseCase.getAllCategories(filter);
    }

    @Override
    public List<CategoryGetModel> getCategoriesByIds(List<Long> ids) {
        return getCategoryUseCase.getCategoriesByIds(ids);
    }

    @Override
    public CategoryEntity updateCategory(Long id, UpdateCategoryRequest request) {
        return updateCategoryUseCase.updateCategory(id, request);
    }

}
