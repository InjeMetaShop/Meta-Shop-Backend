package com.injeMetaShop.metaShop.controller;

import com.injeMetaShop.metaShop.service.MongoTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스트 API")
@RestController
public class TestController {
    @Operation(summary = "핑퐁")
    @GetMapping("/api/ping")
    public String pong() {return "Pong!";}
}

@Slf4j
@RestController
@RequestMapping(path = "/api")
class MongoDBTestController {

    @Autowired
    MongoTestService mongoTestService;

    @GetMapping(value = "/find")
    public String findUserData(@RequestParam String name) {
        return mongoTestService.selectUser(name);
    }

    @GetMapping(value = "/save")
    public String saveUserData(@RequestParam String name, @RequestParam int age) {
        log.info("[Controller][Recv] name : {}, age : {}", name, age);
        mongoTestService.saveUser(name, age);

        return mongoTestService.selectUser(name);
    }
}