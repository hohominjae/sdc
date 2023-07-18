package com.sparta.sdc.shop.entity;

import com.sparta.sdc.user.entity.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "shop")
//두 객체의 내부의 값이 같은지 숫자로 확인하는 값은 hashcode()이다
//같은 객체인지 확인하는 메소드는 equals()이다.
@EqualsAndHashCode
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String shopnumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean delivery;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostLike> postLikes = new ArrayList<>();

    public User(String userName, String password, String nickName, String email, String address, UserRoleEnum role) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.role = role;
    }
}
