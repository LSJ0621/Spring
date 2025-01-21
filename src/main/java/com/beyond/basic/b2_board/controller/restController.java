package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.*;
import com.beyond.basic.b2_board.service.MemberService;
import com.beyond.basic.b2_board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post/rest")
@RequiredArgsConstructor
public class restController {
    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<?> postList(){
        List<PostListDto> postList= postService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "members are found",postList),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> PostDetail(@PathVariable long id){
        PostDetailDto dto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "post is found",dto),HttpStatus.OK);
    }

    @PostMapping("/create")
//    Valid와 NotEmpty 등 검증 어노테이션이 한쌍.
    public ResponseEntity<?> postCreatePost(@Valid @RequestBody PostCreateDto postCreateDto){
        Post post = postService.save(postCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(),
                "post is created",post.getId()),HttpStatus.CREATED);
    }
}
