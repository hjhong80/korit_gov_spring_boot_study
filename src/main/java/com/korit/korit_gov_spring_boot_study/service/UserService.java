package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.request.SigninReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.SignupReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SigninRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SignupRespDto;
import com.korit.korit_gov_spring_boot_study.entity.User;
import com.korit.korit_gov_spring_boot_study.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class UserService {
    private static UserService instance;
    private UserRepository userRepository;
    private UserService() {
        userRepository = UserRepository.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean isDuplicatedUsername(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public SignupRespDto signup(SignupReqDto signupReqDto) {
        if (this.isDuplicatedUsername(signupReqDto.getUsername())) {
            return SignupRespDto.builder()
                    .status("failed")
                    .message(signupReqDto.getUsername() + " : 중복된 username이 존재 합니다.")
                    .build();
        }
        User user = userRepository.addUser(signupReqDto.toEntity());
        return SignupRespDto.builder()
                .status("success")
                .message(user.getUsername() + " 님 가입을 환영합니다.")
                .build();
    }

    public SigninRespDto signin(SigninReqDto signinReqDto) {
        User foundUser = userRepository.findUserByUsername(signinReqDto.getUsername());
        if (foundUser == null) {
            return SigninRespDto.builder()
                    .status("failed")
                    .message("username이 잘못 입력 되었습니다.")
                    .build();
        }
        if (!foundUser.getPassword().equals(signinReqDto.getPassword())) {
            return SigninRespDto.builder()
                    .status("failed")
                    .message("password가 잘못 입력 되었습니다.")
                    .build();
        }
        return SigninRespDto.builder()
                .status("success")
                .message(signinReqDto.getUsername() + " : 로그인에 성공하였습니다.")
                .build();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

//    public User signin(SigninReqDto signinReqDto) {
//
//    }
}
