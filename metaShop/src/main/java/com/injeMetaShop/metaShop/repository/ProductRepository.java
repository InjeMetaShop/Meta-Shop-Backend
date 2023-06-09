package com.injeMetaShop.metaShop.repository;

import com.injeMetaShop.metaShop.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByCategory(String category);
    List<Product> findAllBySex(String sex);
}
