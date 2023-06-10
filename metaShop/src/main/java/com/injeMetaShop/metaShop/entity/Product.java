package com.injeMetaShop.metaShop.entity;

import com.injeMetaShop.metaShop.dto.ProductDto;
import jakarta.persistence.GeneratedValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    @GeneratedValue
    private String id;

    @Field(name = "name")
    @Indexed(unique = true) // 중복 제약 설정
    private String name;

    @Field(name = "price")
    private int price;

    @Field(name = "imagePath")
    private String imagePath;

    @Field(name = "sex")
    private String sex;

    @Field(name = "category")
    private String category;

    @Builder
    public Product(String name, int price, String imagePath, String sex, String category) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.sex = sex;
        this.category = category;
    }

    public static Product addProduct(ProductDto productDto){
        Product product = new Product();
        product.name = productDto.getName();
        product.price = productDto.getPrice();
        product.imagePath = productDto.getImagePath();
        product.sex = productDto.getSex();
        product.category = productDto.getCategory();

        return product;
    }
}