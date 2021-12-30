package com.scope.socialboardweb.service.test;

import com.scope.socialboardweb.dto.LoginRequestDto;
import com.scope.socialboardweb.dto.LoginResponseDto;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService extends UserService {

    public AdminService(UserRepository userRepository) {
        super(userRepository);
    }

    @Transactional
    public boolean loginAdmin(LoginRequestDto requestDto) {
        LoginResponseDto adminDto = null;
        try {
            adminDto = super.login(requestDto);
        } catch (Exception e) {
            return false;
        }
        return hasAdminAuthority(adminDto);
    }

    private boolean hasAdminAuthority(LoginResponseDto dto) {
        if (dto.getIsAdmin()) {
            return true;
        }
        return false;
    }
}
