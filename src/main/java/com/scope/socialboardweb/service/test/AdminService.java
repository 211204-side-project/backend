package com.scope.socialboardweb.service.test;

import com.scope.socialboardweb.domain.Comment;
import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.LoginRequestDto;
import com.scope.socialboardweb.dto.LoginResponseDto;
import com.scope.socialboardweb.repository.CommentRepository;
import com.scope.socialboardweb.repository.PostRepository;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.repository.custom.CustomUserRepository;
import com.scope.socialboardweb.service.UserService;
import com.scope.socialboardweb.utils.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService extends UserService {

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


    /**
     * 각 테이블의 모든 데이터를 가져오는 메서드
     * @param entityTypeClass 가져올 테이블의 실제 도메인 엔티티 타입
     * @param dtoTypeClass 엔티티 타입을 변환시킬 dto 타입
     * @param <ET>
     * @param <DT>
     * @return 해당 테이블의 모든 엔티티에 대한 dto 리스트
     * @throws Exception
     */
    @Transactional
    public <ET, DT> List<DT> getAllEntities(Class<ET> entityTypeClass, Class<DT> dtoTypeClass) throws Exception {
        List<ET> allEntityList = null; //쿼리 결과를 저장할 도메인 엔티티 리스트
        List<DT> tableEntityDtoList = new ArrayList<>(); //엔티티를 dto로 변환하여 저장할 Dto 리스트

        //전달받은 엔티티 타입에 따라, 사용할 repository 가 달라진다.
        if (entityTypeClass.equals(User.class)) {
            allEntityList = (List<ET>) userRepository.findAll();
        } else if (entityTypeClass.equals(Post.class)) {
            allEntityList = (List<ET>) postRepository.findAll();
        } else if (entityTypeClass.equals(Comment.class)) {
            allEntityList = (List<ET>) commentRepository.findAll();
        }

        //dto로 변환하여 리스트에 저장하기
        for (ET entity : allEntityList) {
            /*지정된 dto클래스의 생성자에 쿼리결과물인 entity를 전달하며,
              새 dto 객체를 생성한다.
              그리고 dto리스트에 저장한다.
            */
            DT dto = dtoTypeClass.getDeclaredConstructor(entityTypeClass).newInstance(entity);
            tableEntityDtoList.add(dto);
        }

        return tableEntityDtoList;
    }

    private boolean isAdminAccount(LoginResponseDto dto) {
        if (dto.getIsAdmin()) {
            return true;
        }
        return false;
    }

}
