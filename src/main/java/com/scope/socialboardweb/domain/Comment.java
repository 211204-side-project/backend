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
    @JoinColumn(name = "userId", nullable = false)
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

}
