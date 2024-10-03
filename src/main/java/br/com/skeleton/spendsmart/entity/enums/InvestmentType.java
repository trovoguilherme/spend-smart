package br.com.skeleton.spendsmart.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvestmentType {

    RENDA_FIXA("Renda Fixa"),
    RENDA_VARIAVEL("Renda Variável"),
    CDB("Certificado de Depósito Bancário"),
    LCI("Letra de Crédito Imobiliário"),
    LCA("Letra de Crédito do Agronegócio"),
    TESOURO_DIRETO("Tesouro Direto"),
    ACOES("Ações"),
    FUNDOS_IMOBILIARIOS("Fundos Imobiliários"),
    PREVIDENCIA_PRIVADA("Previdência Privada");

    private final String descricao;

}
