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
import modelo.Eventos;

/**
 *
 * @author rogelio
 */
@Stateless
public class EventosFacade extends AbstractFacade<Eventos> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventosFacade() {
        super(Eventos.class);
    }
    
    public List<Eventos> Consultar_activos(){
    Query consulta = em.createNamedQuery("Eventos.EventosActivos", Eventos.class);
    List<Eventos> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Eventos> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Eventos.EventosEliminados", Eventos.class);
    List<Eventos> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
