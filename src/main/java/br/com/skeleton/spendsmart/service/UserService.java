package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.User;
import br.com.skeleton.spendsmart.exception.PasswordNotMatchesException;
import br.com.skeleton.spendsmart.exception.UsernameAlreadyExistsException;
import br.com.skeleton.spendsmart.repository.UserRepository;
import br.com.skeleton.spendsmart.resource.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        user.getWallet().setUser(user);
        return userRepository.save(user);
    }

    public User updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        String username = authenticatedUserService.getAuthenticatedUsername();

        User userFound = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), userFound.getPassword())) {
            throw new PasswordNotMatchesException();
        }

        userFound.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));

        return userRepository.save(userFound);
    }


}
