package hust.project.productservice.repository.adapter;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.ImageMapper;
import hust.project.productservice.model.ImageModel;
import hust.project.productservice.port.IImagePort;
import hust.project.productservice.repository.IImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageAdapter implements IImagePort {
    private final IImageRepository imageRepository;

    @Override
    public ImageEntity save(ImageEntity imageEntity) {
        try {
            ImageModel imageModel = ImageMapper.INSTANCE.toModelFromEntity(imageEntity);
            return ImageMapper.INSTANCE.toEntityFromModel(imageRepository.save(imageModel));
        } catch (Exception e) {
            log.error("[ImageAdapter] save image failed: {}", e.getMessage());
            throw new AppException(ErrorCode.SAVE_IMAGE_FAILED);
        }
    }

    @Override
    public List<ImageEntity> saveAll(List<ImageEntity> imageEntities) {
        try {
            List<ImageModel> imageModels = ImageMapper.INSTANCE.toModelsFromEntities(imageEntities);
            return ImageMapper.INSTANCE.toEntitiesFromModels(imageRepository.saveAll(imageModels));
        } catch (Exception e) {
            log.error("[ImageAdapter] save all images failed: {}", e.getMessage());
            throw new AppException(ErrorCode.SAVE_IMAGE_FAILED);
        }
    }

    @Override
    public List<ImageEntity> getImageByEntityIdAndEntityType(Long entityId, String entityType) {
        return ImageMapper.INSTANCE.toEntitiesFromModels(
                imageRepository.findByEntityIdAndEntityType(entityId, entityType));
    }

    @Override
    public List<ImageEntity> getImageByEntityIdsAndEntityType(List<Long> entityIds, String entityType) {
        return ImageMapper.INSTANCE.toEntitiesFromModels(
                imageRepository.findByEntityIdInAndEntityType(entityIds, entityType)
        );
    }

    @Override
    public ImageEntity getImageById(Long id) {
        return ImageMapper.INSTANCE.toEntityFromModel(imageRepository.findById(id).orElseThrow(
                () ->{
                    log.error("[ImageAdapter] get image by id failed: {}", id);
                    return new AppException(ErrorCode.GET_IMAGE_FAILED);
                }
        ));
    }

    @Override
    public List<ImageEntity> getImagesByIds(List<Long> ids) {
        return ImageMapper.INSTANCE.toEntitiesFromModels(imageRepository.findByIdIn(ids));
    }

    @Override
    public void deleteImagesByIds(List<Long> ids) {
        try {
            imageRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            log.error("[ImageAdapter] delete images by ids failed: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_IMAGE_FAILED);
        }
    }
}
