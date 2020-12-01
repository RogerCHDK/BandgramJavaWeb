/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rogelio
 */
@Entity
@Table(name = "eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventos.findAll", query = "SELECT e FROM Eventos e")
    , @NamedQuery(name = "Eventos.findById", query = "SELECT e FROM Eventos e WHERE e.id = :id")
    , @NamedQuery(name = "Eventos.findByFechaCreacion", query = "SELECT e FROM Eventos e WHERE e.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Eventos.findByFechaInicio", query = "SELECT e FROM Eventos e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Eventos.findByHoraInicio", query = "SELECT e FROM Eventos e WHERE e.horaInicio = :horaInicio")
    , @NamedQuery(name = "Eventos.findByStatus", query = "SELECT e FROM Eventos e WHERE e.status = :status")})
public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventoId")
    private Collection<FotosEvento> fotosEventoCollection;
    @JoinColumn(name = "locacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Locaciones locacionId;
    @JoinColumn(name = "artista_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Artistas artistaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventoId")
    private Collection<Boletos> boletosCollection;

    public Eventos() {
    }

    public Eventos(Long id) {
        this.id = id;
    }

    public Eventos(Long id, String descripcion, Date fechaCreacion, Date fechaInicio, Date horaInicio, int status) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<FotosEvento> getFotosEventoCollection() {
        return fotosEventoCollection;
    }

    public void setFotosEventoCollection(Collection<FotosEvento> fotosEventoCollection) {
        this.fotosEventoCollection = fotosEventoCollection;
    }

    public Locaciones getLocacionId() {
        return locacionId;
    }

    public void setLocacionId(Locaciones locacionId) {
        this.locacionId = locacionId;
    }

    public Artistas getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Artistas artistaId) {
        this.artistaId = artistaId;
    }

    @XmlTransient
    public Collection<Boletos> getBoletosCollection() {
        return boletosCollection;
    }

    public void setBoletosCollection(Collection<Boletos> boletosCollection) {
        this.boletosCollection = boletosCollection;
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
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
