package com.sparta.sdc.shop.service;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.shop.dto.ShopRequestDto;
import com.sparta.sdc.shop.dto.ShopResponseDto;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopResponseDto createShop(UserDetailsImpl userDetails, ShopRequestDto shopRequestDto) {
        Shop shop = Shop.builder()
                .shopName(shopRequestDto.getShopName())
                .shopNumber(shopRequestDto.getShopNumber())
                .address(shopRequestDto.getAddress())
                .delivery(shopRequestDto.getDelivery())
                .user(userDetails.getUser())
                .build();

        return new ShopResponseDto(shopRepository.save(shop));
    }




    public ShopResponseDto getShop(Long shop_id) {
        return new ShopResponseDto(checkShop(shop_id));
    }

    //public List<ShopResponseDto> getShops() {
    //return shopRepository.findAllByOrderByCreatedAtDesc().stream().map(ShopResponseDto::new).toList();
    //}
    public ShopResponseDto getShops() {
        List<ShopResponseDto> shopList = shopRepository.findAll().stream()
                .map(ShopResponseDto::new)
                .collect(Collectors.toList());
        return new ShopResponseDto(shopList);
    }

    //    public static ShopResponseDto updateShop(Long shopId, ShopRequestDto shopRequestDto) {
//        Shop shop = ShopRepository.findById(shopId).orElseThrow(() ->
//                new NullPointerException("해당 가게는 존재하지 않습니다.")
//        );
//        if (shop.getUser().getUsername().equals(user.getUsername())) {
//            shop.update(shopRequestDto);
//        } else {
//            throw new IllegalArgumentException("권한이 없습니다.");
//        }
//    }
//    //    public ShopService(ShopRepository shopRepository){
////        this.shopRepository=shopRepository;
////    }
    public ShopResponseDto updateShop(Long shop_id, ShopRequestDto shopRequestDto,UserDetailsImpl userDetails) {
        Shop shop = checkShop(shop_id);
        if(shop.getUser().getId().equals(userDetails.getUser().getId())){
            shop.update(shopRequestDto);
        }else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        return new ShopResponseDto(shop);
    }

    public ShopResponseDto deleteShop(Long id, UserDetailsImpl userDetails) {
        Shop shop = checkShop(id);
        if(shop.getUser().getId().equals(userDetails.getUser().getId())){
            shopRepository.delete(shop);
        }else {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        return new ShopResponseDto(shop);
    }

    private Shop checkShop(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new NullPointerException("가게가 존재하지 않습니다."));
        return shop;
    }
}