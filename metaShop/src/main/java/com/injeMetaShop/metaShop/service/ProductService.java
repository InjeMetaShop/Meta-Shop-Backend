package com.injeMetaShop.metaShop.service;

import com.injeMetaShop.metaShop.dto.ProductDto;
import com.injeMetaShop.metaShop.entity.Product;
import com.injeMetaShop.metaShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> allProduct(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public List<Product> categoryOfProduct(String category){
        List<Product> products = productRepository.findAllByCategory(category);
        return products;
    }

    public List<Product> sexOfProdcut(String sex){
        List<Product> products = productRepository.findAllBySex(sex);
        return products;
    }

    public Product addProduct(ProductDto productDto){
        Product product = Product.addProduct(productDto);

        return productRepository.save(product);
    }

}
