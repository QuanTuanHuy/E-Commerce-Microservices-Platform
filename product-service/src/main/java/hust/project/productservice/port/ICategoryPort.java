package hust.project.productservice.port;

import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.dto.request.GetCategoryRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ICategoryPort {
    CategoryEntity save(CategoryEntity categoryEntity);

    CategoryEntity getCategoryById(Long id);

    List<CategoryEntity> getCategoriesByIds(List<Long> ids);

    Pair<PageInfo, List<CategoryEntity>> getAllCategories(GetCategoryRequest filter);

    void deleteCategory(Long id);
}
