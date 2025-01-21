package com.beyond.basic.b2_board.dtos;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.parser.Entity;

@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 매개변수있는 생성자
@Data
@Builder
public class MemberCreateDto {
    private String name;
    private String email;
    private String password;

    public Member toEntity(){
        return Member.builder().name(this.name).email(this.email).password(this.password).build();
    }
}
