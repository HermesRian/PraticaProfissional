package com.cantina.utils;

public class DocumentValidationUtil {

    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;

        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() != 11) return false;

        // Verifica se todos os dígitos são iguais
        if (cpfLimpo.matches("(\\d)\\1{10}")) return false;

        // Validação dos dígitos verificadores
        int soma = 0;
        int resto;

        // Primeiro dígito verificador
        for (int i = 1; i <= 9; i++) {
            soma += Integer.parseInt(cpfLimpo.substring(i - 1, i)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;
        if (resto != Integer.parseInt(cpfLimpo.substring(9, 10))) return false;

        // Segundo dígito verificador
        soma = 0;
        for (int i = 1; i <= 10; i++) {
            soma += Integer.parseInt(cpfLimpo.substring(i - 1, i)) * (12 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;
        if (resto != Integer.parseInt(cpfLimpo.substring(10, 11))) return false;

        return true;
    }

    public static boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;

        String cnpjLimpo = cnpj.replaceAll("\\D", "");

        if (cnpjLimpo.length() != 14) return false;

        if (cnpjLimpo.matches("(\\d)\\1{13}")) return false;

        int tamanho = cnpjLimpo.length() - 2;
        String numeros = cnpjLimpo.substring(0, tamanho);
        String digitos = cnpjLimpo.substring(tamanho);
        int soma = 0;
        int pos = tamanho - 7;

        for (int i = tamanho; i >= 1; i--) {
            soma += (numeros.charAt(tamanho - i) - '0') * pos--;
            if (pos < 2) pos = 9;
        }

        int resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != (digitos.charAt(0) - '0')) return false;

        tamanho = tamanho + 1;
        numeros = cnpjLimpo.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;

        for (int i = tamanho; i >= 1; i--) {
            soma += (numeros.charAt(tamanho - i) - '0') * pos--;
            if (pos < 2) pos = 9;
        }

        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != (digitos.charAt(1) - '0')) return false;

        return true;
    }


    public static boolean validarCpfCnpj(String documento, Integer tipo) {
        if (documento == null || tipo == null) return false;

        if (tipo == 0) { // CPF
            return validarCPF(documento);
        } else if (tipo == 1) { // CNPJ
            return validarCNPJ(documento);
        }

        return false;
    }

    public static boolean validarCpfCnpjComNacionalidade(String documento, Integer tipo, boolean isBrasileiro) {
        if (!isBrasileiro && documento == null) {
            return true;
        }

        if (isBrasileiro) {
            return validarCpfCnpj(documento, tipo);
        }

        if (!isBrasileiro && documento != null && tipo != null) {
            return validarCpfCnpj(documento, tipo);
        }

        return false;
    }

    public static boolean validarCEP(String cep) {
        if (cep == null) return false;

        String cepLimpo = cep.replaceAll("\\D", "");

        return cepLimpo.length() == 8;
    }


    public static boolean validarTelefone(String telefone) {
        if (telefone == null) return false;

        String telefoneLimpo = telefone.replaceAll("\\D", "");

        return telefoneLimpo.length() >= 8 && telefoneLimpo.length() <= 11;
    }
}
