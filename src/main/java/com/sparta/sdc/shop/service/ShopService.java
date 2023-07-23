package com.sparta.sdc.shop.service;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.shop.dto.ShopRequestDto;
import com.sparta.sdc.shop.dto.ShopResponseDto;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.shop.repository.ShopRepository;
import com.sparta.sdc.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopResponseDto createShop(UserDetailsImpl userDetails, ShopRequestDto shopRequestDto) {
        if (userDetails.getRole().equals(UserRoleEnum.ADMIN.toString()) || userDetails.getRole().equals(UserRoleEnum.SHOP_KEEPER.toString())){
            Shop shop = Shop.builder()
                    .shopName(shopRequestDto.getShopName())
                    .shopNumber(shopRequestDto.getShopNumber())
                    .address(shopRequestDto.getAddress())
                    .delivery(shopRequestDto.isDelivery())
                    .user(userDetails.getUser())
                    .build();

            return new ShopResponseDto(shopRepository.save(shop));
        } else throw new IllegalArgumentException("가게 생성 권한이 없습니다.");
    }

    public ShopResponseDto getShop(Long shop_id) {
        return new ShopResponseDto(checkShop(shop_id));
    }

    public ShopResponseDto getShops() {
        List<ShopResponseDto> shopList = shopRepository.findAll().stream()
                .map(ShopResponseDto::new)
                .collect(Collectors.toList());
        return new ShopResponseDto(shopList);
    }

    @Transactional
    public ShopResponseDto updateShop(Long shop_id, ShopRequestDto shopRequestDto,UserDetailsImpl userDetails) {
        Shop shop = checkShop(shop_id);

        if(userDetails.getRole().equals(UserRoleEnum.ADMIN.toString())) {
            shop.update(shopRequestDto);
            return new ShopResponseDto(shop);
        } else if (shop.getUser().getId().equals(userDetails.getUser().getId()) && userDetails.getRole().equals(UserRoleEnum.SHOP_KEEPER.toString())) {
            shop.update(shopRequestDto);
            return new ShopResponseDto(shop);
        } else {
            throw new IllegalArgumentException("가게 수정 권한이 없습니다.");
        }
    }

    @Transactional
    public ShopResponseDto deleteShop(Long shop_id, UserDetailsImpl userDetails) {
        Shop shop = checkShop(shop_id);

        if(userDetails.getRole().equals(UserRoleEnum.ADMIN.toString())) {
            shopRepository.delete(shop);
            return new ShopResponseDto(shop);
        } else if (shop.getUser().getId().equals(userDetails.getUser().getId()) && userDetails.getRole().equals(UserRoleEnum.SHOP_KEEPER.toString())) {
            shopRepository.delete(shop);
            return new ShopResponseDto(shop);
        } else {
            throw new IllegalArgumentException("가게 수정 권한이 없습니다.");
        }
    }

    private Shop checkShop(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new NullPointerException("가게가 존재하지 않습니다."));
        return shop;
    }
}