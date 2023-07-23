package com.sparta.sdc.user.repository;

import com.sparta.sdc.user.entity.ProfilePassword;
import com.sparta.sdc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePasswordRepository extends JpaRepository<ProfilePassword, Long> {

    ProfilePassword findByUser(User user);
}
