package com.example.demo.Security;


import com.example.demo.Model.User;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserService(UserRepository userRepository){this.userRepository=userRepository;}


    public User findByUsername(String username){return userRepository.findByUserName(username);}

    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRoleName("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRoleName("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }


}
