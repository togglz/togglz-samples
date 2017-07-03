package sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.togglz.junit.TogglzRule;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerIntegrationTests {

    @Rule
    public TogglzRule togglzRule = TogglzRule.allDisabled(MyFeatures.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloWorldFeatureDisabled() throws Exception {
        togglzRule.disable(MyFeatures.HELLO_WORLD);
        mockMvc.perform(get("")).andExpect(status().isNotFound());
    }

    @Test
    public void testHelloWorldFeatureEnabled() throws Exception {
        togglzRule.enable(MyFeatures.HELLO_WORLD);
        mockMvc.perform(get("")).andExpect(status().isOk())
                .andExpect(content().string("Greetings from Spring Boot!"));
    }

    @Test
    public void testHelloWorldFeatureAndReverseGreetingEnabled() throws Exception {
        togglzRule.enable(MyFeatures.HELLO_WORLD);
        togglzRule.enable(MyFeatures.REVERSE_GREETING);
        mockMvc.perform(get("")).andExpect(status().isOk())
                .andExpect(content().string("!tooB gnirpS morf sgniteerG"));
    }
}
