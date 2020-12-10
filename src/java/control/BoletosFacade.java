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
import modelo.Boletos;

/**
 *
 * @author rogelio
 */
@Stateless
public class BoletosFacade extends AbstractFacade<Boletos> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoletosFacade() {
        super(Boletos.class);
    }
    
    public List<Boletos> Consultar_activos(){
       
        Query consulta = em.createNamedQuery("Boletos.BoletosActivos",Boletos.class);
         List<Boletos> lista = consulta.getResultList(); 
         if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Boletos> Consultar_eliminados(){
       
        Query consulta = em.createNamedQuery("Boletos.BoletosEliminados",Boletos.class);
         List<Boletos> lista = consulta.getResultList(); 
         if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
