package hu.telecom.xmltojava.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateUserFromXml() throws Exception {
        String userXml = "<user><name>Teszt Elek</name><username>remekElek</username><address>Fő utca 123</address><roles><role>ADMIN</role><role>USER</role></roles></user>";
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_XML)
                        .content(userXml))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("[0-9a-fA-F\\-]{36}")));
    }

}

