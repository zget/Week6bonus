package com.example.demo.Controller;

import com.example.demo.Model.Item;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repositories.ItemRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/itemform")
    public  String itemForm(Model model){
        model.addAttribute("item", new Item());
        return "itemform";
    }

    @PostMapping("/itemform")
    public String processPledge(@Valid @ModelAttribute("item") Item item,
                                BindingResult result, Model model, Authentication auth) {

        if (result.hasErrors())
            return "itemform";


        User currentuser= userRepository.findByUserName(auth.getName());
        userRepository.save(currentuser);
        if(item .getImage().isEmpty()) {
            if (item.getCategory().equalsIgnoreCase("Pets"))
                item.setImage("https://static.pexels.com/photos/59523/pexels-photo-59523.jpeg");
            else if (item.getCategory().equalsIgnoreCase("Cloth"))
                item.setImage("https://images.unsplash.com/photo-1457545195570-67f207084966?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=a87ee980e1c3c754f0cb0e929d9c3081&auto=format&fit=crop&w=700&q=60");
            else
                item.setImage("https://pisces.bbystatic.com/image2/BestBuy_US/store/ee/2017/mob/flx/flx_0329_sol12145-plus.jpg;maxHeight=333;maxWidth=333");
        }


        item.setAuser(currentuser);
        itemRepository.save(item);

        model.addAttribute("item", itemRepository.findAll());
        return "redirect:/home";
    }


    @RequestMapping("/list")
    public  String listAll(Model model, Authentication auth){

        try {
            Iterable<Item> selectedItem= new HashSet<>();
            Collection<Role> myroles = (userRepository.findByUserName
                    (auth.getName())).getRoles();
            for (Role role : myroles) {
                if (role.getRolename().equalsIgnoreCase("Admin")) {
                    selectedItem = itemRepository.findAll();
                    break;

                } else
                    selectedItem = itemRepository.findAllByStatus("LOST");
            }


            model.addAttribute("items", selectedItem);
        }
        catch(NullPointerException e){model.addAttribute("items",
                itemRepository.findAllByStatus("LOST"));}


        return "listall";
    }


    @RequestMapping("/listmy")
    public String applicantResume(Model model, Authentication auth) {

        Iterable<Item> myitems=itemRepository.findByAuser(userRepository.
                findByUserName(auth.getName()));

        model.addAttribute("items", myitems);

        return "listall";
    }
    @RequestMapping("/home")
    public  String myHome(){

        return "HOME";
    }

    @RequestMapping("/")
    public  String listPage(){

        return "redirect:/list";
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
                user.addRole(roleRepository.findByRolename("ADMIN"));

            case "USER":
                user.addRole(roleRepository.findByRolename("USER"));


        }

        userRepository.save(user);
        //  userService.saveUser(user);
        model.addAttribute("message", "User account Successfully Created");
        return "redirect:/login";
    }

    @RequestMapping("/update/item/{id}")
    public String updateItem(@PathVariable("id") long id){

        Item myitem=itemRepository.findOne(id);
        if(myitem.getStatus().equalsIgnoreCase("LOST"))
            myitem.setStatus("FOUND");
        else
            myitem.setStatus("LOST");
        itemRepository.save(myitem);

      return "redirect:/list";

    }

    @GetMapping("/postforregistereduser")
    public String postForregisteredUser(Model model){

        model.addAttribute("newitem", new Item());
      model.addAttribute("regUsers",userRepository.findAll());
      return "adminitemform";
    }

    @PostMapping("/postforregistereduser")
    public String processAdminItemForm(@Valid @ModelAttribute("newitem") Item item,
                                       BindingResult result, Model model,
                                       HttpServletRequest request){
  if (result.hasErrors())
            return "adminitemform";


        User selectedUser=userRepository.findByUserName(request.getParameter("username"));
        item.setAuser(selectedUser);

        if(item .getImage().isEmpty()) {
            if (item.getCategory().equalsIgnoreCase("Pets"))
                item.setImage("https://static.pexels.com/photos/59523/pexels-photo-59523.jpeg");
            else if (item.getCategory().equalsIgnoreCase("Cloth"))
                item.setImage("https://images.unsplash.com/photo-1457545195570-67f207084966?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=a87ee980e1c3c754f0cb0e929d9c3081&auto=format&fit=crop&w=700&q=60");
            else
                item.setImage("https://pisces.bbystatic.com/image2/BestBuy_US/store/ee/2017/mob/flx/flx_0329_sol12145-plus.jpg;maxHeight=333;maxWidth=333");
        }


        itemRepository.save(item);

        model.addAttribute("item", itemRepository.findAll());
        return "redirect:/home";
    }

    @RequestMapping("/postforregistereduser")
    public String test(HttpServletRequest request){
        System.out.println(request.getParameter("username"));
        return "how r u";
    }

    @PostMapping("/searchitem")
    public String showSearchResults(HttpServletRequest request, Model model){
        String query = request.getParameter("search");
        model.addAttribute("search", query);
        model.addAttribute("searchitems", itemRepository.findByCategoryContainingIgnoreCase(query));
        return "Searchresult";
    }

}
