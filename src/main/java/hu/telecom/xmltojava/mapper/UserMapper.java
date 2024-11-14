package hu.telecom.xmltojava.mapper;

import hu.telecom.xmltojava.domain.UserEntity;
import generated.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User mapToUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId().toString());
        user.setName(userEntity.getName());
        user.setUsername(userEntity.getUsername());
        user.setAddress(userEntity.getAddress());

        User.Roles roles = new User.Roles();
        userEntity.getRoles()
                .forEach(roleEntity -> roles.getRole().add(roleEntity.getRoleName()));
        user.setRoles(roles);

        return user;
    }
}
