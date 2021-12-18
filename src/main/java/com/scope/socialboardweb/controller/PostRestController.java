package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.PostRequestDto;
import com.scope.socialboardweb.dto.PostResponseDto;
import com.scope.socialboardweb.service.PostService;
import com.scope.socialboardweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostRestController {
    @Autowired
    PostService postService;

//    @Autowired
//    UserService userService;

    //user 관련 로직은 token이 어떤 형식의 인자로 오는 지에 맞춰 추가 예정
    @PostMapping
    PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
//        String userId = userService.findById(/*id*/).getUserId();
        //user token 받기 전까지 임시적으로 1번 유저 저장하는 걸로
        return postService.createPost(postRequestDto, 1L);
    }
    @GetMapping("/{postId}")
    Optional<Post> readPost(@PathVariable Long postId) {
        return postService.readPost(postId);
    }

    @PutMapping("/{postId}")
    boolean updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(postId, postRequestDto);
    }

    @DeleteMapping("/{postId}")
    boolean deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }


}
