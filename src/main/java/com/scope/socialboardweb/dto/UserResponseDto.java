package com.scope.socialboardweb.dto;

import com.scope.socialboardweb.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDto {
    private String accountId;
    private String nickname;
    private String phoneNumber;
    /*TODO
    * 사용자 사진 관련 시스템 구축 시, 사용자 이미지 필드 추가
    */

    public UserResponseDto(User user) {
        this.accountId = user.getAccountId();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
    }


}
