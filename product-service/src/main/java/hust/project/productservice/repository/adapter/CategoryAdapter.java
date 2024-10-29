package hust.project.productservice.repository.adapter;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.dto.request.GetCategoryRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.CategoryMapper;
import hust.project.productservice.model.CategoryModel;
import hust.project.productservice.port.ICategoryPort;
import hust.project.productservice.repository.ICategoryRepository;
import hust.project.productservice.repository.specification.CategorySpecification;
import hust.project.productservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryAdapter implements ICategoryPort {
    private final ICategoryRepository categoryRepository;

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        try {
            CategoryModel categoryModel = CategoryMapper.INSTANCE.toModelFromEntity(categoryEntity);
            return CategoryMapper.INSTANCE.toEntityFromModel(categoryRepository.save(categoryModel));
        } catch (Exception e) {
            log.error("[CategoryAdapter] save category failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_CATEGORY_FAILED);
        }
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return CategoryMapper.INSTANCE.toEntityFromModel(categoryRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[CategoryAdapter] get category failed, id: {}", id);
                    return new AppException(ErrorCode.GET_CATEGORY_FAILED);
                }
        ));
    }

    @Override
    public List<CategoryEntity> getCategoriesByIds(List<Long> ids) {
        return CategoryMapper.INSTANCE.toEntitiesFromModels(categoryRepository.findByIdIn(ids));
    }

    @Override
    public Pair<PageInfo, List<CategoryEntity>> getAllCategories(GetCategoryRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("id").descending());

        var result = categoryRepository.findAll(CategorySpecification.getAllCategories(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, CategoryMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[CategoryAdapter] delete category failed, id: {}", id);
            throw new AppException(ErrorCode.DELETE_CATEGORY_FAILED);
        }
    }
}
