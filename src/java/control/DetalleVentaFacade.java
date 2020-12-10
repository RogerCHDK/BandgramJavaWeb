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
import modelo.DetalleVenta;

/**
 *
 * @author rogelio
 */
@Stateless
public class DetalleVentaFacade extends AbstractFacade<DetalleVenta> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleVentaFacade() {
        super(DetalleVenta.class);
    }
    
    public List<DetalleVenta> Consultar_activos(){
    Query consulta = em.createNamedQuery("DetalleVenta.DetalleVentaActivos", DetalleVenta.class);
    List<DetalleVenta> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<DetalleVenta> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("DetalleVenta.DetalleVentaEliminados", DetalleVenta.class);
    List<DetalleVenta> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
