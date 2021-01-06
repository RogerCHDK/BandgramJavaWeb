/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author rogelio
 */
@Named(value = "ajax2")
@RequestScoped
public class Ajax2 {
    
    @EJB
    private PaisesFacade paisFacade;
    
    private String mensaje;
    int nuevo;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getNuevo() {
        return nuevo;
    }

    public void setNuevo(int nuevo) {
        this.nuevo = nuevo;
    }
    
    public void CambiaStatusPais(AjaxBehaviorEvent event){
        if (paisFacade.Cambia_status_p(nuevo,1).equals("ok")) {
            mensaje="ESTATUS ACTUALIZADO";
        }else{
            mensaje = "ERROR AL ACTUALIZAR";
        }
    }

    /**
     * Creates a new instance of Ajax2
     */
    public Ajax2() {
    }
    
}
