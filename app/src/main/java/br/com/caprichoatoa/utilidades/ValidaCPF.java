package br.com.caprichoatoa.utilidades;

public class ValidaCPF {

    public static boolean ValidaCPF(String CPF) {
        // RETIRAR A FORMATACAO
        CPF = CPF.replace(".", "").replace("-", "").trim();
        if (CPF.length() < 11) return false;

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }

        char dig10, dig11;
        int soma, indice, resto, numero, peso;

        try {
            // Calculo do 1o. Digito Verificador
            soma = 0;
            peso = 10;
            for (indice = 0; indice < 9; indice++) {
                numero = (int) (CPF.charAt(indice) - 48);
                soma = soma + (numero * peso);
                peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11))
                dig10 = '0';
            else dig10 = (char) (resto + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            soma = 0;
            peso = 11;
            for (indice = 0; indice < 10; indice++) {
                numero = (CPF.charAt(indice) - 48);
                soma = soma + (numero * peso);
                peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11))
                dig11 = '0';
            else dig11 = (char) (resto + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            return ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}