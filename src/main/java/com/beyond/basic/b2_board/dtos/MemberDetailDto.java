package com.beyond.basic.b2_board.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 매개변수있는 생성자
@Data
@Builder
public class MemberDetailDto {
    private String name;
    private String email;
    private String password;
}
