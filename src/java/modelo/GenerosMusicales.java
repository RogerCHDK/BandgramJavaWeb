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
@Table(name = "generos_musicales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenerosMusicales.findAll", query = "SELECT g FROM GenerosMusicales g")
    , @NamedQuery(name = "GenerosMusicales.findById", query = "SELECT g FROM GenerosMusicales g WHERE g.id = :id")
    , @NamedQuery(name = "GenerosMusicales.findByNombre", query = "SELECT g FROM GenerosMusicales g WHERE g.nombre = :nombre")
    , @NamedQuery(name = "GenerosMusicales.findByStatus", query = "SELECT g FROM GenerosMusicales g WHERE g.status = :status")})
public class GenerosMusicales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoId")
    private Collection<Bandas> bandasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoId")
    private Collection<Canciones> cancionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoId")
    private Collection<Artistas> artistasCollection;

    public GenerosMusicales() {
    }

    public GenerosMusicales(Long id) {
        this.id = id;
    }

    public GenerosMusicales(Long id, String nombre, int status) {
        this.id = id;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public Collection<Bandas> getBandasCollection() {
        return bandasCollection;
    }

    public void setBandasCollection(Collection<Bandas> bandasCollection) {
        this.bandasCollection = bandasCollection;
    }

    @XmlTransient
    public Collection<Canciones> getCancionesCollection() {
        return cancionesCollection;
    }

    public void setCancionesCollection(Collection<Canciones> cancionesCollection) {
        this.cancionesCollection = cancionesCollection;
    }

    @XmlTransient
    public Collection<Artistas> getArtistasCollection() {
        return artistasCollection;
    }

    public void setArtistasCollection(Collection<Artistas> artistasCollection) {
        this.artistasCollection = artistasCollection;
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
        if (!(object instanceof GenerosMusicales)) {
            return false;
        }
        GenerosMusicales other = (GenerosMusicales) object;
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
