package com.tutorial.springboot.domain.user;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> Java Object -> 테이블로 매핑해주는 기술

//@DynamicInsert Insert시에 Null값을 제외시켜준다.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User class가 Table화된다
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 200, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 해싱을 위해 넉넉한 길이
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING) // 해당 Enum이 String인걸 알려준다.
    @Column(nullable = false)
    private Role role; // Enum을 쓰는게 좋다. (권한의 집합(domain)을 설정하여 오타 등을 방지한다.)

    @Column(nullable = true)
    private String oauth; // 기본값 : null & kakao, google

    @CreationTimestamp // 시간이 자동으로 입력된다
    private Timestamp createDate;
}
