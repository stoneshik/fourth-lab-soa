package lab.soa.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {
    @GetMapping({
        "/",
        "/{path:^(?!api$)[^\\.]*}",
        "/**/{path:^(?!api$)[^\\.]*}"
    })
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}
