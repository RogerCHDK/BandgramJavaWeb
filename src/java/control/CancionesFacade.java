/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Canciones;

/**
 *
 * @author rogelio
 */
@Stateless
public class CancionesFacade extends AbstractFacade<Canciones> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CancionesFacade() {
        super(Canciones.class);
    }
    
    public List<Canciones> Consultar_activos(){
    Query consulta = em.createNamedQuery("Canciones.CancionesActivas", Canciones.class);
    List<Canciones> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista;
        }else{
            return null;
        }
    }
    
    public List<Canciones> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Canciones.CancionesEliminadas", Canciones.class);
    List<Canciones> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista;
        }else{
            return null;
        }
    }
    
    public List<Canciones> Consultar_por_artista(int artista_id){
    Query consulta = em.createNamedQuery("Canciones.findByartista", Canciones.class)
            .setParameter("id", artista_id);
    List<Canciones> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista;
        }else{
            return null;
        }
    }
    
}
