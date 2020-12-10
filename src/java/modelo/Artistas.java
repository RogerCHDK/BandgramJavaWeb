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
@Table(name = "artistas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artistas.findAll", query = "SELECT a FROM Artistas a")
    , @NamedQuery(name = "Artistas.findById", query = "SELECT a FROM Artistas a WHERE a.id = :id")
    , @NamedQuery(name = "Artistas.ArtistasActivos", query = "SELECT a FROM Artistas a WHERE a.status = 1") 
    , @NamedQuery(name = "Artistas.ArtistasEliminados", query = "SELECT a FROM Artistas a WHERE a.status = 0")     
    , @NamedQuery(name = "Artistas.findByNombre", query = "SELECT a FROM Artistas a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Artistas.findByApPaterno", query = "SELECT a FROM Artistas a WHERE a.apPaterno = :apPaterno")
    , @NamedQuery(name = "Artistas.findByApMaterno", query = "SELECT a FROM Artistas a WHERE a.apMaterno = :apMaterno")
    , @NamedQuery(name = "Artistas.findByFoto", query = "SELECT a FROM Artistas a WHERE a.foto = :foto")
    , @NamedQuery(name = "Artistas.findByEmail", query = "SELECT a FROM Artistas a WHERE a.email = :email")
    , @NamedQuery(name = "Artistas.findByPassword", query = "SELECT a FROM Artistas a WHERE a.password = :password")
    , @NamedQuery(name = "Artistas.findByStatus", query = "SELECT a FROM Artistas a WHERE a.status = :status")})
public class Artistas implements Serializable {

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
    @Column(name = "ap_paterno")
    private String apPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ap_materno")
    private String apMaterno;
    @Size(max = 255)
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "biografia")
    private String biografia;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistaId")
    private Collection<Bandas> bandasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistaId")
    private Collection<Canciones> cancionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistaId")
    private Collection<Eventos> eventosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistaId")
    private Collection<Videos> videosCollection;
    @JoinColumn(name = "tipo_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TiposUsuarios tipoUsuario;
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GenerosMusicales generoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistaId")
    private Collection<Compras> comprasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistaId")
    private Collection<Integrantes> integrantesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistaId")
    private Collection<Productos> productosCollection;

    public Artistas() {
    }

    public Artistas(Long id) {
        this.id = id;
    }

    public Artistas(Long id, String nombre, String apPaterno, String apMaterno, String biografia, String email, String password, int status) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.biografia = biografia;
        this.email = email;
        this.password = password;
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

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public Collection<Eventos> getEventosCollection() {
        return eventosCollection;
    }

    public void setEventosCollection(Collection<Eventos> eventosCollection) {
        this.eventosCollection = eventosCollection;
    }

    @XmlTransient
    public Collection<Videos> getVideosCollection() {
        return videosCollection;
    }

    public void setVideosCollection(Collection<Videos> videosCollection) {
        this.videosCollection = videosCollection;
    }

    public TiposUsuarios getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TiposUsuarios tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public GenerosMusicales getGeneroId() {
        return generoId;
    }

    public void setGeneroId(GenerosMusicales generoId) {
        this.generoId = generoId;
    }

    @XmlTransient
    public Collection<Compras> getComprasCollection() {
        return comprasCollection;
    }

    public void setComprasCollection(Collection<Compras> comprasCollection) {
        this.comprasCollection = comprasCollection;
    }

    @XmlTransient
    public Collection<Integrantes> getIntegrantesCollection() {
        return integrantesCollection;
    }

    public void setIntegrantesCollection(Collection<Integrantes> integrantesCollection) {
        this.integrantesCollection = integrantesCollection;
    }

    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
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
        if (!(object instanceof Artistas)) {
            return false;
        }
        Artistas other = (Artistas) object;
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
