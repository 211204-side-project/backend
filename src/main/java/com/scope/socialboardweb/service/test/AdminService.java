package com.scope.socialboardweb.service.test;

import com.scope.socialboardweb.domain.Comment;
import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.LoginRequestDto;
import com.scope.socialboardweb.dto.LoginResponseDto;
import com.scope.socialboardweb.dto.test.CommentTableEntityDto;
import com.scope.socialboardweb.dto.test.PostTableEntityDto;
import com.scope.socialboardweb.dto.test.UserTableEntityDto;
import com.scope.socialboardweb.repository.CommentRepository;
import com.scope.socialboardweb.repository.PostRepository;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.repository.custom.CustomUserRepository;
import com.scope.socialboardweb.service.UserService;
import com.scope.socialboardweb.utils.jwt.JwtTokenProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService extends UserService {

//    private JpaRepository<ET, Long> repository;
//
//    public AdminService(UserRepository userRepository, CustomUserRepository customUserRepository, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
//        super(userRepository, customUserRepository, tokenProvider, passwordEncoder);
//    }
//
//    public void setRepositoryTypeClass(Class<ET> entityTypeClass) {
//
//    }

    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public AdminService(UserRepository userRepository, CustomUserRepository customUserRepository, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder, PostRepository postRepository, CommentRepository commentRepository) {
        super(userRepository, customUserRepository, tokenProvider, passwordEncoder);
        this.userRepository = userRepository;
        this.customUserRepository = customUserRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public boolean loginAdmin(LoginRequestDto requestDto) {
        LoginResponseDto adminDto = null;
        try {
            adminDto = super.login(requestDto);
        } catch (Exception e) {
            return false;
        }
        return isAdminAccount(adminDto);
    }

    private boolean isAdminAccount(LoginResponseDto dto) {
        if (dto.getIsAdmin()) {
            return true;
        }
        return false;
    }

    @Transactional
    public List<UserTableEntityDto> getAllUserEntities() {
        List<User> allUserList = userRepository.findAll();
        List<UserTableEntityDto> userTableEntityDtoList = new ArrayList<>();
        for (User user : allUserList) {
            userTableEntityDtoList.add(new UserTableEntityDto(user));
        }

        return userTableEntityDtoList;
    }

    @Transactional
    public List<PostTableEntityDto> getAllPostEntities() {
        List<Post> allPostList = postRepository.findAll();
        List<PostTableEntityDto> postTableEntityDtos = new ArrayList<>();
        for (Post post : allPostList) {
            postTableEntityDtos.add(new PostTableEntityDto(post));
        }

        return postTableEntityDtos;
    }

    @Transactional
    public List<CommentTableEntityDto> getAllCommentEntities() {
        List<Comment> allCommentList = commentRepository.findAll();
        List<CommentTableEntityDto> commentTableEntityDtos = new ArrayList<>();
        for (Comment comment : allCommentList) {
            commentTableEntityDtos.add(new CommentTableEntityDto(comment));
        }

        return commentTableEntityDtos;
    }
}
