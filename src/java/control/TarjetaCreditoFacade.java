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
import modelo.TarjetaCredito;

/**
 *
 * @author rogelio
 */
@Stateless
public class TarjetaCreditoFacade extends AbstractFacade<TarjetaCredito> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaCreditoFacade() {
        super(TarjetaCredito.class);
    }
    
    public List<TarjetaCredito> Consultar_activos(){
    Query consulta = em.createNamedQuery("TarjetaCredito.TarjetaCreditoActivas", TarjetaCredito.class);
    List<TarjetaCredito> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<TarjetaCredito> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("TarjetaCredito.TarjetaCreditoEliminadas", TarjetaCredito.class);
    List<TarjetaCredito> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
