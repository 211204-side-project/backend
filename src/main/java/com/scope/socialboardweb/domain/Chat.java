package com.scope.socialboardweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    //양방향 관계 편의 메서드
    public void setChatRoom(ChatRoom chatRoom) {
        if (this.getChatRoom() != null) {
            this.getChatRoom().getChatList().remove(this);
        }
        chatRoom.getChatList().add(this);
        this.chatRoom = chatRoom;
    }
}
