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
@Table(name = "tipos_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposUsuarios.findAll", query = "SELECT t FROM TiposUsuarios t")
    , @NamedQuery(name = "TiposUsuarios.findById", query = "SELECT t FROM TiposUsuarios t WHERE t.id = :id")
    , @NamedQuery(name = "TiposUsuarios.TiposUsuariosActivos", query = "SELECT t FROM TiposUsuarios t WHERE t.status = 1")
    , @NamedQuery(name = "TiposUsuarios.TiposUsuariosEliminados", query = "SELECT t FROM TiposUsuarios t WHERE t.status = 0")
    , @NamedQuery(name = "TiposUsuarios.findByNombre", query = "SELECT t FROM TiposUsuarios t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TiposUsuarios.findByNivel", query = "SELECT t FROM TiposUsuarios t WHERE t.nivel = :nivel")
    , @NamedQuery(name = "TiposUsuarios.findByStatus", query = "SELECT t FROM TiposUsuarios t WHERE t.status = :status")})
public class TiposUsuarios implements Serializable {

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
    @Column(name = "nivel")
    private int nivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoUsuario")
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoUsuario")
    private Collection<Artistas> artistasCollection;

    public TiposUsuarios() {
    }

    public TiposUsuarios(Long id) {
        this.id = id;
    }

    public TiposUsuarios(Long id, String nombre, int nivel, int status) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof TiposUsuarios)) {
            return false;
        }
        TiposUsuarios other = (TiposUsuarios) object;
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
