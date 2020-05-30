package com.task.todoshare.services;

import com.task.todoshare.dto.UserInfoDTO;
import com.task.todoshare.exceptions.UserNotFoundException;
import com.task.todoshare.model.UserEntity;
import com.task.todoshare.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    public UserInfoDTO createUser(UserInfoDTO userInfoDTO) {
        String username = userInfoDTO.getUsername();

        if (userInfoRepository.existsByUsername(username)) {
//            throw new ValidationException("Username already exists");
            System.out.println("username already exists");
            throw new RuntimeException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userInfoDTO.getPassword());
        String fullName = userInfoDTO.getFullName();

        try {
            userInfoRepository.save(new UserEntity(
                    username,
                    encryptedPassword,
                    fullName
            ));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return userInfoDTO;
    }

    public UserEntity findUserById(Long id) {
        return userInfoRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }
}
