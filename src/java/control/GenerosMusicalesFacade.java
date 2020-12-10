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
    
    public List<GenerosMusicales> Consultar_activos(){
    Query consulta = em.createNamedQuery("GenerosMusicales.GenerosMusicalesActivos", GenerosMusicales.class);
    List<GenerosMusicales> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<GenerosMusicales> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("GenerosMusicales.GenerosMusicalesEliminados", GenerosMusicales.class);
    List<GenerosMusicales> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
