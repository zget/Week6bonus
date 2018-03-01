package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/form")
    public  String myForm(){

        return "FORM";
    }

    @GetMapping("/table")
    public  String myTable(){

        return "TABLE";
    }

    @GetMapping("/home")
    public  String myHome(){

        return "HOME";
    }
}
