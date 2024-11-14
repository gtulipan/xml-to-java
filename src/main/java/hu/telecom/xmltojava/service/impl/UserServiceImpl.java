package hu.telecom.xmltojava.service.impl;

import hu.telecom.xmltojava.domain.RoleEntity;
import hu.telecom.xmltojava.domain.UserEntity;
import hu.telecom.xmltojava.mapper.UserMapper;
import hu.telecom.xmltojava.repository.UserRepository;
import hu.telecom.xmltojava.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import generated.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String createUser(User user) {

        UserEntity userEntity = UserEntity.builder()
                .name(user.getName())
                .username(user.getUsername())
                .address(user.getAddress())
                .build();

        Set<RoleEntity> roleEntities = user.getRoles()
                .getRole().stream()
                .map(roleName ->
                        RoleEntity.builder()
                                .roleName(roleName)
                                .user(userEntity)
                                .build())
                .collect(Collectors.toSet());

        userEntity.setRoles(roleEntities);

        userRepository.save(userEntity);

        return userEntity.getId().toString();
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        log.debug("User entities found: {}", userEntities.size());
        if (userEntities.isEmpty()) {
            log.debug("User entities list is empty.");
            return new ArrayList<>();
        }
        return userEntities.stream()
                .map(UserMapper::mapToUser)
                .toList();
    }
}
