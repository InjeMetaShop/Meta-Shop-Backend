package com.injeMetaShop.metaShop.repository;

import com.injeMetaShop.metaShop.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByCategory(String category);
    List<Product> findAllBySex(String sex);
    Optional<Product> findById(String id);
}
