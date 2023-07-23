package com.sparta.sdc.menu.repository;

import com.sparta.sdc.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
