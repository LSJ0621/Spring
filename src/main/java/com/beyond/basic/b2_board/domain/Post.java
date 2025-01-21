package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
//Builder 어노테이션을 사용하여, Builder패턴으로 엔티티의 생성자를 구성
//빌더패턴의 장점: 매개변수의 순서와 개수를 유연하게 세팅할 수 있다.
@Builder
//JPA의 엔티티 매니저에게 객체를 위임하려면 @Entity 어노테이션 필요.
@Entity
public class Post extends BaseTimeEntity{
    @Id //pk설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
//    @Column(length =50,unique = true,nullable = false)
    private String contents;
//    lazy(지연로딩)로 설정시 member 객체를 사용하지 않는한, member 테이블로 쿼리 발생하지 않음.
//    이에 반해 EAGER(즉시로딩) 타입으로 설정시 사용하지 않아도 member 테이블로 쿼리 발생.
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne에서는 default EAGER
    @JoinColumn(name = "member_id")
    private Member member;

    public PostListDto listFromEntity(){
        return PostListDto.builder().id(this.id).title(this.title).build();
    }
    public PostDetailDto detailFromEntity(String email){
        return PostDetailDto.builder().id(this.id).title(this.title).contents(this.contents).memberEmail(email).build();
    }
}
