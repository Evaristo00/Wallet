package Wallet.service;


import Wallet.dto.JwtAuthenticationResponse;
import Wallet.dto.SingInRequestDTO;
import Wallet.dto.SingUpRequestDTO;
import Wallet.entity.Role;
import Wallet.entity.User;
import Wallet.enums.EnumRole;
import Wallet.repository.RoleRepository;
import Wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse singUp(SingUpRequestDTO singUpRequestDTO){
        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER).orElseThrow(() -> new IllegalStateException("El rol ROLE_USER no existe"));
        User user = User.builder()
                .email(singUpRequestDTO.email())
                .firstName(singUpRequestDTO.firstName())
                .lastName(singUpRequestDTO.lastName())
                .role(userRole)
                .password(passwordEncoder.encode(singUpRequestDTO.password()))
                .build();
        userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        return  JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse singIn(SingInRequestDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(),request.password()));
        User user = userRepository.findByEmail(request.email())
                .orElseThrow( () -> new IllegalArgumentException("Invalid mail or password"));

        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


}
