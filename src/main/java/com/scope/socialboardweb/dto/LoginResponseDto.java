package com.scope.socialboardweb.dto;

import com.scope.socialboardweb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    @NotEmpty
    private String token;
    @NotNull
    private Long id;
    @NotEmpty
    private String accountId;
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String phoneNumber;
    @NotNull
    private Boolean isVerifiedEmail;
    @NotNull
    private Boolean isAdmin;

    private String userImgUrl;

    /*TODO
    프로필 사진 필드까지 포함해야 함
     */

    public LoginResponseDto(JwtTokenDto tokenDto, Long id, String accountId, String nickname, String phoneNumber, Boolean isVerifiedEmail, Boolean isAdmin, String userImgUrl) {
        this.token = tokenDto.getToken();
        this.id = id;
        this.accountId = accountId;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.isVerifiedEmail = isVerifiedEmail;
        this.isAdmin = isAdmin;
        this.userImgUrl = userImgUrl;
    }

    public LoginResponseDto(String token, User user) {
        this.token = token;
        this.id = user.getId();
        this.accountId = user.getAccountId();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
        this.isVerifiedEmail = user.getIsVerifiedEmail();
        this.isAdmin = user.getIsAdmin();
        this.userImgUrl = user.getUserImgUrl();
    }
}
