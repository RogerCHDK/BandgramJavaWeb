/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Users;

/**
 *
 * @author rogelio
 */
@Named(value = "login")
@SessionScoped
public class login implements Serializable {

    /**
     * Creates a new instance of login
     */
    
    
    @EJB
    private UsersFacade usufacade;
    
    private HttpServletRequest httpservlet;
    
    private String username;
    private String password;
    private Users usuautenticado;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUsuautenticado() {
        return usuautenticado;
    }

    public void setUsuautenticado(Users usuautenticado) {
        this.usuautenticado = usuautenticado;
    }
    
    public login() {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public void Acceso() throws IOException{
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuautenticado = usufacade.Buscar(username,password);
        if (usuautenticado != null) {
            httpservlet.getSession().setAttribute("email", usuautenticado.getEmail());
            httpservlet.getSession().setAttribute("nombre_com", usuautenticado.getNombre()+" "+usuautenticado.getApPaterno()+" "+usuautenticado.getApMaterno());
            httpservlet.getSession().setAttribute("nivel_usu", usuautenticado.getTipoUsuario().getNivel());
            httpservlet.getSession().setAttribute("usuario", usuautenticado);
            switch(usuautenticado.getTipoUsuario().getNivel()){
                case 1:
                    System.out.println("entreeeeeeeee");
                    System.out.println(FacesContext.getCurrentInstance().getExternalContext());
                    FacesContext.getCurrentInstance().getExternalContext().redirect("bandindex.xhtml");
                    break;
                default:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("otro.xhtml");
                    break;
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o password incorrecto",null));
        }
    }
    
    public void cerrarSesion(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/bandgram/faces/login.xhtml");
        } catch (Exception e) {
        }
        
    }
    
    public void verificaSesionynivel(int nivel) throws IOException{
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Users usu = (Users) httpservlet.getSession().getAttribute("usuario");
        if (usu != null) {
            if (usu.getTipoUsuario().getNivel() != nivel) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        }else{
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/bandgram/faces/login.xhtml");
        }
    }
    
}
