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
import modelo.Zonas;

/**
 *
 * @author rogelio
 */
@Stateless
public class ZonasFacade extends AbstractFacade<Zonas> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZonasFacade() {
        super(Zonas.class);
    }
    
    public List<Zonas> Consultar_activos(){
    Query consulta = em.createNamedQuery("Zonas.ZonasActivas", Zonas.class);
    List<Zonas> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Zonas> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Zonas.ZonasEliminadas", Zonas.class);
    List<Zonas> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
