package sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

@RestController
public class HelloWorldController {

    private static final String GREETING = "Greetings from Spring Boot!";
    
    private final FeatureManager manager;

    public HelloWorldController(FeatureManager manager) {
		this.manager = manager;
	}

	@RequestMapping("/")
    public ResponseEntity<?> index() {
        if (manager.isActive(Features.HELLO_WORLD)) {
            StringBuilder sb = new StringBuilder(GREETING);
            if (manager.isActive(Features.REVERSE_GREETING)) {
                sb.reverse();
            }
            return ResponseEntity.ok().body(sb.toString());
        }
        return ResponseEntity.notFound().build();
    }
}
