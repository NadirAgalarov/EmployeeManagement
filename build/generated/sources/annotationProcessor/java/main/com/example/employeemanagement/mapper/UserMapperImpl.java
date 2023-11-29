package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Role;
import com.example.employeemanagement.entity.User;
import com.example.employeemanagement.model.RoleDto;
import com.example.employeemanagement.model.UserRequest;
import com.example.employeemanagement.model.UserResponse;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-28T18:07:00+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 21 (Oracle Corporation)"
)
public class UserMapperImpl extends UserMapper {

    @Override
    public User modelToEntity(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        Set<Role> set = roleDtoSetToRoleSet( request.getRoleDtos() );
        if ( set != null ) {
            user.roles( set );
        }
        if ( request.getName() != null ) {
            user.name( request.getName() );
        }
        if ( request.getSurname() != null ) {
            user.surname( request.getSurname() );
        }
        if ( request.getEmail() != null ) {
            user.email( request.getEmail() );
        }
        if ( request.getUsername() != null ) {
            user.username( request.getUsername() );
        }
        if ( request.getPassword() != null ) {
            user.password( request.getPassword() );
        }

        user.status( getStatus() );

        return user.build();
    }

    @Override
    public UserResponse entityTomodel(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        if ( user.getName() != null ) {
            userResponse.name( user.getName() );
        }
        if ( user.getSurname() != null ) {
            userResponse.surname( user.getSurname() );
        }
        if ( user.getEmail() != null ) {
            userResponse.email( user.getEmail() );
        }

        return userResponse.build();
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.id( roleDto.getId() );

        return role.build();
    }

    protected Set<Role> roleDtoSetToRoleSet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( roleDtoToRole( roleDto ) );
        }

        return set1;
    }
}
