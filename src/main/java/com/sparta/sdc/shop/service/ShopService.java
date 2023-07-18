package com.sparta.sdc.shop.service;

import com.sparta.sdc.common.timestamp.security.UserDetailsImpl;
import com.sparta.sdc.shop.dto.ShopRequestDto;
import com.sparta.sdc.shop.dto.ShopResponseDto;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    public ShopResponseDto createShop(UserDetailsImpl userDetails, ShopRequestDto shopRequestDto) {
        Shop shop = Shop.builder()
                .shopname(shopRequestDto.getShopname())
                .shopnumber(shopRequestDto.getShopnumber())
                .address(shopRequestDto.getAddress())
                .delivery(shopRequestDto.getDelivery())
                //.user(userDetails.getUser())
                .build();

        return new ShopResponseDto(shopRepository.save(shop));

    }
}