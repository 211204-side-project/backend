package com.scope.socialboardweb.dto;

import com.scope.socialboardweb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRequestDto {

    @NotNull
    private User userEntity;
    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String nickname;

    private String userImgUrl;

    public UserRequestDto(User user) {
        this.userEntity = user;
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.nickname = user.getNickname();
        this.userImgUrl = user.getUserImgUrl();
    }
}
