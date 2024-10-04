package br.com.skeleton.spendsmart.resource.response;

import br.com.skeleton.spendsmart.entity.enums.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponse {

    private Double balance;

    private Bank bankType;

    private LocalDateTime updatedAt;

}
