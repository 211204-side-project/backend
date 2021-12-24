package com.scope.socialboardweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alert {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private User user;

    @Column(name = "text", nullable = false)
    private String text;

    //양방향 관계 편의 메서드
    public void setUser(User user) {
        if (this.getUser() != null) {
            this.getUser().getAlertList().remove(this);
        }
        user.getAlertList().add(this);
        this.user = user;
    }
}
