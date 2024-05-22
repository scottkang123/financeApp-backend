package com.finance.app.controller;
import com.finance.app.model.User;
import com.finance.app.repo.UserRepo;
import com.finance.app.security.JwtService;
import com.finance.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController  {

    @GetMapping("/name")
    public ResponseEntity<Map<String,String>> getUserName(Authentication connectedUser) {
        // Fetch user details from repository
        User user = (User) connectedUser.getPrincipal();
        Map<String, String> response = new HashMap<>();
        response.put("name", user.fullName());
        return ResponseEntity.ok(response);
    }


}
