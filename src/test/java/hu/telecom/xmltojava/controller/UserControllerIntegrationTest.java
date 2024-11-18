package hu.telecom.xmltojava.controller;

import generated.User;
import hu.telecom.xmltojava.domain.UserEntity;
import hu.telecom.xmltojava.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetAllUsers() {

        var name = "Mekk Elek";
        var userName = "remekElek";
        var address = "Fő utca 123";

        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setName(name);
        userEntity.setUsername(userName);
        userEntity.setAddress(address);
        userRepository.save(userEntity);

        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/users", User[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        User[] users = responseEntity.getBody();
        assertEquals(1, users.length);
        User user = users[0];
        assertEquals(name, user.getName());
        assertEquals(userName, user.getUsername());
        assertEquals(address, user.getAddress());

        userRepository.deleteAll();
    }
}

