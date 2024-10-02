package hust.project.identityservice.mapper;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.dto.request.CreateRoleRequest;
import hust.project.identityservice.model.RoleModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-02T23:54:57+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class IRoleMapperImpl implements IRoleMapper {

    @Override
    public RoleEntity toEntityFromModel(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        RoleEntity.RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.id( roleModel.getId() );
        roleEntity.name( roleModel.getName() );
        roleEntity.description( roleModel.getDescription() );

        return roleEntity.build();
    }

    @Override
    public List<RoleEntity> toEntitiesFromModels(List<RoleModel> roleModels) {
        if ( roleModels == null ) {
            return null;
        }

        List<RoleEntity> list = new ArrayList<RoleEntity>( roleModels.size() );
        for ( RoleModel roleModel : roleModels ) {
            list.add( toEntityFromModel( roleModel ) );
        }

        return list;
    }

    @Override
    public RoleModel toModelFromEntity(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleModel.RoleModelBuilder roleModel = RoleModel.builder();

        roleModel.id( roleEntity.getId() );
        roleModel.name( roleEntity.getName() );
        roleModel.description( roleEntity.getDescription() );

        return roleModel.build();
    }

    @Override
    public RoleEntity toEntityFromRequest(CreateRoleRequest request) {
        if ( request == null ) {
            return null;
        }

        RoleEntity.RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.name( request.getName() );
        roleEntity.description( request.getDescription() );

        return roleEntity.build();
    }
}
