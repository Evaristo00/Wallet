package Wallet.controller;

import Wallet.dto.JwtAuthenticationResponse;
import Wallet.dto.SingInRequestDTO;
import Wallet.dto.SingUpRequestDTO;
import Wallet.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;



    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SingUpRequestDTO request) {
        return ResponseEntity.ok(authenticationService.singUp(request));
    }

    @PostMapping("signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SingInRequestDTO request) {
        return ResponseEntity.ok(authenticationService.singIn(request));
    }
}