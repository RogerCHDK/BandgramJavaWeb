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
import modelo.Bandas;

/**
 *
 * @author rogelio
 */
@Stateless
public class BandasFacade extends AbstractFacade<Bandas> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BandasFacade() {
        super(Bandas.class);
    }
    
    public List<Bandas> Consultar_eliminados(){
       
        Query consulta = em.createNamedQuery("Bandas.BandasEliminados",Bandas.class);
         List<Bandas> lista = consulta.getResultList(); 
         if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Bandas> Consultar_activos(){
       
        Query consulta = em.createNamedQuery("Bandas.BandasActivos",Bandas.class);
         List<Bandas> lista = consulta.getResultList(); 
         if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Bandas> Consultar_por_artista(int artista_id){
       
        Query consulta = em.createNamedQuery("Bandas.findByartista",Bandas.class)
                .setParameter("id", artista_id);
         List<Bandas> lista = consulta.getResultList(); 
         if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
