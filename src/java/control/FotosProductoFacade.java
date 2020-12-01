/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
