package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.CommonDto;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.service.MemberService;
import com.beyond.basic.b2_board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/response/entity")
public class ResponseEntityController {
//    case1. @ResponseStatus 어노테이션 사용
    private final MemberService memberService;

    public ResponseEntityController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/annotation1")
    @ResponseStatus(HttpStatus.OK)
    public String annotation1(){
        return "ok";
    }

    @PostMapping("/annotation2")
    @ResponseStatus(HttpStatus.CREATED)
    public String annotation2(){
        return "ok";
    }

    //    case2. 메서드 체이닝 방식: ResponseEntity의 클래스 사용
    @PostMapping("chaining1")
    public ResponseEntity<Member> chaining1(@Valid @RequestBody MemberCreateDto memberCreateDto){
        Member member = memberService.save2(memberCreateDto);
//            header부에 200 OK, body에 Member 형태의 json
//        return ResponseEntity.ok(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

//    case3. ResponseEntity 객체를 직접 생성하여 custom하는 방식
    @PostMapping("/custom1")
//    Object 자리에 Member, ?도 가능
    public ResponseEntity<Object> custom1(@Valid @RequestBody MemberCreateDto memberCreateDto){
        Member member = memberService.save2(memberCreateDto);
        return  new ResponseEntity<>(member,HttpStatus.CREATED);
    }

    @PostMapping("custom2")
    public ResponseEntity<?> custom2(@Valid @RequestBody MemberCreateDto memberCreateDto){
        Member member = memberService.save2(memberCreateDto);
//        header: 상태코드  + 상태 메세지, body: 상태코드, 상태메세지, 객체
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),"member is found",member), HttpStatus.OK);
    }
}
