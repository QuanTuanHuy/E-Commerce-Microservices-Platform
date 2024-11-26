package hust.project.productservice.usecase;

import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.entity.dto.request.GetCategoryRequest;
import hust.project.productservice.entity.dto.response.CategoryGetModel;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.mapper.CategoryMapper;
import hust.project.productservice.port.ICategoryPort;
import hust.project.productservice.port.IImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCategoryUseCase {
    private final ICategoryPort categoryPort;
    private final IImagePort imagePort;

    public CategoryEntity getDetailCategory(Long id) {
        CategoryEntity category = categoryPort.getCategoryById(id);

        if (category.getImageId() != null) {
            category.setImage(imagePort.getImageById(category.getImageId()));
        }

        return category;
    }

    public Pair<PageInfo, List<CategoryEntity>> getAllCategories(GetCategoryRequest filter) {
        var result = categoryPort.getAllCategories(filter);

        List<Long> imageIds = result.getSecond().stream()
                .map(CategoryEntity::getImageId)
                .filter(Objects::nonNull)
                .toList();

        if (CollectionUtils.isEmpty(imageIds)) {
            return result;
        }


        List<ImageEntity> images = imagePort.getImagesByIds(imageIds);
        var mapIdToImage = images.stream()
                .collect(Collectors.toMap(ImageEntity::getId, Function.identity()));

        result.getSecond().forEach(category -> {
            if (category.getImageId() != null) {
                category.setImage(mapIdToImage.getOrDefault(category.getImageId(), null));
            }
        });

        return result;
    }

    public List<CategoryGetModel> getCategoriesByIds(List<Long> ids) {
        return categoryPort.getCategoriesByIds(ids).stream()
                .map(CategoryMapper.INSTANCE::toGetModelFromEntity)
                .toList();
    }
}
