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
import modelo.Estados;

/**
 *
 * @author rogelio
 */
@Stateless
public class EstadosFacade extends AbstractFacade<Estados> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosFacade() {
        super(Estados.class);
    }
    
    public List<Estados> Consultar_activos(){
    Query consulta = em.createNamedQuery("Estados.EstadosActivos", Estados.class);
    List<Estados> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Estados> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Estados.EstadosEliminados", Estados.class);
    List<Estados> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
}
