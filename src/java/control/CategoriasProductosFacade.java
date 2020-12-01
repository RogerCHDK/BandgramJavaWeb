/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
