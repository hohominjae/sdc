package com.sparta.sdc.shop.controller;

import com.sparta.sdc.common.timestamp.security.UserDetailsImpl;
import com.sparta.sdc.shop.dto.ShopRequestDto;
import com.sparta.sdc.shop.dto.ShopResponseDto;
import com.sparta.sdc.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    @PostMapping("/shop")
    public ShopResponseDto createShop(UserDetailsImpl userDetails, @RequestBody ShopRequestDto shopRequestDto) {
        //controller 에서 requesto -> service
        ShopResponseDto shopResponseDto = shopService.createShop(userDetails,shopRequestDto);
        //service 로직 수행하고나서 reponsedto로 반환
        return shopResponseDto;

//        return ShopService.save(shopRequestDto);
    }
}