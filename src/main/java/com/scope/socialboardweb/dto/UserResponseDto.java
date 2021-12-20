package com.scope.socialboardweb.dto;

import com.scope.socialboardweb.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDto {
    private String accessToken;
    private String userId;
    private String nickname;
    private String phoneNumber;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
    }

    public UserResponseDto(User user, String accessToken) {
        this(user);
        this.accessToken = accessToken;
    }
}
