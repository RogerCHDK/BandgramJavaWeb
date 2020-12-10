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
import modelo.Locaciones;

/**
 *
 * @author rogelio
 */
@Stateless
public class LocacionesFacade extends AbstractFacade<Locaciones> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocacionesFacade() {
        super(Locaciones.class);
    }
    
    public List<Locaciones> Consultar_activos(){
    Query consulta = em.createNamedQuery("Locaciones.LocacionesActivas", Locaciones.class);
    List<Locaciones> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Locaciones> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Locaciones.LocacionesEliminadas", Locaciones.class);
    List<Locaciones> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
}
