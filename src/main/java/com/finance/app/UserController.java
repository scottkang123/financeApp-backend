package com.finance.app;

import com.finance.app.model.User;
import com.finance.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController  {


    @Autowired
     UserRepo repo;

    @PostMapping("/addUser")
    public void addPerson(@RequestBody User user){
        repo.save(user);
    }


}
