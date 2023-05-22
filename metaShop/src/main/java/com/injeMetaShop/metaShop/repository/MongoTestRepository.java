package com.injeMetaShop.metaShop.repository;

import com.injeMetaShop.metaShop.dto.MongoTestDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoTestRepository extends MongoRepository<MongoTestDto, String> {
    MongoTestDto findByName(String name);
}
