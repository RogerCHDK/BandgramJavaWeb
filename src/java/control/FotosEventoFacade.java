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
import modelo.FotosEvento;

/**
 *
 * @author rogelio
 */
@Stateless
public class FotosEventoFacade extends AbstractFacade<FotosEvento> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FotosEventoFacade() {
        super(FotosEvento.class);
    }
    
    public List<FotosEvento> Consultar_activos(){
    Query consulta = em.createNamedQuery("FotosEvento.FotosEventoActivos", FotosEvento.class);
    List<FotosEvento> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<FotosEvento> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("FotosEvento.FotosEventoEliminados", FotosEvento.class);
    List<FotosEvento> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
}
