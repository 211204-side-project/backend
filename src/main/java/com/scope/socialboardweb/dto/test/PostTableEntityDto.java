package com.scope.socialboardweb.dto.test;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@RequiredArgsConstructor
public class PostTableEntityDto implements TableEntityDto {
    @NotNull
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String postImgUrl;
    @NotEmpty
    private String postVideoUrl;
    @NotNull
    private Long userId;

    public PostTableEntityDto(Long id, String title, String content, String postImgUrl, String postVideoUrl, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;

        if (postImgUrl == null || postImgUrl.isEmpty())
            this.postImgUrl = "null";
        else
            this.postImgUrl = postImgUrl;

        if (postVideoUrl == null || postVideoUrl.isEmpty())
            this.postVideoUrl = "null";
        else
            this.postVideoUrl = postVideoUrl;
    }

    public PostTableEntityDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUser().getId();

        if (post.getPostImgUrl() == null || post.getPostImgUrl().isEmpty())
            this.postImgUrl = "null";
        else
            this.postImgUrl = post.getPostImgUrl();

        if (post.getPostVideoUrl() == null || post.getPostVideoUrl().isEmpty())
            this.postVideoUrl = "null";
        else
            this.postVideoUrl = post.getPostVideoUrl();
    }

    @Override
    public String getFieldValueByName(String fieldName) {
        switch (fieldName) {
            case "id":
                return String.valueOf(this.getId());
            case "title":
                return this.getTitle();
            case "content":
                return this.getContent();
            case "postImgUrl":
                return this.getPostImgUrl();
            case "postVideoUrl":
                return this.getPostVideoUrl();
            case "userId":
                return String.valueOf(this.getUserId());
        }
        return null;
    }
}
