package example.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    
    @GetMapping("/")
    public String root(){
        return "Welcome";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome Admin";
    }

    @GetMapping("/user")
    public String user(){
        return "Welcome User";
    }
}
