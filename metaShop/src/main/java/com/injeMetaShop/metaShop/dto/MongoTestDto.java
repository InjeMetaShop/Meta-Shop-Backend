package com.injeMetaShop.metaShop.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class MongoTestDto {
    private String id;
    private String name;
    private int age;
}
