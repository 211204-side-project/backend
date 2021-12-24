package com.scope.socialboardweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private User user;

    @Column(name = "comment", nullable = false)
    private String comment;

    public Comment(User user, Post post,String comment) {
        this.user = user;
        this.post = post;
        this.comment = comment;
    }

    public void deleteComment(){
        this.post.getCommentList().remove(this);
        this.user.getCommentList().remove(this);
    }

    //양방향 관계 편의 메서드
    public void setUser(User user) {
        if (this.getUser() != null) {
            this.user.getCommentList().remove(this);
        }
        user.getCommentList().add(this);
        this.user = user;
    }

    public void setPost(Post post) {
        if (this.getPost() != null) {
            this.getPost().getCommentList().remove(this);
        }
        post.getCommentList().add(this);
        this.post = post;
    }
}
