package exemplo.util;

import java.math.BigInteger;

/**
 * Calcula o dígito verificador módulo 10.
 * Neste algoritmo os pesos para cada posição são 2 e 1, alternadamente.
 *
 
 * @author Vitor Yudi Hansen Horstmeyer Bogado
 * @version 1.00
 */
public class DVModulo10 extends DigitoVerificador {

    public DVModulo10(BigInteger valor) {
        super(valor);
    }

    public DVModulo10(long valor) {
        super(valor);
    }

    public DVModulo10(String valor) {
        super(valor);
    }

    public DVModulo10() {
    }

    /**
     * Calcula a parcela a ser somada para uma determinada posição.
     * Quando digito x peso for maior que 9 deve-se somar os 2 digitos
     * do resultado para obter a parcela nesta posição, p. ex.:
     * pos:   76543210
     * valor: 20381709
     * peso:  12121212
     * A parcela na posição 0 será 2x9=18 => parcela = 1+8 = 9
     * A parcela na posição 2 será 2x7=14 => parcela = 1+4 = 5
     * @param pos A posição cuja parcela se deseja calcular
     * @return O valor da parcela na posição desejada
     */
    @Override
    public int getFatorAt(int pos) {
        int f = super.getFatorAt(pos);
        return f > 9 ? f - 9 : f;
    }

    /**
     * O DV é calculado subtraindo-se de 10 o resto da divisão por 10 do
     * somatório, ou seja, <code>10 - getSomatorio() % 10</code>.
     * @return O DV calculado
     */
    @Override
    protected int calculaDV() {
        int d = getSomatorio() % 10;
        return d == 0 ? 0 : 10 - d;
    }

    /**
     * Retorna o peso na posição especificada.
     * Os pesos são 2 e 1, alternados da direita para a esquerda, p. ex.:
     * valor: 20381709
     * peso:  12121212
     * @return O peso na posição especificada
     */
    @Override
    protected int getPesoAt(int pos) {
        return pos % 2 == 0 ? 2 : 1;
    }

}
