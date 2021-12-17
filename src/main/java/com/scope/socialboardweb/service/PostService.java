package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.PostRequestDto;
import com.scope.socialboardweb.dto.PostResponseDto;
import com.scope.socialboardweb.repository.PostRepository;
import com.scope.socialboardweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if(user.isPresent()){
            Long postId = postRepository.save(new Post(postRequestDto, user.get())).getId();
            return new PostResponseDto(true, postId);
        } else return new PostResponseDto(false);
    }

    public Optional<Post> readPost(Long postId) {
        return postRepository.findById(postId);
    }

    public boolean updatePost(Long postId, PostRequestDto postRequestDto) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            post.get().setTitle(postRequestDto.getTitle());
            post.get().setContent(postRequestDto.getContent());
            post.get().setPostImgUrl(postRequestDto.getPostImgUrl());
            post.get().setPostVideoUrl(postRequestDto.getPostVideoUrl());
            postRepository.save(post.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean deletePost(Long postId) {
        if(postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
            return true;
        } else { return false; }
    }

    public Post findPostByPostId(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("해당 포스트를 찾을 수 없습니다."));
        return post;

    }




}
