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
import modelo.Artistas;

/**
 *
 * @author rogelio
 */
@Stateless
public class ArtistasFacade extends AbstractFacade<Artistas> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtistasFacade() {
        super(Artistas.class);
    }
    
    public List<Artistas> Consultar_activos(){
       
        Query consulta = em.createNamedQuery("Artistas.ArtistasActivos",Artistas.class);
         List<Artistas> lista = consulta.getResultList(); 
         if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Artistas> Consultar_eliminados(){
       
        Query consulta = em.createNamedQuery("Artistas.ArtistasEliminados",Artistas.class);
         List<Artistas> lista = consulta.getResultList(); 
         if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public Artistas Buscar(String usu, String pas){
        Query consulta = em.createNamedQuery("Artistas.buscar", Artistas.class)
                .setParameter("email", usu)
                .setParameter("password", pas);
        List<Artistas> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null; 
    }
    
}
