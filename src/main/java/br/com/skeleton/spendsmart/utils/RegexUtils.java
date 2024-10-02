package br.com.skeleton.spendsmart.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class RegexUtils {

    /**
     * Regex garante pelo menos uma letra maiúscula, um número, um caracter especial e comprimento de no mínimo de 6 letras
     */
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$");

}
