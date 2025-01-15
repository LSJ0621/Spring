package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberMemoryRepository {
    private List<Member> memberList = new ArrayList<>();
    public static Long id = 1L;

    public List<Member> findAll(){
        return this.memberList;
    }
    public void save(Member member){

        this.memberList.add(member);
        id++;
    }

    public Optional<Member> findById(long id){
        Member memberDetail = null;
        for(Member m: memberList){
            if(m.getId()==id){
                memberDetail = m;
            }
        }
        return Optional.ofNullable(memberDetail);
    }
    public Optional<Member> findByEmail(String email){
        Member memberDetail = null;
        for(Member m: memberList){
            if(m.getEmail().equals(email)){
                memberDetail = m;
            }
        }
        return Optional.ofNullable(memberDetail);
    }
}
