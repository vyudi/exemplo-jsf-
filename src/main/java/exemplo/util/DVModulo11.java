package exemplo.util;

import java.math.BigInteger;

/**
 * Calcula o dígito verificador módulo 11.
 * Este algoritmo apresenta variantes. O mais comum é que quando
 * o dígito verificar resultar 10 seja adotado o valor 0.
 * Outro critério consiste em adotar 1 para dígitos 0 ou 10. Este critério é
 * usado, p. ex., nos digitos verificadores de código de barras de bloquetos
 * bancários.
 *
 * @author Vitor Yudi Hansen
 * @version 1.00
 */
public class DVModulo11 extends DigitoVerificador {

    /**
     * Indica a variante "normal" que substitui o DV 10 por 0.
     */
    public static final int DVM11_NORMAL  = 0;

    /**
     * Indica a variante "codigo de barras" que DVs 0 e 10 por 1.
     */
    public static final int DVM11_BARCODE = 1;

    /**
     * A variante de cálculo em uso.
     */
    private int variante = DVM11_NORMAL;

    /**
     * Cria uma instância sem valor base definido.
     */
    public DVModulo11() {
        super();
    }

    /**
     * Cria uma instância com valor base do tipo <code>String</code>.
     */
    public DVModulo11(String valor) {
        super(valor);
    }

    /**
     * Cria uma instância com valor base do tipo <code>long</code>.
     */
    public DVModulo11(long valor) {
        super(valor);
    }

    /**
     * Cria uma instância com valor base do tipo <code>BigInteger</code>.
     */
    public DVModulo11(BigInteger valor) {
        super(valor);
    }

    public int getVariante() {
        return variante;
    }

    public void setVariante(int variante) {
        this.variante = variante;
        recalculaDv();
    }

    /**
     * Retorna o DV ajustado de acordo com a variante especificada.
     * @param dv O DV calculado
     * @return O DV ajustado
     */
    protected int ajustaDV(int dv) {
        int dvAjustado = dv;
        switch (variante) {
            case DVM11_NORMAL:
                if (dv > 9) dvAjustado = 0;
                break;
            case DVM11_BARCODE:
                if (dv == 0 || dv == 10) dvAjustado = 1;
                break;
        }
        return dvAjustado;
    }

    /**
     * Obtem o peso na posição especificada.
     * Neste algoritmo os pesos variam de 2 a 9, crescentes da direita para a
     * esquerda, repetindo-se a cada 8 posições, p. ex.:
     * valor: 2620381709
     * peso:  3298765432
     * @param pos A posição
     * @return O peso na posição especificada
     */
    @Override
    protected int getPesoAt(int pos) {
        return pos % 8 + 2;
    }

    /**
     * O DV é calculdo obtendo-se o resto da divisão por 11 do
     * somatório multiplicado por 10, ou seja, <code>getSomatorio() * 10 % 11</code>.
     * O resultado da operação é ajustado de acordo com a variante.
     * @return O DV calculado
     * @see #ajustaDV(int)
     */
    @Override
    protected int calculaDV() {
        return ajustaDV(getSomatorio() * 10 % 11);
    }
}
