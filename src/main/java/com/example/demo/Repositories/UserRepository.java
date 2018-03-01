package com.example.demo.Repositories;

import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String username);

}
