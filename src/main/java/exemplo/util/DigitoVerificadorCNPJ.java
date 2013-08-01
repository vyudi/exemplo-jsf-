package exemplo.util;

import java.math.BigInteger;

/**
 * Calcula o dígito verificador de CNPJ.
 * O CNPJ tem dois dígitos verificadores obtidos aplicando-se duas vezes o
 * algoritmo de modulo 11 usando o critério "normal".
 * No cálculo do segundo dígito concatena-se o primeiro dígito para cálculo.
 *
  * @author Vitor Yudi Hansen 
 */
public class DigitoVerificadorCNPJ extends DVModulo11 {

    /**
     * Cria uma instância sem valor.
     * Inicializa o número de DVs com 2.
     */
    public DigitoVerificadorCNPJ() {
        super();
        setNumDV(2);
    }

    /**
     * Cria uma instância com valor base do tipo <code>String</code>.
     * Inicializa o número de DVs com 2.
     * @param valor O valor base
     */
    public DigitoVerificadorCNPJ(String valor) {
        super(valor);
        setNumDV(2);
    }

    /**
     * Cria uma instância com valor base do tipo <code>long</code>.
     * Inicializa o número de DVs com 2.
     * @param valor O valor base
     */
    public DigitoVerificadorCNPJ(long valor) {
        super(valor);
        setNumDV(2);
    }

    /**
     * Cria uma instância com valor base do tipo <code>BigInteger</code>.
     * Inicializa o número de DVs com 2.
     * @param valor O valor base
     */
    public DigitoVerificadorCNPJ(BigInteger valor) {
        super(valor);
        setNumDV(2);
    }

    /**
     * Atribui o valor base como <code>String</code> e verifica se o número
     * de dígitos é menor ou igual a 9, caso contrário lança uma exceção.
     * @param valor
     * @throws IllegalArgumentException
     */
    @Override
    public void setValor(String valor) {
        super.setValor(valor);
        if (valor.length() > 12) {
            throw new IllegalArgumentException("CNPJ inválido: " + valor + ". Deve ter no máximo 12 dígitos");
        }
    }

    /**
     * Aplica duas vezes o algoritmo de módulo 11 no critério da RFB.
     * @return O digito verificador.
     */
    @Override
    protected int calculaDV() {
        int dv1 = super.calculaDV();
        // usado para calcular o segundo DV
        DVModulo11 dvCPF = new DVModulo11(getValorLong() * 10 + dv1);
        int dv2 = dvCPF.getDigito();
        return dv1 * 10 + dv2;
    }
}
