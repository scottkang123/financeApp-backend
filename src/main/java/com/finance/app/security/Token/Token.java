package com.finance.app.security.Token;

import com.finance.app.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    //can have many tokens to one user
    //need to specify the user
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
