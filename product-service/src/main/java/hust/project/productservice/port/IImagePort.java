package hust.project.productservice.port;

import hust.project.productservice.entity.ImageEntity;

import java.util.List;

public interface IImagePort {
    ImageEntity save(ImageEntity imageEntity);

    List<ImageEntity> saveAll(List<ImageEntity> imageEntities);

    List<ImageEntity> getImageByEntityIdAndEntityType(Long entityId, String entityType);

    List<ImageEntity> getImageByEntityIdsAndEntityType(List<Long> entityIds, String entityType);

    ImageEntity getImageById(Long id);

    List<ImageEntity> getImagesByIds(List<Long> ids);

    void deleteImagesByIds(List<Long> ids);
}
