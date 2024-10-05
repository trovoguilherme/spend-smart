package br.com.skeleton.spendsmart.resource.request;

import br.com.skeleton.spendsmart.resource.response.BankAccountResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletResponse {

    private Double totalBalance;

    private List<BankAccountResponse> bankAccounts;

}
