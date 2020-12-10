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
import javax.persistence.Lob;
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
@Table(name = "bandas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bandas.findAll", query = "SELECT b FROM Bandas b")
    , @NamedQuery(name = "Bandas.findById", query = "SELECT b FROM Bandas b WHERE b.id = :id")
    , @NamedQuery(name = "Bandas.BandasActivos", query = "SELECT b FROM Bandas b WHERE b.status = 1")
    , @NamedQuery(name = "Bandas.BandasEliminados", query = "SELECT b FROM Bandas b WHERE b.status = 0")
    , @NamedQuery(name = "Bandas.findByNombre", query = "SELECT b FROM Bandas b WHERE b.nombre = :nombre")
    , @NamedQuery(name = "Bandas.findByFoto", query = "SELECT b FROM Bandas b WHERE b.foto = :foto")
    , @NamedQuery(name = "Bandas.findByStatus", query = "SELECT b FROM Bandas b WHERE b.status = :status")})
public class Bandas implements Serializable {

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
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "biografia")
    private String biografia;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bandaId")
    private Collection<Integrantes> integrantesCollection;

    public Bandas() {
    }

    public Bandas(Long id) {
        this.id = id;
    }

    public Bandas(Long id, String nombre, String biografia, int status) {
        this.id = id;
        this.nombre = nombre;
        this.biografia = biografia;
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
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

    @XmlTransient
    public Collection<Integrantes> getIntegrantesCollection() {
        return integrantesCollection;
    }

    public void setIntegrantesCollection(Collection<Integrantes> integrantesCollection) {
        this.integrantesCollection = integrantesCollection;
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
        if (!(object instanceof Bandas)) {
            return false;
        }
        Bandas other = (Bandas) object;
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
