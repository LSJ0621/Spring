package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.*;
import com.beyond.basic.b2_board.repository.MemberRepository;
import com.beyond.basic.b2_board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository,MemberRepository memberRepository){
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }


    public List<PostListDto> findAll(){
        return postRepository.findAll().stream().map(m -> m.listFromEntity()).collect(Collectors.toList());
//        List<Post> posts = postRepository.findAll();
//        return posts.stream().map(p->p.listFromEntity()).collect(Collectors.toList());
    }

    public Post save(PostCreateDto postCreateDto) throws IllegalArgumentException{
        Member member = memberRepository.findById(postCreateDto.getMemberId()).orElseThrow(()->new IllegalArgumentException("member is not found"));
        Post post = postRepository.save(postCreateDto.toEntity(member));
        return post;
    }

    public PostDetailDto findById(Long id)throws NoSuchElementException,RuntimeException{

        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 ID입니다"));
//        return postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 ID입니다")).detailFromEntity(email);
        return post.detailFromEntity(post.getMember().getEmail());
    }
}
