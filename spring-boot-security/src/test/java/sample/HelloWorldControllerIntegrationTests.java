package sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sample.Application;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HelloWorldControllerIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @Test
    public void testHelloWorldFeatureWithAdmin() throws Exception {
        mockMvc.perform(get("")
                .with(user("admin")))
                .andExpect(status().isOk())
                .andExpect(content().string("Greetings from Spring Boot!"));
    }

    @Test
    public void testHelloWorldFeatureWithUser1() throws Exception {
        mockMvc.perform(get("")
                .with(user("user1")))
                .andExpect(status().isOk())
                .andExpect(content().string("Greetings from Spring Boot!"));
    }

    @Test
    public void testHelloWorldFeatureWithUser2() throws Exception {
        mockMvc.perform(get("")
                .with(user("user2")))
                .andExpect(status().isOk())
                .andExpect(content().string("!tooB gnirpS morf sgniteerG"));
    }

    @Test
    public void testHelloWorldFeatureWithUser3() throws Exception {
        mockMvc.perform(get("")
                .with(user("user3")))
                .andExpect(status().isOk())
                .andExpect(content().string("!tooB gnirpS morf sgniteerG"));
    }

    @Test
    public void testHelloWorldFeatureWithUser4() throws Exception {
        mockMvc.perform(get("")
                .with(user("user4")))
                .andExpect(status().isOk())
                .andExpect(content().string("Greetings from Spring Boot!"));
    }
}
