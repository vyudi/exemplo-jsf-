/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inscricao.persistence.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Vitor Yudi Hansen
 */
@Entity
@Table(name = "cliente_periodo")
@NamedQueries({
    @NamedQuery(name = "ClientePeriodo.findAll", query = "SELECT c FROM ClientePeriodo c")})
public class ClientePeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClientePeriodoPK clientePeriodoPK;

    public ClientePeriodo() {
    }

    public ClientePeriodo(ClientePeriodoPK clientePeriodoPK) {
        this.clientePeriodoPK = clientePeriodoPK;
    }

    public ClientePeriodo(String clienteCpf, String periodoPeriodo) {
        this.clientePeriodoPK = new ClientePeriodoPK(clienteCpf, periodoPeriodo);
    }

    public ClientePeriodoPK getClientePeriodoPK() {
        return clientePeriodoPK;
    }

    public void setClientePeriodoPK(ClientePeriodoPK clientePeriodoPK) {
        this.clientePeriodoPK = clientePeriodoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientePeriodoPK != null ? clientePeriodoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientePeriodo)) {
            return false;
        }
        ClientePeriodo other = (ClientePeriodo) object;
        if ((this.clientePeriodoPK == null && other.clientePeriodoPK != null) || (this.clientePeriodoPK != null && !this.clientePeriodoPK.equals(other.clientePeriodoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inscricao.persistence.entity.ClientePeriodo[ clientePeriodoPK=" + clientePeriodoPK + " ]";
    }
    
}
