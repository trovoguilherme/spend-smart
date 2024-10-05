package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.BankAccount;
import br.com.skeleton.spendsmart.mapper.BankAccountMapper;
import br.com.skeleton.spendsmart.resource.request.BankAccountRequest;
import br.com.skeleton.spendsmart.resource.request.UpdateBankAccount;
import br.com.skeleton.spendsmart.resource.response.BankAccountResponse;
import br.com.skeleton.spendsmart.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankAccountResource {

    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    @PostMapping("/deposit")
    public ResponseEntity<BankAccountResponse> deposit(@RequestBody @Valid BankAccountRequest bankAccountRequest) {
        BankAccount bankAccountSave = bankAccountService.deposit(bankAccountMapper.toEntity(bankAccountRequest));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bankAccountSave.getId())
                .toUri();

        return ResponseEntity.created(location).body(bankAccountMapper.toResponse(bankAccountSave));
    }

    //TODO Criar put para alterar o valor todo, Criar InvestmentResource, e na Wallet somar sempre todos os saldos dos BankAccounts

    @PutMapping("/deposit")
    public ResponseEntity<BankAccountResponse> updateBalance(@RequestBody @Valid UpdateBankAccount updateBankAccount) {

        BankAccount bankAccountSave = bankAccountService.updateBalance(bankAccountMapper.toEntity(updateBankAccount));

        return ResponseEntity.ok(bankAccountMapper.toResponse(bankAccountSave));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccountResponse> withdraw(@RequestBody @Valid BankAccountRequest bankAccountRequest) {
        BankAccount bankAccountSave = bankAccountService.withdraw(bankAccountMapper.toEntity(bankAccountRequest));

        return ResponseEntity.ok().body(bankAccountMapper.toResponse(bankAccountSave));
    }

}
