package com.sparta.sdc.user.entity;

import com.sparta.sdc.order.entity.Order;
import com.sparta.sdc.review.entity.Review;
import com.sparta.sdc.user.dto.ProfileRequestDto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_tb")
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

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false) //필드 추가 , EnumType.ORDINAL는 integer타입(숫자, 순번)으로 db에 저장하는거. ORDINAL로 저장할 시 다른 객체(?)가 들어오면 db에 있는 데이터가 꼬이기 때문
    @Enumerated(value = EnumType.STRING) //이넘 클래스를 엔티티의 컬럼으로 사용하기 위한 어노테이션. 이 값을 어떤 형태로 db에 저장할지 정의.
    private UserRoleEnum role;
    
    //cascade 수정삭제 관련 오류시 수정
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Review> reviewList =new ArrayList<>();

    //cascade 수정삭제 관련 오류시 수정
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private ProfilePassword profilePassword;

    public User(String userName, String password, String nickName, String email, String address, UserRoleEnum role) {
        this.userName = userName;
        this.password = password;
//        this.profilePassword = new ProfilePassword(password);
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.role = role;

    }

    public void update(ProfileRequestDto requestDto) {
        this.nickName = requestDto.getNickName();
        this.address = requestDto.getAddress();

    }

    public String getUserName() {
        return userName;

    }
}
