package com.example.traveler.service;

import com.example.traveler.jwt.JwtTokenProvider;
import com.example.traveler.repository.UserRepository;
import com.example.traveler.model.entity.User;
import com.example.traveler.model.dto.UpdateNicknameDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {

        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    public User updateNicknameById(Long id, UpdateNicknameDTO updateNicknameDTO) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNickname(updateNicknameDTO.getNickname());
            return userRepository.save(user);
        } else {
            // 사용자를 찾지 못한 경우에 대한 처리
            throw new NoSuchElementException("User not found for id: " + id);
        }
    }


    public void deleteUser(Long kakao) {
        Optional<User> user = userRepository.findByKakao(kakao);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
        }
    }

    public User getUser(Long Id) {
        return userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));
    }

    public User getUserByToken(String accessToken) {
        Long Id = jwtTokenProvider.extractId(accessToken);
        return getUser(Id);
    }

}