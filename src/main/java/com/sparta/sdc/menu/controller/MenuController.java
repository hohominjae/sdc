package com.sparta.sdc.menu.controller;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.menu.dto.MenuRequestDto;
import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.menu.service.MenuService;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.user.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/sdc")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // 메뉴 생성
    @PostMapping("/menus")
    public ResponseEntity<MenuResponseDto> createMenu(@RequestBody MenuRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        MenuResponseDto result = menuService.createMenu(requestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(result);
    }

    // 특정 가게의 메뉴 목록조회
    @GetMapping("/menus/{shop_id}")
    public List<MenuResponseDto> getMenus(@PathVariable Long shop_id){
        return menuService.getMenus(shop_id);
    }

    // 메뉴 수정
    @PutMapping("/menus/{menu_id}")
    public ResponseEntity<MenuResponseDto> updateMenu(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody MenuRequestDto requestDto){
        try {
            MenuResponseDto result = menuService.updateMenu(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(result);
        }catch (RejectedExecutionException e){
            return ResponseEntity.badRequest().build();
        }
    }

    // 메뉴 삭제
    @DeleteMapping("/menus/{menu_id}")
    public ResponseEntity<ApiResponseDto> deleteMenu(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        try {
            menuService.deleteMenu(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("메뉴 삭제 성공", HttpStatus.OK.value()));
        }catch (RejectedExecutionException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
