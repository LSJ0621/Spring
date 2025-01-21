package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.*;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

//controller + responsebody(모든 메서드에) : RestController
@RestController
@RequestMapping("/member/rest")
//@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원 목록조회
    @GetMapping("/list")
    public ResponseEntity<?> memberList(){
        List<MemberListRes> memberListResList= memberService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "members are found",memberListResList),HttpStatus.OK);
    }

    //    회원 상세조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> memberDetail(@PathVariable long id){
        MemberDetailDto dto = memberService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "member is found",dto),HttpStatus.OK);
    }
    //
//    회원 가입
//    @PostMapping("/create")
//    public String memberCreatePost(@RequestBody MemberCreateDto memberCreateDto){
//        memberService.save(memberCreateDto);
//        return "ok";
//    }
    @PostMapping("/create")
    public ResponseEntity<?> memberCreatePost(@Valid @RequestBody MemberCreateDto memberCreateDto){
        Member member = memberService.save2(memberCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(),
                "members is created",member.getId()),HttpStatus.CREATED);
    }


//    @PostMapping("/update")
//    public String pwdUpdate(@RequestBody MemberUpdateDto memberUpdateDto){
//        memberService.updateByEmail(memberUpdateDto);
//        return "ok";
//    }
//    get:조회, post:등록, patch:부분수정, put: 대체, delete: 삭제
//    axios.patch
    @PatchMapping("/update/pw")
    public ResponseEntity<?> updatePw(@Valid @RequestBody MemberUpdateDto memberUpdateDto){
        memberService.updateByEmail(memberUpdateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "members is updated","ok"),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteId(@PathVariable Long id){
        memberService.delete(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "member infromation is deleted","ok"),HttpStatus.OK);
    }
}
