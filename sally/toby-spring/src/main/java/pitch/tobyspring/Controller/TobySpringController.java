package pitch.tobyspring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TobySpringController {
    @GetMapping("/test")
    public String test(){
        return "Hello World!";
    }
}
