package com.injeMetaShop.metaShop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.injeMetaShop.metaShop.dto.MongoTestDto;
import com.injeMetaShop.metaShop.repository.MongoTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MongoTestService {
    final
    MongoTestRepository mongoTestRepository;

    public MongoTestService(MongoTestRepository mongoTestRepository) {
        this.mongoTestRepository = mongoTestRepository;
    }

    public String selectUser(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (mongoTestRepository.findByName(name) == null) {
                log.info("[Service] user name : {} not exist!!", name);
                return String.format("user name : %s not exist!!", name);
            } else {
                return objectMapper.writeValueAsString(mongoTestRepository.findByName(name));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public void saveUser(String name, int age) {

        MongoTestDto mongoTestDto = new MongoTestDto();
        mongoTestDto.setName(name);
        mongoTestDto.setAge(age);

        if (mongoTestRepository.findByName(name) != null) {
            log.info("[Service][update] name is already exist!!");
            mongoTestDto.setId(mongoTestRepository.findByName(name).getId());
        } else {
            log.info("[Service][insert] New name received!!");
        }

        mongoTestRepository.save(mongoTestDto);
    }
}
