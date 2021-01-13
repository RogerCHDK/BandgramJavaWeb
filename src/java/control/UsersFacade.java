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
import modelo.Users;

/**
 *
 * @author rogelio
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "bandgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users Buscar(String usu, String pas){
        Query consulta = em.createNamedQuery("Usuarios.buscar", Users.class)
                .setParameter("email", usu)
                .setParameter("password", pas);
        List<Users> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }
    
    public Users BuscarEmail(String email){
         Query consulta = em.createNamedQuery("Users.findByEmail", Users.class)
                .setParameter("email", email); 
         List<Users>  usuario = consulta.getResultList();
         if (!usuario.isEmpty()) {
            return usuario.get(0);
        }
        return null;
         
    }
    
    public List<Users> Consultar_activos(){
    Query consulta = em.createNamedQuery("Users.UsersActivos", Users.class);
    List<Users> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
    public List<Users> Consultar_eliminados(){
    Query consulta = em.createNamedQuery("Users.UsersEliminados", Users.class);
    List<Users> lista = consulta.getResultList(); 
         
    if(!lista.isEmpty())
             return lista;
         else
            return null;
    }
    
}
