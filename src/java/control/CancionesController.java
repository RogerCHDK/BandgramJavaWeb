package control;

import modelo.Canciones;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("cancionesController")
@SessionScoped
public class CancionesController implements Serializable {

    @EJB
    private control.CancionesFacade ejbFacade;
    private List<Canciones> items = null;
    private List<Canciones> items_eliminados = null;
    private Canciones selected;

    public List<Canciones> getItems_eliminados() {
        if (items_eliminados == null) {
            items_eliminados = ejbFacade.Consultar_eliminados();
        }
        return items_eliminados;
    }

    public void setItems_eliminados(List<Canciones> items_eliminados) {
        this.items_eliminados = items_eliminados;
    }

    public CancionesController() {
    }

    public Canciones getSelected() {
        return selected;
    }

    public void setSelected(Canciones selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CancionesFacade getFacade() {
        return ejbFacade;
    }

    public Canciones prepareCreate() {
        selected = new Canciones();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CancionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CancionesUpdated"));
    }

    public void destroy() {
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CancionesUpdated"));
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CancionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;
            items_eliminados = null;// Invalidate list of items to trigger re-query.
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CancionesUpdated"));
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CancionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;
            items_eliminados = null;// Invalidate list of items to trigger re-query.
        }
    }

    public List<Canciones> getItems() {
        if (items == null) {
            //items = getFacade().findAll();
            items = ejbFacade.Consultar_activos();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Canciones getCanciones(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Canciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Canciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Canciones.class)
    public static class CancionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CancionesController controller = (CancionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cancionesController");
            return controller.getCanciones(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Canciones) {
                Canciones o = (Canciones) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Canciones.class.getName()});
                return null;
            }
        }

    }

}
