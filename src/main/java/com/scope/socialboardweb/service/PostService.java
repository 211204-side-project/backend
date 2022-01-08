package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.PostRequestDto;
import com.scope.socialboardweb.dto.PostResponseDto;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.repository.PostRepository;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.repository.custom.CustomPostRepository;
import com.scope.socialboardweb.service.exception.UserNotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CustomPostRepository customPostRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, UserRequestDto userRequestDto) {
        Long userId = userRequestDto.getUserEntity().getId();
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            //post 저장, postId 추출
            Post post = new Post(postRequestDto, user.get());
            Long postId = postRepository.save(post).getId();
            return new PostResponseDto(true, postId);
        } else return new PostResponseDto(false);
    }

    public Optional<Post> readPost(Long postId) {
        return postRepository.findById(postId);
    }

    public boolean updatePost(Long postId, PostRequestDto postRequestDto, UserRequestDto userRequestDto) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            if (!post.get().getUser().getId().equals(userRequestDto.getUserEntity().getId())) {
                throw new UserNotAuthorizedException();
            }
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

    public boolean deletePost(Long postId, UserRequestDto userRequestDto) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            if (!post.get().getUser().getId().equals(userRequestDto.getUserEntity().getId())) {
                throw new UserNotAuthorizedException();
            }
            postRepository.deleteById(postId);
            return true;
        } else { return false; }
    }

    public Post findPostByPostId(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("해당 포스트를 찾을 수 없습니다."));
        return post;
    }

    public List<Post> searchPostByTitleOrContent(String keyword) {
        List<Post> foundPost = customPostRepository.searchPostByTitleOrContent(keyword);
        return foundPost;
    }


}
