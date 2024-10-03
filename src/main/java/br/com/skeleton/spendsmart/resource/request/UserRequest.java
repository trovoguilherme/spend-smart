package br.com.skeleton.spendsmart.resource.request;

import br.com.skeleton.spendsmart.validation.Email;
import br.com.skeleton.spendsmart.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Email
    private String username;

    @Password
    private String password;

}
