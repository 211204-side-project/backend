package com.scope.socialboardweb.dto.test;

import com.scope.socialboardweb.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class UserTableEntityDto implements TableEntityDto {
    @NotNull
    private Long id;
    @NotEmpty
    private String accountId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String nickname;
    @NotNull
    private Boolean isVerifiedEmail;
    @NotNull
    private Boolean isAdmin;
    @NotEmpty
    private String userImgUrl;

    public UserTableEntityDto(Long id, String accountId, String password, String phoneNumber, String nickname, Boolean isVerifiedEmail, Boolean isAdmin, @Nullable String userImgUrl) {
        this.id = id;
        this.accountId = accountId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.isVerifiedEmail = isVerifiedEmail;
        this.isAdmin = isAdmin;

        if (userImgUrl == null || userImgUrl.isEmpty())
            this.userImgUrl = "null";
        else
            this.userImgUrl = userImgUrl;

    }

    public UserTableEntityDto(User user) {
        this.id = user.getId();
        this.accountId = user.getAccountId();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.nickname = user.getNickname();
        this.isVerifiedEmail = user.getIsVerifiedEmail();
        this.isAdmin = user.getIsAdmin();

        if (user.getUserImgUrl() == null || user.getUserImgUrl().isEmpty())
            this.userImgUrl = "null";
        else
            this.userImgUrl = user.getUserImgUrl();

    }

    @Override
    public String getFieldValueByName(String fieldName) {
        switch (fieldName) {
            case "id":
                return String.valueOf(this.getId());
            case "accountId":
                return this.getAccountId();
            case "password":
                return this.getPassword();
            case "phoneNumber":
                return this.getPhoneNumber();
            case "nickname":
                return this.getNickname();
            case "isVerifiedEmail":
                return String.valueOf(this.getIsVerifiedEmail());
            case "isAdmin":
                return String.valueOf(this.getIsAdmin());
            case "userImgUrl":
                return this.getUserImgUrl();
        }
        return null;
    }
}
