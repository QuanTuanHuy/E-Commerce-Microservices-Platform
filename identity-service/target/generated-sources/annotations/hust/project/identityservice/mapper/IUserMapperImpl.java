package hust.project.identityservice.mapper;

import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.CreateUserRequest;
import hust.project.identityservice.model.UserModel;
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
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserEntity toEntityFromModel(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userModel.getId() );
        userEntity.email( userModel.getEmail() );
        userEntity.password( userModel.getPassword() );
        userEntity.firstName( userModel.getFirstName() );
        userEntity.lastName( userModel.getLastName() );
        userEntity.phoneNumber( userModel.getPhoneNumber() );
        userEntity.gender( userModel.getGender() );
        userEntity.dateOfBirth( userModel.getDateOfBirth() );
        userEntity.roleId( userModel.getRoleId() );
        userEntity.addressId( userModel.getAddressId() );

        return userEntity.build();
    }

    @Override
    public UserModel toModelFromEntity(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.id( userEntity.getId() );
        userModel.email( userEntity.getEmail() );
        userModel.password( userEntity.getPassword() );
        userModel.firstName( userEntity.getFirstName() );
        userModel.lastName( userEntity.getLastName() );
        userModel.phoneNumber( userEntity.getPhoneNumber() );
        userModel.gender( userEntity.getGender() );
        userModel.dateOfBirth( userEntity.getDateOfBirth() );
        userModel.roleId( userEntity.getRoleId() );
        userModel.addressId( userEntity.getAddressId() );

        return userModel.build();
    }

    @Override
    public List<UserEntity> toEntitiesFromModels(List<UserModel> userModels) {
        if ( userModels == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( userModels.size() );
        for ( UserModel userModel : userModels ) {
            list.add( toEntityFromModel( userModel ) );
        }

        return list;
    }

    @Override
    public UserEntity toEntityFromRequest(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( request.getId() );
        userEntity.email( request.getEmail() );
        userEntity.firstName( request.getFirstName() );
        userEntity.lastName( request.getLastName() );
        userEntity.phoneNumber( request.getPhoneNumber() );
        userEntity.gender( request.getGender() );
        userEntity.dateOfBirth( request.getDateOfBirth() );

        return userEntity.build();
    }
}
