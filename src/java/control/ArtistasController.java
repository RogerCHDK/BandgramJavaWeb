package control;

import modelo.Artistas;
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

@Named("artistasController")
@SessionScoped
public class ArtistasController implements Serializable {

    @EJB
    private control.ArtistasFacade ejbFacade;
    private List<Artistas> items = null;
    private List<Artistas> items_eliminados = null;
    private Artistas selected;

    public List<Artistas> getItems_eliminados() {
        if (items_eliminados == null) {
            //items = getFacade().findAll();
            items_eliminados = ejbFacade.Consultar_eliminados();
        }
        return items_eliminados;
    }

    public void setItems_eliminados(List<Artistas> items_eliminados) {
        this.items_eliminados = items_eliminados;
    }

    public ArtistasController() {
    }

    public Artistas getSelected() {
        return selected;
    }

    public void setSelected(Artistas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ArtistasFacade getFacade() {
        return ejbFacade;
    }

    public Artistas prepareCreate() {
        selected = new Artistas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ArtistasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArtistasUpdated"));
    }

    public void destroy() {
        selected.setStatus(0);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ArtistasDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArtistasUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null; 
            items_eliminados = null;/// Invalidate list of items to trigger re-query.
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ArtistasDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArtistasUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;
            items_eliminados = null;// Invalidate list of items to trigger re-query.
        }
    }

    public List<Artistas> getItems() {
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

    public Artistas getArtistas(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Artistas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Artistas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Artistas.class)
    public static class ArtistasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArtistasController controller = (ArtistasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "artistasController");
            return controller.getArtistas(getKey(value));
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
            if (object instanceof Artistas) {
                Artistas o = (Artistas) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Artistas.class.getName()});
                return null;
            }
        }

    }

}
