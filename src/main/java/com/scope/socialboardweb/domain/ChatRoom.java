package com.scope.socialboardweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "toUserId", nullable = false)
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "fromUserId", nullable = false)
    private User fromUser;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<Chat> chatList = new ArrayList<>();
}
