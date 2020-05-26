package com.task.todoshare.repository;

import com.task.todoshare.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
