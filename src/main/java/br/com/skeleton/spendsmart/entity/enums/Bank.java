package br.com.skeleton.spendsmart.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bank {

    ITAU("Itaú"),
    BRADESCO("Bradesco"),
    BANCO_DO_BRAZIL("Banco do Brasil"),
    SANTANDER("Santander"),
    NUBANK("Nubank"),
    C6BANK("C6 Bank"),
    BANCO_INTER("Banco Inter"),
    BTG_PACTUAL("BTG Pactual"),
    ORIGINAL("Banco Original"),
    SAFEWAY("Safeway"),
    PAGBANK("Pagbank");

    private final String bankName;

}
