package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.MemberUpdateDto;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public List<MemberListRes> memberList(){
        List<MemberListRes> memberListResList= memberService.findAll();
        return memberListResList;
    }

    //    회원 상세조회
    @GetMapping("/detail/{id}")
    public MemberDetailDto memberDetail(@PathVariable long id){
//        name, email , password
        MemberDetailDto dto = memberService.findById(id);
        return dto;
    }
    //
//    회원 가입
    @PostMapping("/create")
    public String memberCreatePost(@RequestBody MemberCreateDto memberCreateDto){
        memberService.save(memberCreateDto);
        return "ok";
    }


//    @PostMapping("/update")
//    public String pwdUpdate(@RequestBody MemberUpdateDto memberUpdateDto){
//        memberService.updateByEmail(memberUpdateDto);
//        return "ok";
//    }
//    get:조회, post:등록, patch:부분수정, put: 대체, delete: 삭제
//    axios.patch
    @PatchMapping("/update/pw")
    public String updatePw(@RequestBody MemberUpdateDto memberUpdateDto){
        memberService.updateByEmail(memberUpdateDto);
        return "ok";
    }
}
