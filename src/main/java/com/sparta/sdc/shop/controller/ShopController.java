package com.sparta.sdc.shop.controller;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.shop.dto.ShopRequestDto;
import com.sparta.sdc.shop.dto.ShopResponseDto;
import com.sparta.sdc.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/sdc")
public class ShopController {
    private final ShopService shopService;
    @PostMapping("/shops")

    public ResponseEntity<ShopResponseDto> createShop(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ShopRequestDto shopRequestDto) {
        return new ResponseEntity<>(shopService.createShop(userDetails,shopRequestDto), HttpStatus.OK);
    }

    @GetMapping("/shops")
    //@ResponseBody
    public ResponseEntity<ShopResponseDto> getShops(){
        ShopResponseDto result = shopService.getShops();
        return ResponseEntity.ok().body(result);
        //return new ResponseEntity<>(shopService.getShops(), HttpStatus.OK);
    }

    @GetMapping("/shops/{shop_id}")

    public ResponseEntity<ShopResponseDto> getShop(@PathVariable Long shop_id){
        return new ResponseEntity<>(shopService.getShop(shop_id), HttpStatus.OK);
    }

    @PutMapping("/shops/{shop_id}")

    public ResponseEntity<ShopResponseDto> updateShop(@PathVariable Long shop_id, @RequestBody ShopRequestDto shopRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return new ResponseEntity<>(shopService.updateShop(shop_id, shopRequestDto, userDetails),HttpStatus.OK);
    }

    @DeleteMapping("/shops/{shop_id}")

    public ResponseEntity<ShopResponseDto> deleteShop(@PathVariable Long shop_id, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return new ResponseEntity<>(shopService.deleteShop(shop_id, userDetails),HttpStatus.OK);
    }
}