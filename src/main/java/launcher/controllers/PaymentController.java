package launcher.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    //requestmapping can only map for GET methods not the others
    @RequestMapping("/payment")
    public String hello(){
        return "HEllo Govna";
    }
}
