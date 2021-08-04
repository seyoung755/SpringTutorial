package com.tutorial.springboot.domain;

import com.tutorial.springboot.domain.board.Board;
import com.tutorial.springboot.domain.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length=200)
    private String content;

    // 어느 게시글의 댓글인지 나타내는 연관관계 설정이 필요하다.
    @ManyToOne // 여러개의 댓글이 한 게시글에 있을 수 있다.
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
