package sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private static final String GREETING = "Greetings from Spring Boot!";

    @RequestMapping("/")
    public ResponseEntity<?> index() {
        if (MyFeatures.HELLO_WORLD.isActive()) {
            StringBuilder sb = new StringBuilder(GREETING);
            if (MyFeatures.REVERSE_GREETING.isActive()) {
                sb.reverse();
            }
            return ResponseEntity.ok().body(sb.toString());
        }
        return ResponseEntity.notFound().build();
    }
}
