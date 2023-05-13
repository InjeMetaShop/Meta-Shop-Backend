package com.injeMetaShop.metaShop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스트 API")
@RestController
public class TestController {
    @Operation(summary = "핑퐁")
    @GetMapping("/api/ping")
    public String pong() {return "Pong!";}
}
