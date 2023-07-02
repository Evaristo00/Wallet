package Wallet.controller;

import Wallet.entity.Role;
import Wallet.enums.EnumRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/prueba")
    public ResponseEntity<?> crearUser() {
        return ResponseEntity.ok("Holaa");
    }
}
