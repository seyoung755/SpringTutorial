package com.tutorial.springboot.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> Java Object -> 테이블로 매핑해주는 기술
@Entity // User class가 Table화된다
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 20)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 해싱을 위해 넉넉한 길이
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // Enum을 쓰는게 좋다. (권한의 집합(domain)을 설정하여 오타 등을 방지한다.)

    @CreationTimestamp // 시간이 자동으로 입력된다
    private Timestamp createDate;
}
