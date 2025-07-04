package biblio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {
    @GetMapping("/")
    public String loginAdherant() {
        return "adherant/logAdherant";
    }
}