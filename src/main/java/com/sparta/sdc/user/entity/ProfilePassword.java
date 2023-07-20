package com.sparta.sdc.user.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="editPassword")
@EqualsAndHashCode
public class ProfilePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String firstPassword;

    @Column(nullable = true)
    private String secondPassword;

    @Column(nullable = true)
    private String thirdPassword;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public ProfilePassword(String password, User user){
        this.firstPassword = password;
        this.secondPassword= " ";
        this.thirdPassword=" ";
        this.user = user;
    }

    public void addSecondPassword(String password){
        this.secondPassword = password;
    }

    public void addThirdPassword(String password){
        this.thirdPassword = password;
    }

    public void changePassword(String password){
        this.firstPassword = this.secondPassword;
        this.secondPassword = this.thirdPassword;
        this.thirdPassword = password;
    }


}
