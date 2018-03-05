package com.example.demo.Repositories;

import com.example.demo.Model.Item;
import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Iterable<Item> findByAuser(User auser);
    Iterable<Item> findAllByStatus(String status);
    Iterable<Item> findByCategoryContainingIgnoreCase(String category);
    Iterable<Item> findByCategory(String category);
    Iterable<Item> findByCategoryAndTitle(String category, String title);
}
