package com.scope.socialboardweb.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Post {
    @Id
    @Column(name = "post_id", nullable = false)
    private Long id;

    private String title;
    private String Content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
