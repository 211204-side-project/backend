package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.PostRequestDto;
import com.scope.socialboardweb.dto.PostResponseDto;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.service.PostService;
import com.scope.socialboardweb.service.UserService;
import com.scope.socialboardweb.utils.annotation.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "PostController",description = "게시물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostRestController {

    private final PostService postService;

    @Operation(summary = "게시물 생성")
    @PostMapping(consumes = "application/x-www-form-urlencoded")
//    PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto) {
    PostResponseDto createPost(@ModelAttribute PostRequestDto postRequestDto, @LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto) {
        //user token 받기 전까지 임시적으로 1번 유저 저장하는 걸로
        return postService.createPost(postRequestDto, userRequestDto);
    }
    @Operation(summary = "게시물 가져오기")
    @GetMapping("/{postId}")
    Optional<Post> readPost(@PathVariable Long postId) {
        return postService.readPost(postId);
    }

    @Operation(summary = "게시물 수정")
    @PatchMapping(value = "/{postId}", consumes = "application/x-www-form-urlencoded")
    boolean updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDto postRequestDto, @LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto) {
        return postService.updatePost(postId, postRequestDto, userRequestDto);
    }
    @Operation(summary = "게시물 삭제")
    @DeleteMapping("/{postId}")
    boolean deletePost(@PathVariable Long postId, @LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto) {
        return postService.deletePost(postId, userRequestDto);
    }


}
