package com.sparta.sdc.user.entity;

import com.sparta.sdc.review.entity.Review;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false) //필드 추가 , EnumType.ORDINAL는 integer타입(숫자, 순번)으로 db에 저장하는거. ORDINAL로 저장할 시 다른 객체(?)가 들어오면 db에 있는 데이터가 꼬이기 때문
    @Enumerated(value = EnumType.STRING) //이넘 클래스를 엔티티의 컬럼으로 사용하기 위한 어노테이션. 이 값을 어떤 형태로 db에 저장할지 정의.
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList =new ArrayList<>();

    public User(String userName, String password, String nickName, String email, String address, UserRoleEnum role) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }
}
