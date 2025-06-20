package com.cantina.utils;

/**
 * Classe utilitária para validação de documentos no backend
 * Implementa as mesmas validações do frontend para garantir consistência
 */
public class DocumentValidationUtil {

    /**
     * Valida um CPF
     * @param cpf - CPF com ou sem formatação
     * @return true se o CPF for válido, false caso contrário
     */
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

    /**
     * Valida um CNPJ
     * @param cnpj - CNPJ com ou sem formatação
     * @return true se o CNPJ for válido, false caso contrário
     */
    public static boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;

        // Remove caracteres não numéricos
        String cnpjLimpo = cnpj.replaceAll("\\D", "");

        // Verifica se tem 14 dígitos
        if (cnpjLimpo.length() != 14) return false;

        // Verifica se todos os dígitos são iguais
        if (cnpjLimpo.matches("(\\d)\\1{13}")) return false;

        // Validação dos dígitos verificadores
        int tamanho = cnpjLimpo.length() - 2;
        String numeros = cnpjLimpo.substring(0, tamanho);
        String digitos = cnpjLimpo.substring(tamanho);
        int soma = 0;
        int pos = tamanho - 7;

        // Primeiro dígito verificador
        for (int i = tamanho; i >= 1; i--) {
            soma += (numeros.charAt(tamanho - i) - '0') * pos--;
            if (pos < 2) pos = 9;
        }

        int resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != (digitos.charAt(0) - '0')) return false;

        // Segundo dígito verificador
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

    /**
     * Valida CPF ou CNPJ dependendo do tipo
     * @param documento - CPF ou CNPJ com ou sem formatação
     * @param tipo - 0 para CPF (PESSOA FÍSICA) ou 1 para CNPJ (PESSOA JURÍDICA)
     * @return true se o documento for válido, false caso contrário
     */
    public static boolean validarCpfCnpj(String documento, Integer tipo) {
        if (documento == null || tipo == null) return false;

        if (tipo == 0) { // CPF
            return validarCPF(documento);
        } else if (tipo == 1) { // CNPJ
            return validarCNPJ(documento);
        }

        return false;
    }

    /**
     * Valida formato do CEP (apenas verifica se tem 8 dígitos)
     * @param cep - CEP com ou sem formatação
     * @return true se o formato do CEP for válido, false caso contrário
     */
    public static boolean validarCEP(String cep) {
        if (cep == null) return false;

        // Remove caracteres não numéricos
        String cepLimpo = cep.replaceAll("\\D", "");

        // Verifica se tem 8 dígitos
        return cepLimpo.length() == 8;
    }

    /**
     * Valida formato do telefone (8 a 11 dígitos)
     * @param telefone - Telefone com ou sem formatação
     * @return true se o formato do telefone for válido, false caso contrário
     */
    public static boolean validarTelefone(String telefone) {
        if (telefone == null) return false;

        // Remove caracteres não numéricos
        String telefoneLimpo = telefone.replaceAll("\\D", "");

        // Verifica se tem entre 8 e 11 dígitos
        return telefoneLimpo.length() >= 8 && telefoneLimpo.length() <= 11;
    }
}
