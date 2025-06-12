package com.cantina.utils;

public class DocumentoUtils {

    public static boolean isCpfValido(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        int soma = 0, peso = 10;
        for (int i = 0; i < 9; i++) soma += (cpf.charAt(i) - '0') * peso--;
        int dig1 = 11 - (soma % 11);
        if (dig1 > 9) dig1 = 0;
        if (dig1 != (cpf.charAt(9) - '0')) return false;

        soma = 0; peso = 11;
        for (int i = 0; i < 10; i++) soma += (cpf.charAt(i) - '0') * peso--;
        int dig2 = 11 - (soma % 11);
        if (dig2 > 9) dig2 = 0;
        return dig2 == (cpf.charAt(10) - '0');
    }

    public static boolean isCnpjValido(String cnpj) {
        if (cnpj == null) return false;
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesos1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int[] pesos2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        int soma = 0;
        for (int i = 0; i < 12; i++) soma += (cnpj.charAt(i) - '0') * pesos1[i];
        int dig1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
        if (dig1 != (cnpj.charAt(12) - '0')) return false;

        soma = 0;
        for (int i = 0; i < 13; i++) soma += (cnpj.charAt(i) - '0') * pesos2[i];
        int dig2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
        return dig2 == (cnpj.charAt(13) - '0');
    }
}