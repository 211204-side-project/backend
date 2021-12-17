package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    public Post findPostBtPostId(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("해당 포스트를 찾을 수 없습니다."));
        return post;

    }
}
