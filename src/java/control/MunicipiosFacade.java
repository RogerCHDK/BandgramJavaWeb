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
import modelo.Municipios;

/**
 *
 * @author rogelio
 */
@Stateless
public class MunicipiosFacade extends AbstractFacade<Municipios> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipiosFacade() {
        super(Municipios.class);
    }
    
    public List<Municipios> Consultar_activos(){
    Query consulta = em.createNamedQuery("Municipios.MunicipiosActivos", Municipios.class);
    List<Municipios> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Municipios> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Municipios.MunicipiosEliminados", Municipios.class);
    List<Municipios> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
