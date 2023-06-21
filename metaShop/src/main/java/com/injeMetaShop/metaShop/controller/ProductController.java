package com.injeMetaShop.metaShop.controller;

import com.injeMetaShop.metaShop.dto.ProductDto;
import com.injeMetaShop.metaShop.entity.Product;
import com.injeMetaShop.metaShop.entity.User;
import com.injeMetaShop.metaShop.repository.ProductRepository;
import com.injeMetaShop.metaShop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "3. 상품 페이지", description = "상품 관련 api")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "상품 조회", description = "등록된 모든 상품 조회")
    @GetMapping("/all")
    public @ResponseBody ResponseEntity dashboard(){
        List<Product> productList;
        try{
            productList = productService.allProduct();
        } catch(IllegalStateException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(productList, HttpStatus.OK);
    }

    @Operation(summary = "카테고리에 대한 상품 조회", description = "사용자가 원하는 카테고리에 해당하는 상품 조회")
    @GetMapping("/category/{category}")
    ResponseEntity categoryOfProduct(@Parameter(description = "파라미터는 원하는 category를 입력합니다." + "<br>ex) 상의") @PathVariable("category") String category){
        List<Product> productList;
        try{
            productList = productService.categoryOfProduct(category);
        } catch(IllegalStateException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(productList, HttpStatus.OK);
    }

    @Operation(summary = "성별에 대한 상품 조회", description = "사용자가 원하는 카테고리에 해당하는 상품 조회")
    @GetMapping("/sex/{sex}")
    ResponseEntity sexOfProduct(@Parameter(description = "파라미터는 원하는 성별을 입력합니다." + "<br>ex) 상의") @PathVariable("sex") String sex){
        List<Product> productList;
        try{
            productList = productService.sexOfProdcut(sex);
        } catch(IllegalStateException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(productList, HttpStatus.OK);
    }

    @Operation(summary = "상품 추가", description = "상품을 추가합니다.")
    @PostMapping(value = "/add", consumes = { "multipart/form-data"})
    public ResponseEntity<Void> addProduct(
            @RequestPart("product") @Valid ProductDto productDto,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestPart(value = "fbxFile", required = false) MultipartFile fbxFile) throws IOException {

        productService.addProduct(productDto, thumbnail, fbxFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "ID값에 대한 상품 조회", description = "사용자가 원하는 ID에 해당하는 상품 조회")
    @GetMapping("/{id}")
    ResponseEntity findById(@Parameter(description = "파라미터는 원하는 ID를 입력합니다." + "<br>ex) 64847e4fd4b8925200fad6f8") @PathVariable("id") String id){
        Optional<Product> product;
        try{
            product = productService.findById(id);
        } catch(IllegalStateException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(product, HttpStatus.OK);
    }

}
