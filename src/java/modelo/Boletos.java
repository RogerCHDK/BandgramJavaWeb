/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rogelio
 */
@Entity
@Table(name = "boletos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boletos.findAll", query = "SELECT b FROM Boletos b")
    , @NamedQuery(name = "Boletos.findById", query = "SELECT b FROM Boletos b WHERE b.id = :id")
    , @NamedQuery(name = "Boletos.BoletosActivos", query = "SELECT b FROM Boletos b WHERE b.status = 1")
    , @NamedQuery(name = "Boletos.BoletosEliminados", query = "SELECT b FROM Boletos b WHERE b.status = 0")
    , @NamedQuery(name = "Boletos.findByPrecio", query = "SELECT b FROM Boletos b WHERE b.precio = :precio")
    , @NamedQuery(name = "Boletos.findByStatus", query = "SELECT b FROM Boletos b WHERE b.status = :status")})
public class Boletos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Eventos eventoId;
    @JoinColumn(name = "zona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Zonas zonaId;

    public Boletos() {
    }

    public Boletos(Long id) {
        this.id = id;
    }

    public Boletos(Long id, BigDecimal precio, int status) {
        this.id = id;
        this.precio = precio;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Eventos getEventoId() {
        return eventoId;
    }

    public void setEventoId(Eventos eventoId) {
        this.eventoId = eventoId;
    }

    public Zonas getZonaId() {
        return zonaId;
    }

    public void setZonaId(Zonas zonaId) {
        this.zonaId = zonaId;
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
        if (!(object instanceof Boletos)) {
            return false;
        }
        Boletos other = (Boletos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return precio.toString();
    }
    
}
