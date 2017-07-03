package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

@RestController
public class HelloWorldController {

    private static final String GREETING = "Greetings from Spring Boot!";

    private FeatureManager featureManager;

    @Autowired
    public HelloWorldController(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    @RequestMapping("/")
    public ResponseEntity<?> index() {
        if (featureManager.isActive(MyFeatures.HELLO_WORLD)) {
            StringBuilder sb = new StringBuilder(GREETING);
            if (featureManager.isActive(MyFeatures.REVERSE_GREETING)) {
                sb.reverse();
            }
            return ResponseEntity.ok().body(sb.toString());
        }
        return ResponseEntity.notFound().build();
    }
}
