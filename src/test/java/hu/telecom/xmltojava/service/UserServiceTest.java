package hu.telecom.xmltojava.service;

import hu.telecom.xmltojava.domain.UserEntity;
import hu.telecom.xmltojava.repository.UserRepository;
import hu.telecom.xmltojava.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserRepositoryMock() {
        var name = "Mekk Elek";
        var userName = "remekElek";
        var address = "Fő utca 123";

        var mockUserEntity = new UserEntity();
        mockUserEntity.setId(UUID.randomUUID());
        mockUserEntity.setName(name);
        mockUserEntity.setUsername(userName);
        mockUserEntity.setAddress(address);

        when(userRepository.findAll()).thenReturn(List.of(mockUserEntity));

        var userEntities = userRepository.findAll();
        log.debug("Direct repository call returned size: {}", userEntities.size());

        verify(userRepository).findAll();

        assertEquals(1, userEntities.size());
        UserEntity userEntity = userEntities.get(0);
        assertEquals(name, userEntity.getName());
        assertEquals(userName, userEntity.getUsername());
        assertEquals(address, userEntity.getAddress());
    }

    @Test
    void testEmptyUserRepositoryMock() {

        when(userRepository.findAll()).thenReturn(List.of());

        var userEntities = userRepository.findAll();
        log.debug("Direct repository call returned size: {}", userEntities.size());

        verify(userRepository).findAll();

        assertEquals(0, userEntities.size());
    }
}
