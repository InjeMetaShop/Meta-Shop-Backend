package com.injeMetaShop.metaShop.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Product DTO")
public class ProductDto {
    @Schema(description = "이름", example = "카카오 티셔츠")
    private String name;

    @Schema(description = "가격", example = "10000")
    private int price;

    @Schema(description = "성별", example = "male")
    private String sex;

    @Schema(description = "품목", example = "상의")
    private String category;
}
