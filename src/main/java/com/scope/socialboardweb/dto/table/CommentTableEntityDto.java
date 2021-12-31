package com.scope.socialboardweb.dto.table;

import com.scope.socialboardweb.domain.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@RequiredArgsConstructor
public class CommentTableEntityDto implements TableEntityDto {

    @NotNull
    private Long id;
    @NotEmpty
    private String comment;
    @NotNull
    private Long postId;
    @NotNull
    private Long userId;

    public CommentTableEntityDto(Long id, String comment, Long postId, Long accountId) {
        this.id = id;
        this.comment = comment;
        this.postId = postId;
        this.userId = accountId;
    }

    public CommentTableEntityDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
    }

    @Override
    public String getFieldValueByName(String fieldName) {
        switch (fieldName) {
            case "id":
                return String.valueOf(this.getId());
            case "comment":
                return this.getComment();
            case "postId":
                return String.valueOf(this.getPostId());
            case "userId":
                return String.valueOf(this.getUserId());
        }
        return null;
    }
}
