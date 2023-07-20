package com.sparta.sdc.menu.controller;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.menu.dto.MenuRequestDto;
import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sdc")
public class MenuController {
    private final MenuService menuService;
    @PostMapping("/menus")
    public ResponseEntity<MenuResponseDto> createMenu(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MenuRequestDto menuRequestDto) {
        return new ResponseEntity<>(menuService.createMenu(userDetails,menuRequestDto), HttpStatus.OK);
    }

    //선택한 가게 메뉴판 api 설정?? {shop_id} 넣어줄 필요없는가?
    @GetMapping("/menus")
    //@ResponseBody
    public ResponseEntity<MenuResponseDto> getMenus(){
        MenuResponseDto result = menuService.getMenus();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/menus/{menu_id}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long menu_id){
        return new ResponseEntity<>(menuService.getMenu(menu_id), HttpStatus.OK);
    }


    @PutMapping("/menus/{menu_id}")
    public ResponseEntity<MenuResponseDto> updateMenu(@PathVariable Long menu_id, @RequestBody MenuRequestDto menuRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return new ResponseEntity<>(menuService.updateMenu(menu_id, menuRequestDto, userDetails),HttpStatus.OK);
    }

    @DeleteMapping("/menus/{menu_id}")
    public ResponseEntity<MenuResponseDto> deleteMenu(@PathVariable Long menu_id, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return new ResponseEntity<>(menuService.deleteMenu(menu_id, userDetails),HttpStatus.OK);
    }
}