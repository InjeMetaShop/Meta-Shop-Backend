package com.injeMetaShop.metaShop.controller;


import com.injeMetaShop.metaShop.authorize.jwt.AuthDto;
import com.injeMetaShop.metaShop.entity.User;
import com.injeMetaShop.metaShop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "2. 관리자 페이지", description = "관리자 페이지 관련 api")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @Operation(summary = "회원조회", description = "Admin의 모든 회원 조회 기능입니다. <br>Role이 ADMIN인 회원만 접속할 수 있습니다.")
    @GetMapping("/users")
    public @ResponseBody
    ResponseEntity getAllMember() {
        List<User> userList;
        try {
            userList = userService.allMember();
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @Operation(summary = "Admin 회원가입", description = "Admin의 회원가입 기능입니다. <br>여기서 회원가입을 하면 Role이 ADMIN인 회원이 생성됩니다.")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid AuthDto.SignupDto signupDto) {
        String encodedPassword = encoder.encode(signupDto.getPassword());
        AuthDto.SignupDto newSignupDto = AuthDto.SignupDto.encodePassword(signupDto, encodedPassword);

        userService.registerAdmin(newSignupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
