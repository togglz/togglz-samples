package sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.StateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerIntegrationTests {
	
	@Autowired
	private StateRepository state;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloWorldFeatureDisabled() throws Exception {
        state.setFeatureState(new FeatureState(HelloWorldController.HELLO_WORLD, false));
        mockMvc.perform(get("")).andExpect(status().isNotFound());
    }

    @Test
    public void testHelloWorldFeatureEnabled() throws Exception {
        mockMvc.perform(get("")).andExpect(status().isOk())
                .andExpect(content().string("Greetings from Spring Boot!"));
    }

    @Test
    public void testHelloWorldFeatureAndReverseGreetingEnabled() throws Exception {
        state.setFeatureState(new FeatureState(HelloWorldController.REVERSE_GREETING, true));
        mockMvc.perform(get("")).andExpect(status().isOk())
                .andExpect(content().string("!tooB gnirpS morf sgniteerG"));
    }
}
