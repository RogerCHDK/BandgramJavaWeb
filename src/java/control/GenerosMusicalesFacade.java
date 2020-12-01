/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.GenerosMusicales;

/**
 *
 * @author rogelio
 */
@Stateless
public class GenerosMusicalesFacade extends AbstractFacade<GenerosMusicales> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenerosMusicalesFacade() {
        super(GenerosMusicales.class);
    }
    
}
