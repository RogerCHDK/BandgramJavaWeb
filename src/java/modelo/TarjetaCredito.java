/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rogelio
 */
@Entity
@Table(name = "tarjeta_credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarjetaCredito.findAll", query = "SELECT t FROM TarjetaCredito t")
    , @NamedQuery(name = "TarjetaCredito.findById", query = "SELECT t FROM TarjetaCredito t WHERE t.id = :id")
        , @NamedQuery(name = "TarjetaCredito.findByusuario", query = "SELECT t FROM TarjetaCredito t WHERE t.userId.id = :id and t.status = 1")
    , @NamedQuery(name = "TarjetaCredito.TarjetaCreditoActivas", query = "SELECT t FROM TarjetaCredito t WHERE t.status = 1")
    , @NamedQuery(name = "TarjetaCredito.TarjetaCreditoEliminadas", query = "SELECT t FROM TarjetaCredito t WHERE t.status = 0")
    , @NamedQuery(name = "TarjetaCredito.findByTitular", query = "SELECT t FROM TarjetaCredito t WHERE t.titular = :titular")
    , @NamedQuery(name = "TarjetaCredito.findByNumero", query = "SELECT t FROM TarjetaCredito t WHERE t.numero = :numero")
    , @NamedQuery(name = "TarjetaCredito.findByMes", query = "SELECT t FROM TarjetaCredito t WHERE t.mes = :mes")
    , @NamedQuery(name = "TarjetaCredito.findByAnio", query = "SELECT t FROM TarjetaCredito t WHERE t.anio = :anio")
    , @NamedQuery(name = "TarjetaCredito.findByStatus", query = "SELECT t FROM TarjetaCredito t WHERE t.status = :status")})
public class TarjetaCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titular")
    private String titular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private long numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjetaCreditoId")
    private Collection<Ventas> ventasCollection;

    public TarjetaCredito() {
    }

    public TarjetaCredito(Long id) {
        this.id = id;
    }

    public TarjetaCredito(Long id, String titular, long numero, int mes, int anio, int status) {
        this.id = id;
        this.titular = titular;
        this.numero = numero;
        this.mes = mes;
        this.anio = anio;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaCredito)) {
            return false;
        }
        TarjetaCredito other = (TarjetaCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titular;
    }
    
}
