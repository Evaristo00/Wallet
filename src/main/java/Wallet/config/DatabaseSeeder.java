package Wallet.config;

import Wallet.entity.Role;
import Wallet.entity.User;
import Wallet.enums.EnumRole;
import Wallet.repository.RoleRepository;
import Wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    @Value("${develop.seeder}")
    private Boolean crearUsuarios;

    @Override
    public void run(String... args) throws Exception {
        // Crear 10 usuarios administradores
        if (crearUsuarios){
            Role roleUser = roleRepository.findById(EnumRole.ROLE_USER).orElseThrow(() -> new IllegalStateException("El rol ROLE_USER no existe"));
            Role roleAdmin = roleRepository.findById(EnumRole.ROLE_ADMIN).orElseThrow(() -> new IllegalStateException("El rol ROLE_ADMIN no existe"));

            for (int i = 1; i <= 10; i++) {
                User admin = new User();
                admin.setFirstName("Admin" + i);
                admin.setLastName("LastName" + i);
                admin.setEmail("admin" + i + "@example.com");
                admin.setPassword(passwordEncoder.encode("123"));
                admin.setRole(roleAdmin);

                try {
                    userRepository.save(admin);
                }catch (DataIntegrityViolationException   e){
                    //System.out.println(e.getMessage());
                }
            }

            // Crear 10 usuarios regulares
            for (int i = 1; i <= 10; i++) {
                User regularUser = new User();
                regularUser.setFirstName("User" + i);
                regularUser.setLastName("LastName" + i);
                regularUser.setEmail("user" + i + "@example.com");
                regularUser.setPassword(passwordEncoder.encode("123"));
                regularUser.setRole(roleUser);
                try {
                    userRepository.save(regularUser);
                }catch (DataIntegrityViolationException e){
                    //System.out.println(e.getMessage());
                }
            }
        }
    }
}
