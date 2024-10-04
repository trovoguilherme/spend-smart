package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.BankAccount;
import br.com.skeleton.spendsmart.mapper.BankAccountMapper;
import br.com.skeleton.spendsmart.resource.request.BankAccountRequest;
import br.com.skeleton.spendsmart.resource.response.BankAccountResponse;
import br.com.skeleton.spendsmart.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankAccountResource {

    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    @PostMapping
    public ResponseEntity<BankAccountResponse> save(@RequestBody @Valid BankAccountRequest bankAccountRequest) {
        BankAccount bankAccountSave = bankAccountService.save(bankAccountMapper.toEntity(bankAccountRequest));

        return ResponseEntity.ok(bankAccountMapper.toResponse(bankAccountSave));
    }

}
