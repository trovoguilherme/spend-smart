package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.User;
import br.com.skeleton.spendsmart.mapper.UserMapper;
import br.com.skeleton.spendsmart.resource.request.UserRequest;
import br.com.skeleton.spendsmart.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserRequest userRequest) {

        User user = userService.save(userMapper.toEntity(userRequest));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }

}
