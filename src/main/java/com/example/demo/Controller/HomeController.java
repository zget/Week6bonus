package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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



    @RequestMapping("/login")
    public String login(){

        return "myLoginPage";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(){

        return "accessDenied";
    }

    @GetMapping("/register")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "Registration";
    }


    @PostMapping("/register")
    public String processUser(@Valid @ModelAttribute("user") User user, @RequestParam("selectedRole") String selectedRole, BindingResult result, Model model){
        if(result.hasErrors()){
            return "Registration";
        }
        System.out.println(selectedRole);

        switch (selectedRole)
        {
            case "ADMIN":
                user.addRole(roleRepository.findByRoleName("ADMIN"));

            case "USER":
                user.addRole(roleRepository.findByRoleName("USER"));


        }

        userRepository.save(user);
        //  userService.saveUser(user);
        model.addAttribute("message", "User account Successfully Created");
        return "redirect:/login";
    }



}
