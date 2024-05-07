package com.finance.app.security.Token;

import com.finance.app.model.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
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
