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
import modelo.FotosProducto;

/**
 *
 * @author rogelio
 */
@Stateless
public class FotosProductoFacade extends AbstractFacade<FotosProducto> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FotosProductoFacade() {
        super(FotosProducto.class);
    }
    
    public List<FotosProducto> Consultar_activos(){
    Query consulta = em.createNamedQuery("FotosProducto.FotosProductosActivos", FotosProducto.class);
    List<FotosProducto> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<FotosProducto> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("FotosProducto.FotosProductosEliminados", FotosProducto.class);
    List<FotosProducto> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
}
