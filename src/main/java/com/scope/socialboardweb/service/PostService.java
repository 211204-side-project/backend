package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.PostDto;
import com.scope.socialboardweb.dto.PostRequestDto;
import com.scope.socialboardweb.dto.PostResponseDto;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.repository.PostRepository;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.repository.custom.CustomPostRepository;
import com.scope.socialboardweb.repository.custom.CustomUserRepository;
import com.scope.socialboardweb.service.exception.UserNotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CustomPostRepository customPostRepository;
    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;

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

    /**
     * post 검색 메서드
     * @param rawKeyword 입력(Input)받은 검색어
     * @param nowPage 출력할 페이지 번호
     * @param size 페이지당 최대 게시글 수
     * @return 페이징된 게시글 검색 결과 반환
     */
    public List<PostDto> searchPostByTitleOrContentOrUser(String rawKeyword, int nowPage, int size) {
        String generalKeyword = convertToSearchKeyword(SearchKeywordType.GENERAL, rawKeyword);
        String noSpaceKeyword = convertToSearchKeyword(SearchKeywordType.NO_SPACE, rawKeyword);
        List<PostDto> postDtoList = new ArrayList<>();

        List<User> foundUsers = customUserRepository.searchByNickname(generalKeyword, noSpaceKeyword);
        List<Post> foundPosts = customPostRepository.searchPostByTitleOrContentOrUser(generalKeyword, noSpaceKeyword, foundUsers, nowPage, size);

        for (Post p : foundPosts) {
            postDtoList.add(new PostDto(p));
        }

        return postDtoList;
    }

    /**
     * @param type GENERAL: 공백 포함 검색어, NO_SPACE: 공백 미포함 검색어
     * @param rawKeyword 입력(Input)받은 검색어
     * @return 설정한 type에 맞춰, 변환된 검색어 반환
     */
    private String convertToSearchKeyword(SearchKeywordType type, String rawKeyword) {
        String convertedKeyword = rawKeyword.toLowerCase(Locale.ROOT);
        convertedKeyword = "%" + convertedKeyword + "%";

        if (type == SearchKeywordType.GENERAL) {
            convertedKeyword = convertedKeyword.replace(" ", "%");
        } else if (type == SearchKeywordType.NO_SPACE) {
            convertedKeyword = convertedKeyword.replace(" ", "");
        }

        return convertedKeyword;
    }


}
