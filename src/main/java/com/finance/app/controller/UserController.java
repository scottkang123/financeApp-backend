package com.finance.app.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController  {

    @GetMapping("/name")
    public ResponseEntity<Map<String,String>> getUserName(Authentication connectedUser) {
        Map<String, String> response = new HashMap<>();
        response.put("name", "checking backend is returning");
        return ResponseEntity.ok(response);

    }


}
