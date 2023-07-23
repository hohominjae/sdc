package com.sparta.sdc.review.dto;

import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewResponseDto{

    private String review;
    private LocalDateTime createdate;
    private LocalDateTime modifydate;
    private String username;
    private String shopname;
    private List<MenuResponseDto> menus;

    public ReviewResponseDto(Review review){
        this.review = review.getReview();
        this.createdate = review.getCreatedate();
        this.modifydate = review.getModifydate();
        this.username = review.getUser().getUserName();
        this.shopname = review.getShop().getShopName();
        this.menus = review.getMenus().stream().map(MenuResponseDto::new).toList();
    }
}



