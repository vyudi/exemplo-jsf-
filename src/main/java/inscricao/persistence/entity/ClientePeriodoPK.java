/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inscricao.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vitor Yudi Hansen
 */
@Embeddable
public class ClientePeriodoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "cliente_cpf")
    private String clienteCpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "periodo_periodo")
    private String periodoPeriodo;

    public ClientePeriodoPK() {
    }

    public ClientePeriodoPK(String clienteCpf, String periodoPeriodo) {
        this.clienteCpf = clienteCpf;
        this.periodoPeriodo = periodoPeriodo;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getPeriodoPeriodo() {
        return periodoPeriodo;
    }

    public void setPeriodoPeriodo(String periodoPeriodo) {
        this.periodoPeriodo = periodoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteCpf != null ? clienteCpf.hashCode() : 0);
        hash += (periodoPeriodo != null ? periodoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientePeriodoPK)) {
            return false;
        }
        ClientePeriodoPK other = (ClientePeriodoPK) object;
        if ((this.clienteCpf == null && other.clienteCpf != null) || (this.clienteCpf != null && !this.clienteCpf.equals(other.clienteCpf))) {
            return false;
        }
        if ((this.periodoPeriodo == null && other.periodoPeriodo != null) || (this.periodoPeriodo != null && !this.periodoPeriodo.equals(other.periodoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inscricao.persistence.entity.ClientePeriodoPK[ clienteCpf=" + clienteCpf + ", periodoPeriodo=" + periodoPeriodo + " ]";
    }
    
}
