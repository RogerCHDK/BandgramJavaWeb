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
import modelo.TiposUsuarios;

/**
 *
 * @author rogelio
 */
@Stateless
public class TiposUsuariosFacade extends AbstractFacade<TiposUsuarios> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposUsuariosFacade() {
        super(TiposUsuarios.class);
    }
    
    public List<TiposUsuarios> Consultar_activos(){
    Query consulta = em.createNamedQuery("TiposUsuarios.TiposUsuariosActivos", TiposUsuarios.class);
    List<TiposUsuarios> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<TiposUsuarios> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("TiposUsuarios.TiposUsuariosEliminados", TiposUsuarios.class);
    List<TiposUsuarios> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
