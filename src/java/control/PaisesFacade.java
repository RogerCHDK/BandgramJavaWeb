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
import modelo.Paises;

/**
 *
 * @author rogelio
 */
@Stateless
public class PaisesFacade extends AbstractFacade<Paises> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisesFacade() {
        super(Paises.class);
    }
    
    public String  Cambia_status_p(int nuevo, int idpais){
        Query consulta = em.createNamedQuery("Paises.cambia1",Paises.class)
                .setParameter("status_p", nuevo)
                .setParameter("id_p", idpais);
        consulta.executeUpdate();
         return "ok";
    }
    
    
    public List<Paises> Consultar_activos(){
    Query consulta = em.createNamedQuery("Paises.PaisesActivos", Paises.class);
    List<Paises> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Paises> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Paises.PaisesEliminados", Paises.class);
    List<Paises> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
