package com.injeMetaShop.metaShop.entity;

import com.injeMetaShop.metaShop.authorize.jwt.AuthDto;
import com.injeMetaShop.metaShop.dto.RoleDto;
import com.injeMetaShop.metaShop.role.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String name){
        this.email = email;
        this.name = name;
        this.role = Role.USER;
    }

    public static User registerUser(AuthDto.SignupDto signupDto) {
        User user = new User();
        user.email = signupDto.getEmail();
        user.password = signupDto.getPassword();
        user.name = signupDto.getName();
        user.role = Role.USER;
        return user;
    }

    public static User registerAdmin(AuthDto.SignupDto signupDto) {
        User user = new User();
        user.email = signupDto.getEmail();
        user.password = signupDto.getPassword();
        user.name = signupDto.getName();
        user.role = Role.ADMIN;

        return user;
    }
    public void changeUserRole(RoleDto roleDto) {
        this.role = roleDto.getRole();
    }

}
