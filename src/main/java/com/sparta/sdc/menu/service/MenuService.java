package com.sparta.sdc.menu.service;

import com.sparta.sdc.menu.dto.MenuRequestDto;
import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.menu.entity.Menu;
import com.sparta.sdc.menu.repository.MenuRepository;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.shop.repository.ShopRepository;
import com.sparta.sdc.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final ShopRepository shopRepository;
    private final MenuRepository menuRepository;

//    메뉴 생성
    public MenuResponseDto createMenu(MenuRequestDto requestDto, User user) {
        Shop shop = shopRepository.findByUserId(user.getId());

        // dto -> entity
        Menu menu = new Menu(requestDto, shop);

        // DB 저장
        menuRepository.save(menu);

        // Entity -> Dto
        return new MenuResponseDto(menu);
    }

//  메뉴 전체 조회 --> 이부분 수정(shop_id)를 통해 특정 가게의 주문 목록을 조회 해야하는데 menu엔티티의 id가 넘어가고 있음.
    public List<MenuResponseDto> getMenus(Long shop_id) {
        return menuRepository.findById(shop_id).stream().map(MenuResponseDto::new).toList();
    }

//  메뉴 수정
    @Transactional
    public MenuResponseDto updateMenu(Long id, MenuRequestDto requestDto, User user) {
        Menu menu = findMenu(id);

        if(!user.getUserName().equals(menu.getShop().getUser().getUserName())){
            throw new RejectedExecutionException();
        }

        menu.update(requestDto);

        return new MenuResponseDto(menu);
    }

//  메뉴 삭제
    public void deleteMenu(Long id, User user) {
        Menu menu = findMenu(id);

        if(!menu.getShop().getUser().equals(user)){
            throw new RejectedExecutionException();
        }

        menuRepository.delete(menu);

    }


    private Menu findMenu(Long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메뉴는 존재하지 않습니다.")
        );

    }



}
