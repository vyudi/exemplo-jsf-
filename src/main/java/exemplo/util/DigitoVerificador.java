package exemplo.util;

import java.math.BigInteger;

/**
 * Classe genérica para cálculo de dígito verificador.
 * Classes derivadas desta classe calculam dígitos verificadores
 * específicos, p. ex., módulo 11, módulo 10, CPF, CNPJ, etc.
 * @author Engeweb Serviços de Internet
 * @author Vitor Yudi Hansen 
 * @version 1.00
 */
public abstract class DigitoVerificador {
    private String valor;
    private int somatorio;
    private int dv;
    protected int numDV  = 1;

    /**
     * Cria uma instância sem valor definido com 1 DV.
     */
    protected DigitoVerificador() {
    }

    /**
     * Cria uma instância com o valor base <code>String</code> e o número de DVs especificado.
     * Lança exceção se o valor ou o número de DVs for inválido.
     * @param valor Valor base
     * @param numDV Número de dígitos verificadores
     * @throws IllegalArgumentException
     */
    protected DigitoVerificador(String valor, int numDV) {
        setNumDV(numDV);
        inicializaValor(valor);
    }

    /**
     * Cria uma instância com o valor base <code>long</code> e o número de DVs especificado.
     * Lança exceção se o número de DVs for inválido.
     * @param valor Valor base
     * @param numDV Número de dígitos verificadores
     * @throws IllegalArgumentException
     */
    protected DigitoVerificador(long valor, int numDV) {
        this(String.valueOf(valor), numDV);
    }

    /**
     * Cria uma instância com o valor base <code>BigInteger</code> e o número de DVs especificado.
     * Lança exceção se o número de DVs for inválido.
     * @param valor Valor base
     * @param numDV Número de dígitos verificadores
     * @throws IllegalArgumentException
     */
    protected DigitoVerificador(BigInteger valor, int numDV) {
        this(valor.toString(), numDV);
    }

    /**
     * Cria uma instância com o valor base <code>String</code> e 1 DV.
     * Lança exceção se o valor base não for numérico.
     * @param valor Valor base
     * @throws IllegalArgumentException
     */
    public DigitoVerificador(String valor) {
        this(valor, 1);
    }

    /**
     * Cria uma instância com o valor base <code>long</code> e 1 DV.
     * @param valor Valor base
     */
    protected DigitoVerificador(long valor) {
        this(valor, 1);
    }

    /**
     * Cria uma instância com o valor base <code>BigInteger</code> e 1 DV.
     * @param valor Valor base
     */
    protected DigitoVerificador(BigInteger valor) {
        this(valor, 1);
    }

    private void inicializaValor(String valor) {
        this.valor = validaValor(valor);
        calculaSomatorio();
        dv = calculaDV();
    }

    /**
     * Força o recálculo do DV. Usado por classes derivadas para forçar
     * o recálculo em casos em que não houve mudança do valor base
     * mas de algum outro atributo que exige o recálculo.
     */
    protected void recalculaDv() {
        inicializaValor(valor);
    }

    /**
     * Atribui o valor base como <code>String</code>.
     * Lança exceção se o valor não for composto somente de caracteres numéricos.
     * Este método deverá ser sobrecarregado por classes derivadas que necessitem
     * realizar validações adicionais do valor. Por exemplo, para limitar o número
     * máximo de dígitos admissíveis no valor base. Após fazer as validações
     * desejadas, caso nenhuma exceção seja lançada, este método deverá ser
     * chamado para completar a inicialização.
     * @param valor Valor base
     * @throws IllegalArgumentException
     */
    public void setValor(String valor) {
        inicializaValor(valor);
    }

    private String validaValor(String valor) {
        if (!valor.matches("^\\d+$"))
            throw new IllegalArgumentException(String.format("'%s' contem caracteres não numéricos", valor));
        return valor;
    }

    /**
     * Atribui o valor base como <code>long</code>.
     * @param valor Valor base
     * @throws IllegalArgumentException
     */
    public void setValor(long valor) {
        setValor(String.valueOf(valor));
    }

    /**
     * Atribui o valor base como <code>BigInteger</code>.
     * @param valor Valor base
     * @throws IllegalArgumentException
     */
    public void setValor(BigInteger valor) {
        setValor(valor.toString());
    }

    /**
     * Atribui o número de DVs para cálculo.
     * Lança exceção se o número de DVs for menor que 1.
     * @param numDV
     * @throws IllegalArgumentException
     */
    protected final void setNumDV(int numDV) {
        if (numDV < 1)
            throw new IllegalArgumentException(String.format("Número de dígitos verificadores inválido (%d)", numDV));
        this.numDV = numDV;
    }

    /**
     * Retorna o valor base como <code>String</code>.
     * @param dv Indica se o dígito verificador deve ser concatenado.
     * @return A representação do valor base como <code>String</code>
     */
    public String getValorString(boolean dv) {
        String format = String.format("%%s%%0%d", numDV);
        return dv ? String.format(format, valor, getDigito()) : valor;
    }

    /**
     * Retorna o valor base como <code>int</code>.
     * @param dv Indica se o dígito verificador deve ser concatenado.
     * @return A representação do valor base como <code>int</code>
     */
    public int getValorInt(boolean dv) {
        return dv ? Integer.parseInt(valor) * (int)Math.round(Math.pow(10, numDV)) + getDigito() : Integer.parseInt(valor);
    }

    /**
     * Retorna o valor base como <code>long</code>.
     * @param dv Indica se o dígito verificador deve ser concatenado.
     * @return A representação do valor base como <code>long</code>
     */
    public long getValorLong(boolean dv) {
        return dv ? Long.parseLong(valor) * Math.round(Math.pow(10, numDV)) + getDigito() : Long.parseLong(valor);
    }

    /**
     * Retorna o valor base como <code>BigInteger</code>.
     * @param dv Indica se o dígito verificador deve ser concatenado.
     * @return A representação do valor base como <code>long</code>
     */
    public BigInteger getValorBigInt(boolean dv) {
        BigInteger v = new BigInteger(valor);
        return dv ? v.multiply(BigInteger.TEN.pow(numDV)).add(BigInteger.valueOf(getDigito())) : v;
    }

    /**
     * Retorna o valor base como <code>String</code> sem DV.
     * @return O valor como <code>String</code>
     */
    public String getValorString() {
        return getValorString(false);
    }

    /**
     * Retorna o valor base como <code>int</code> sem DV.
     * @return O valor como <code>int</code>
     */
    public int getValorInt() {
        return getValorInt(false);
    }

    /**
     * Retorna o valor base como <code>long</code> sem DV.
     * @return O valor como <code>long</code>
     */
    public long getValorLong() {
        return getValorLong(false);
    }

    /**
     * Retorna o valor base como <code>BigInteger</code> sem DV.
     * @return O valor como <code>BigInteger</code>
     */
    public BigInteger getValorBigInteger() {
        return getValorBigInt(false);
    }

    /**
     * Retorna o dígito verificador.
     * Este método é otimizado para somente recalcular o DV se o valor
     * base for alterado.
     * @return O dígito verificador
     */
    public int getDigito() {
        return dv;
    }

    /**
     * Retorna o dígito na posição especificada.
     * Os dígitos são ordenados da direita para a esquerda, p. ex.:
     * valor: 20381709
     * pos:   76543210
     * @param pos A posição do dígito desejado
     * @return O dígito na posição
     */
    public int getDigitoAt(int pos) {
        if (pos < 0 || pos >= valor.length())
            throw new IndexOutOfBoundsException(String.format("Posição do dígito (%d) fora da faixa válida (0-%d)", pos, valor.length()-1));
        int p = valor.length()-pos-1;
        return Integer.parseInt(valor.substring(p, p+1));
    }

    /**
     * Calcula a parcela a ser somada no cálculo do DV na posição especificada.
     * A valor retornado é <code>getDigitoAt(pos) * getPesoAt(pos)</code>
     * @param pos A posição do fator desejado.
     * @return O fator na posição desejada
     */
    public int getFatorAt(int pos) {
        return getDigitoAt(pos) * getPesoAt(pos);
    }

    /**
     * Calcula o somatório de digito x peso.
     * Eventualmente poderá ser sobrecarregado se for necessário um cálculo
     * diferente.
     * @return O somatório
     */
    protected void calculaSomatorio() {
        somatorio = 0;
        for (int pos = 0; pos < valor.length(); pos++)
            somatorio += getFatorAt(pos);
    }

    /**
     * Retorna o valor do somatório de digito x peso.
     * Otimizado para somente ser recalculado se o valor base for alterado.
     * @return O somatório
     */
    public int getSomatorio() {
        return this.somatorio;
    }

    /**
     * Retorna o número de dígitos verificadores.
     * @return O número de dígitos verificadores
     */
    public int getNumDV() {
        return numDV;
    }

    /**
     * Retorna <code>true</code> se <code>dv</code> for o DV correto.
     * @param dv O DV a ser verificado
     * @return <code>true</code> se o DV for válido.
     */
    public boolean isValido(int dv) {
        return this.dv == dv;
    }

    /**
     * Retorna o peso na posição especificada.
     */
    protected abstract int getPesoAt(int pos);

    /**
     * Calcula o dígito verificador.
     */
    protected abstract int calculaDV();

}
