package hu.telecom.xmltojava.controller;

import generated.User;
import hu.telecom.xmltojava.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "XML to Java Service", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final Unmarshaller unmarshaller;

    public UserController(UserService userService, @Qualifier("UserUnmarshaller") Unmarshaller unmarshaller) {
        this.userService = userService;
        this.unmarshaller = unmarshaller;
    }

    @Operation(summary = "Post User", description = "Saves a new User")
    @PostMapping(path = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createUserFromJson(@Valid @RequestBody User user) {
        String userId = userService.createUser(user);
        return ResponseEntity.ok(userId);
    }

    @Operation(summary = "Post User", description = "Saves a new User")
    @PostMapping(path = "/user", consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> createUserFromXml(@RequestBody String xml) throws JAXBException {
        User user = (User) unmarshaller.unmarshal(new StringReader(xml));
        String userId = userService.createUser(user);
        return ResponseEntity.ok(userId);
    }

    @Operation(summary = "Get all Users", description = "Retrieves a list of all Users")
    @GetMapping("/users")//(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
