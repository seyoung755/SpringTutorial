package com.tutorial.springboot.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 사용 -> <html>태그가 섞여서 디자인이 됨.

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Many = Board, User = One. 하나의 유저는 여러 게시글을 작성할 수 있다.
    @JoinColumn(name="userId")
    private User user; // DB는 오브젝트를 저장할 수 없으므로 Foreign Key를 사용한다. 그러나 Java는 오브젝트를 저장할 수 있다.

    @CreationTimestamp
    private Timestamp createDate;
}
