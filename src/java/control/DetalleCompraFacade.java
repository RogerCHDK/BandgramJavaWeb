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
import modelo.DetalleCompra;

/**
 *
 * @author rogelio
 */
@Stateless
public class DetalleCompraFacade extends AbstractFacade<DetalleCompra> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCompraFacade() {
        super(DetalleCompra.class);
    }
    
    public List<DetalleCompra> Consultar_activos(){
    Query consulta = em.createNamedQuery("DetalleCompra.DetalleCompraActivos", DetalleCompra.class);
    List<DetalleCompra> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<DetalleCompra> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("DetalleCompra.DetalleCompraEliminados", DetalleCompra.class);
    List<DetalleCompra> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
