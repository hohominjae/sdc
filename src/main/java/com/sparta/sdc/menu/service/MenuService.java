package com.sparta.sdc.menu.service;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.menu.dto.MenuRequestDto;
import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.menu.entity.Menu;
import com.sparta.sdc.menu.repository.MenuRepository;
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
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuResponseDto createMenu(UserDetailsImpl userDetails, MenuRequestDto menuRequestDto) {
        if (userDetails.getRole().equals(UserRoleEnum.ADMIN.toString()) || userDetails.getRole().equals(UserRoleEnum.SHOP_KEEPER.toString())){
            Menu menu = Menu.builder()
                    .menuname(menuRequestDto.getMenuname())
                    .menuprice(menuRequestDto.getMenuprice())
                    .menunum(menuRequestDto.getMenunum())
                    .user(userDetails.getUser())
                    .build();

            return new MenuResponseDto(menuRepository.save(menu));
        } else throw new IllegalArgumentException("메뉴 생성 권한이 없습니다.");
    }




    public MenuResponseDto getMenu(Long menu_id) {
        return new MenuResponseDto(checkMenu(menu_id));
    }


    public MenuResponseDto getMenus() {
        List<MenuResponseDto> menuList = menuRepository.findAll().stream()
                .map(MenuResponseDto::new)
                .collect(Collectors.toList());
        return new MenuResponseDto(menuList);
    }

    public MenuResponseDto updateMenu(Long menu_id, MenuRequestDto menuRequestDto,UserDetailsImpl userDetails) {
        Menu menu = checkMenu(menu_id);

        if(userDetails.getRole().equals(UserRoleEnum.ADMIN.toString())) {
            menu.update(menuRequestDto);
            return new MenuResponseDto(menu);
        } else if (menu.getUser().getId().equals(userDetails.getUser().getId()) && userDetails.getRole().equals(UserRoleEnum.SHOP_KEEPER.toString())) {
            menu.update(menuRequestDto);
            return new MenuResponseDto(menu);
        } else {
            throw new IllegalArgumentException("메뉴 수정 권한이 없습니다.");
        }
    }

    public MenuResponseDto deleteMenu(Long menu_id, UserDetailsImpl userDetails) {
        Menu menu = checkMenu(menu_id);

        if(userDetails.getRole().equals(UserRoleEnum.ADMIN.toString())) {
            menuRepository.delete(menu);
            return new MenuResponseDto(menu);
        } else if (menu.getUser().getId().equals(userDetails.getUser().getId()) && userDetails.getRole().equals(UserRoleEnum.SHOP_KEEPER.toString())) {
            menuRepository.delete(menu);
            return new MenuResponseDto(menu);
        } else {
            throw new IllegalArgumentException("메뉴 수정 권한이 없습니다.");
        }
    }

    private Menu checkMenu(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new NullPointerException("메뉴가 존재하지 않습니다."));
        return menu;
    }
}