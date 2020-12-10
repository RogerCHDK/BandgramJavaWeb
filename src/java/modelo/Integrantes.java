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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rogelio
 */
@Entity
@Table(name = "integrantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Integrantes.findAll", query = "SELECT i FROM Integrantes i")
    , @NamedQuery(name = "Integrantes.findById", query = "SELECT i FROM Integrantes i WHERE i.id = :id")
    , @NamedQuery(name = "Integrantes.IntegrantesActivos", query = "SELECT i FROM Integrantes i WHERE i.status = 1")
    , @NamedQuery(name = "Integrantes.IntegrantesEliminados", query = "SELECT i FROM Integrantes i WHERE i.status = 0")
    , @NamedQuery(name = "Integrantes.findByStatus", query = "SELECT i FROM Integrantes i WHERE i.status = :status")})
public class Integrantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "artista_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Artistas artistaId;
    @JoinColumn(name = "banda_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bandas bandaId;

    public Integrantes() {
    }

    public Integrantes(Long id) {
        this.id = id;
    }

    public Integrantes(Long id, int status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Bandas getBandaId() {
        return bandaId;
    }

    public void setBandaId(Bandas bandaId) {
        this.bandaId = bandaId;
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
        if (!(object instanceof Integrantes)) {
            return false;
        }
        Integrantes other = (Integrantes) object;
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
