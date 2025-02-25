package com.beyond.basic.b2_board.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberListRes {
    private Long id;
    private String name;
    private String email;

}
