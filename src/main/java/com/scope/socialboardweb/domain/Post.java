package com.scope.socialboardweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scope.socialboardweb.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;
    @Setter
    private String postImgUrl;
    @Setter
    private String postVideoUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> commentList = new ArrayList<>();

    //
    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.postImgUrl = postRequestDto.getPostImgUrl();
        this.postVideoUrl = postRequestDto.getPostVideoUrl();
        this.user = user;
    }

    //양방향 관계 편의 메서드
    public void setUser(User user) {
        if (this.getUser() != null) {
            this.getUser().getPostList().remove(this);
        }
        user.getPostList().add(this);
        this.user = user;
    }
}
