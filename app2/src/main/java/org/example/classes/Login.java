package org.example.classes;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    @Transient
    private String confirmPassword;
}
