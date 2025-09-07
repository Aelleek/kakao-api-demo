package com.example.demo.controller;

import com.example.demo.service.KakaoApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@RestController
public class KakaoController {

    private final KakaoApiService kakaoApiService;

    public KakaoController(KakaoApiService kakaoApiService) {
        this.kakaoApiService = kakaoApiService;
    }

    @GetMapping("/friends")
    public ResponseEntity<?> getFriends() {
        return kakaoApiService.getFriends();
    }

    @GetMapping("/message")
    public ResponseEntity<?> sendMessage() {
        return kakaoApiService.sendMessage(kakaoApiService.createDefaultMessage());
    }

    @GetMapping("/friend-message")
    public ResponseEntity<?> sendMessageToFriend(@RequestParam String uuid) {
        return kakaoApiService.sendMessageToFriend(uuid, kakaoApiService.createDefaultMessage());
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return kakaoApiService.logout();
    }

    @GetMapping("/unlink")
    public ResponseEntity<?> unlink() {
        return kakaoApiService.unlink();
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes(); // 카카오에서 내려준 사용자 정보(JSON 그대로)
    }
}