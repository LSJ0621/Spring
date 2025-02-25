package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//JPA의 엔티티 매니저에게 객체를 위임하려면 @Entity 어노테이션 필요.
@Entity
@Builder
public class Member extends BaseTimeEntity {
    @Id //pk설정
//    identity : auto_increment 설정(AUTO설정은 jpa에게 적절한 전략을 위임하는것)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    String은 별다른 설정 없을 경우 varchar(255)로 DB컬럼으로 설정. 변수명 == 컬러명으로 변환.
    private String name;
    @Column(length =50,unique = true,nullable = false)
    private String email;
//    @Column(name = "pw") 이렇게 할수는 있으나, 되도록이면 컬럼명과 변수명을 일치시키는것이 개발의 혼선을 줄일 수 있음.
    private String password;
//    java에서 캐멀케이스 사용시 db에는 created_time으로 컬럼변환.


//    public Member(String name ,String email, String password){
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }

    public MemberListRes listFromEntity(){
        return MemberListRes.builder().id(this.id).name(this.name).email(this.email).build();
    }
    public MemberDetailDto detailFromEntity(){
        return MemberDetailDto.builder().name(this.name).email(this.email).password(this.password).build();
    }
    public void updatePw(String newPw){
        this.password = newPw;
    }
}
