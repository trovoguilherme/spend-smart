package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.User;
import br.com.skeleton.spendsmart.exception.UsernameAlreadyExistsException;
import br.com.skeleton.spendsmart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        return userRepository.save(user);
    }

}
