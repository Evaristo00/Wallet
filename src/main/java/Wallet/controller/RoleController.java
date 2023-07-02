package Wallet.controller;

import Wallet.entity.Role;
import Wallet.enums.EnumRole;
import Wallet.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/user")
    public ResponseEntity<?> crearUser() {
        return ResponseEntity.ok(roleService.saveRole(new Role(EnumRole.ROLE_USER)));
    }
    @GetMapping("/admin")
    public ResponseEntity<?> crearAdmin() {
        return ResponseEntity.ok(roleService.saveRole(new Role(EnumRole.ROLE_ADMIN)));
    }
}
