package com.injeMetaShop.metaShop.controller;

import com.injeMetaShop.metaShop.entity.Product;
import com.injeMetaShop.metaShop.entity.User;
import com.injeMetaShop.metaShop.repository.ProductRepository;
import com.injeMetaShop.metaShop.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "4. 결제 페이지", description = "결제 관련 api")
@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Operation(summary = "아이템 결제", description = "아이템을 결제합니다.")
    @PostMapping("/item/{itemId}/{userEmail}")
    public ResponseEntity<String> purchaseItem(
            @Parameter(description = "파라미터는 구매하고자 하는 itemId를 입력합니다." + "<br>ex) 64848e69f3c1e01e622cfe78") @PathVariable("itemId") String itemId,
            @Parameter(description = "파라미터는 사용자의 이메일을 입력합니다." + "<br>ex) test@test.com") @PathVariable("userEmail") String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
            Product product = productRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid product"));

            user.getPurchase().add(itemId);
            userRepository.save(user);

            return ResponseEntity.ok("Item purchased successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "결제한 아이템 조회", description = "결제한 아이템을 조회합니다.")
    @GetMapping("/show/{userEmail}")
    public ResponseEntity<List<String>> showPurchasedItems(
            @Parameter(description = "파라미터는 사용자의 이메일을 입력합니다." + "<br>ex) test@test.com") @PathVariable("userEmail") String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
            List<String> purchasedItems = user.getPurchase();
            return ResponseEntity.ok(purchasedItems);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}