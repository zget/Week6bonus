package com.example.demo.Security;


import com.example.demo.Model.Item;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repositories.ItemRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data....");

        roleRepository.save(new Role("USER")) ;
        roleRepository.save(new Role("ADMIN")) ;



        User user1= new User("d@d.com", "password", "gech",
                "dere",true, "admin");
        Role myRole= roleRepository.findByRolename("ADMIN");
            user1.addRole(myRole);
            userRepository.save(user1);

        User user2= new User("d@d.com", "password", "RODAS",
                "ASFAW",true, "user");
         myRole= roleRepository.findByRolename("USER");
        user2.addRole(myRole);
        userRepository.save(user2);

        Item item=new Item("https://www.cats.org.uk/uploads/images/featurebox_sidebar_kids/grief-and-loss.jpg",
                "cat","Pets","fluffy");
        itemRepository.save(item);
        item.setAuser(user1);
        itemRepository.save(item);

        item=new Item("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEF0Z0-VDJyNDeQUkwnAHorE9DYof05cuZnSfMBHo8VebrQxu_ew",
                "jacket","Pets","fluffy");
        itemRepository.save(item);
        item.setAuser(user2);
        itemRepository.save(item);
//        user1.additem(item);
//        userRepository.save(user1);


    }
}
