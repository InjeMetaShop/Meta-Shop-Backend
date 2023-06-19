package com.injeMetaShop.metaShop.entity;


import com.injeMetaShop.metaShop.authorize.jwt.AuthDto;
import com.injeMetaShop.metaShop.role.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

    @Field(name = "name")
    private String name;

    @Field(name = "purchase")
    private List<String> purchase;

    @Field(name = "role")
    private Role role;

    @Builder
    public User(String email, String password, String name, List<String> purchase, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.purchase = purchase;
        this.role = role;

    }

    public static User registerUser(AuthDto.SignupDto signupDto){
        return User.builder()
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .name(signupDto.getName())
                .role(Role.USER)
                .purchase(Collections.emptyList())
                .build();
    }

    public static User registerAdmin(AuthDto.SignupDto signupDto) {
        return User.builder()
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .name(signupDto.getName())
                .role(Role.ADMIN)
                .build();
    }
}
