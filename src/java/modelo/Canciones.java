/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rogelio
 */
@Entity
@Table(name = "canciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canciones.findAll", query = "SELECT c FROM Canciones c")
    , @NamedQuery(name = "Canciones.findById", query = "SELECT c FROM Canciones c WHERE c.id = :id")
    , @NamedQuery(name = "Canciones.findByartista", query = "SELECT c FROM Canciones c WHERE c.artistaId.id = :id and c.status = 1")
    , @NamedQuery(name = "Canciones.CancionesActivas", query = "SELECT c FROM Canciones c WHERE c.status = 1")
    , @NamedQuery(name = "Canciones.CancionesEliminadas", query = "SELECT c FROM Canciones c WHERE c.status = 0")
    , @NamedQuery(name = "Canciones.findByNombre", query = "SELECT c FROM Canciones c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Canciones.findByRuta", query = "SELECT c FROM Canciones c WHERE c.ruta = :ruta")
    , @NamedQuery(name = "Canciones.findByAlbum", query = "SELECT c FROM Canciones c WHERE c.album = :album")
    , @NamedQuery(name = "Canciones.findByFoto", query = "SELECT c FROM Canciones c WHERE c.foto = :foto")
    , @NamedQuery(name = "Canciones.findByStatus", query = "SELECT c FROM Canciones c WHERE c.status = :status")})
public class Canciones implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 255)
    @Column(name = "album")
    private String album;
    @Size(max = 255)
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "artista_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Artistas artistaId;
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GenerosMusicales generoId;

    public Canciones() {
    }

    public Canciones(Long id) {
        this.id = id;
    }

    public Canciones(Long id, String nombre, String ruta, int status) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Artistas getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Artistas artistaId) {
        this.artistaId = artistaId;
    }

    public GenerosMusicales getGeneroId() {
        return generoId;
    }

    public void setGeneroId(GenerosMusicales generoId) {
        this.generoId = generoId;
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
        if (!(object instanceof Canciones)) {
            return false;
        }
        Canciones other = (Canciones) object;
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
