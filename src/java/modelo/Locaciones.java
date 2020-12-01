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
@Table(name = "locaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locaciones.findAll", query = "SELECT l FROM Locaciones l")
    , @NamedQuery(name = "Locaciones.findById", query = "SELECT l FROM Locaciones l WHERE l.id = :id")
    , @NamedQuery(name = "Locaciones.findByCalle", query = "SELECT l FROM Locaciones l WHERE l.calle = :calle")
    , @NamedQuery(name = "Locaciones.findByColonia", query = "SELECT l FROM Locaciones l WHERE l.colonia = :colonia")
    , @NamedQuery(name = "Locaciones.findByNombre", query = "SELECT l FROM Locaciones l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Locaciones.findByStatus", query = "SELECT l FROM Locaciones l WHERE l.status = :status")})
public class Locaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "calle")
    private String calle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "colonia")
    private String colonia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locacionId")
    private Collection<Eventos> eventosCollection;
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados estadoId;
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paises paisId;
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Municipios municipioId;

    public Locaciones() {
    }

    public Locaciones(Long id) {
        this.id = id;
    }

    public Locaciones(Long id, String calle, String colonia, String nombre, int status) {
        this.id = id;
        this.calle = calle;
        this.colonia = colonia;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Eventos> getEventosCollection() {
        return eventosCollection;
    }

    public void setEventosCollection(Collection<Eventos> eventosCollection) {
        this.eventosCollection = eventosCollection;
    }

    public Estados getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estados estadoId) {
        this.estadoId = estadoId;
    }

    public Paises getPaisId() {
        return paisId;
    }

    public void setPaisId(Paises paisId) {
        this.paisId = paisId;
    }

    public Municipios getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Municipios municipioId) {
        this.municipioId = municipioId;
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
        if (!(object instanceof Locaciones)) {
            return false;
        }
        Locaciones other = (Locaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
