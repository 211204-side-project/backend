package com.scope.socialboardweb.dto;

import com.scope.socialboardweb.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostDto {
  private Long id;
  private String title;
  private String content;
  private String postImgUrl;
  private String postVideoUrl;
  private Integer commentQuantity;
  private UserResponseDto writer;

  public PostDto(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.postImgUrl = post.getPostImgUrl();
    this.postVideoUrl = post.getPostVideoUrl();
    this.commentQuantity = post.getCommentList().size();
    this.writer = new UserResponseDto(post.getUser());
  }
}
