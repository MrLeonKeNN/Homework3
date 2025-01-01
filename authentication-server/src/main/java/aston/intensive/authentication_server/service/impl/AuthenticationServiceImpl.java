package aston.intensive.authentication_server.service.impl;

import aston.intensive.authentication_server.dto.UserDto;
import aston.intensive.authentication_server.exeption.AuthenticationException;
import aston.intensive.authentication_server.jwt.JwtTokenProvider;
import aston.intensive.authentication_server.model.User;
import aston.intensive.authentication_server.repository.UserRepository;
import aston.intensive.authentication_server.service.api.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(UserDto userDto) {
        User user = userRepository.findByLogin(userDto.getLogin())
                .orElseThrow(() -> new AuthenticationException("Invalid login or password"));

        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return tokenProvider.generateToken(user.getLogin(), user.getRoles());
        }

        throw new AuthenticationException("Invalid login or password");
    }
}
