package com.injeMetaShop.metaShop.dto;

import com.injeMetaShop.metaShop.role.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User DTO")
public class UserDto {
    @Schema(description = "email", example = "test@test.com")
    private String email;

    @Schema(description = "비밀번호", example = "password")
    private String password;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "구매목록", example = "상의, 하의")
    private String[] purchase;

    @Schema(description = "권한")
    private Role role;

}
