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



        User user1= new User("d@d.com", "password", "Getahun",
                "Dereje",true, "admin");
        Role myRole= roleRepository.findByRolename("ADMIN");
            user1.addRole(myRole);
            userRepository.save(user1);
        Item item=new Item("https://visualhunt.com/photos/s/7/mammals-pet-animal-felines.jpg",
                "cat","Pets","kitty is cute");
        itemRepository.save(item);
        item.setAuser(user1);
        itemRepository.save(item);

      user1= new User("d@d.com", "password", "Rodas",
                "Asfaw",true, "rodas");
         myRole= roleRepository.findByRolename("USER");
        user1.addRole(myRole);
        userRepository.save(user1);
        item=new Item("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEF0Z0-VDJyNDeQUkwnAHorE9DYof05cuZnSfMBHo8VebrQxu_ew",
                "jacket","Cloth","Winter Jacket");
        itemRepository.save(item);
        item.setAuser(user1);
        itemRepository.save(item);

        user1= new User("d@d.com", "password", "Rekik",
                "haile",true, "rekik");
        myRole= roleRepository.findByRolename("USER");
        user1.addRole(myRole);
        userRepository.save(user1);

        item=new Item("https://pisces.bbystatic.com/image2/BestBuy_US/store/ee/2017/mob/flx/flx_0329_sol12145-plus.jpg;maxHeight=333;maxWidth=333",
                "lost item","others","was not seen...");
        itemRepository.save(item);
        item.setAuser(user1);
        itemRepository.save(item);

//        user1.additem(item);
//        userRepository.save(user1);


    }
}
