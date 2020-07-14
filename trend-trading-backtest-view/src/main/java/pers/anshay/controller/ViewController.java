package pers.anshay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author anshay
 * @date 2020/7/14
 */
@Controller
public class ViewController {
    @GetMapping("/")
    public String view() {
        return "view";
    }
}
