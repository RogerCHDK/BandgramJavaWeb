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
import modelo.Integrantes;

/**
 *
 * @author rogelio
 */
@Stateless
public class IntegrantesFacade extends AbstractFacade<Integrantes> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IntegrantesFacade() {
        super(Integrantes.class);
    }
    
    public List<Integrantes> Consultar_activos(){
    Query consulta = em.createNamedQuery("Integrantes.IntegrantesActivos", Integrantes.class);
    List<Integrantes> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Integrantes> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Integrantes.IntegrantesEliminados", Integrantes.class);
    List<Integrantes> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
