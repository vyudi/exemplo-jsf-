/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exemplo.util;

import java.math.BigInteger;

/**
 * Calcula o dígito verificador módulo 11 usando pesos diferentes do padrão usual,
 * conforme especificado pela Receita Federal do Brasil para o cálculo do DV do CPF.
 * O que diferencia do cálculo tradicional é que o peso sempre aumenta a partir
 * de 2, independentemente do tamanho do valor base a ser calculado.
 *
 * @author Engeweb Servíços de Internet
 * @author Vitor Yudi Hansen
 * @version 1.00
 */
public class DVModulo11CPF extends DVModulo11 {

    public DVModulo11CPF(BigInteger valor) {
        super(valor);
    }

    public DVModulo11CPF(long valor) {
        super(valor);
    }

    public DVModulo11CPF(String valor) {
        super(valor);
    }

    public DVModulo11CPF() {
    }

    /**
     * Os pesos sempre aumentam a partir de 2, isto é, não reiniciam em 2 após o 9.
     * @param pos A posição desejada
     * @return O peso na posição
     */
    @Override
    protected int getPesoAt(int pos) {
        return pos + 2;
    }
}
