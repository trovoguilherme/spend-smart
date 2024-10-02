package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.User;
import br.com.skeleton.spendsmart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save() {
        userRepository.save(User.builder()
                .username("teste")
                .password(new BCryptPasswordEncoder().encode("teste"))
                .build());
    }

}
