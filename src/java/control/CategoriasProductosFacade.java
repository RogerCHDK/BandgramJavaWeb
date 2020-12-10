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
import modelo.CategoriasProductos;

/**
 *
 * @author rogelio
 */
@Stateless
public class CategoriasProductosFacade extends AbstractFacade<CategoriasProductos> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasProductosFacade() {
        super(CategoriasProductos.class);
    }
    
    public List<CategoriasProductos> Consultar_activos(){
    Query consulta = em.createNamedQuery("CategoriasProductos.CategoriasActivas", CategoriasProductos.class);
    List<CategoriasProductos> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<CategoriasProductos> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("CategoriasProductos.CategoriasEliminadas", CategoriasProductos.class);
    List<CategoriasProductos> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
