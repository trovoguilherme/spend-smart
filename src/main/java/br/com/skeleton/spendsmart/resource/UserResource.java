package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> save() {
        log.info("entrando para salvar");
        userService.save();
        log.info("Salvo");
        return ResponseEntity.created(URI.create("")).build();
    }

}
