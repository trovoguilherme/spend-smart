package br.com.skeleton.spendsmart.resource.request;

import br.com.skeleton.spendsmart.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {

    @Password
    private String oldPassword;

    @Password
    private String newPassword;

}
