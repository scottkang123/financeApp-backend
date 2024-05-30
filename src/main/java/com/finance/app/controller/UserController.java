package com.finance.app.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController  {

    @GetMapping("/name")
    public ResponseEntity<Map<String,String>> getUserName(Authentication connectedUser) {
        // Fetch user details from repository
        Map<String, String> response = new HashMap<>();
        response.put("name", connectedUser.getName());
        return ResponseEntity.ok(response);
    }


}
