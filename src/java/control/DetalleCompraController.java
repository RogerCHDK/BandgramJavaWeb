package control;

import modelo.DetalleCompra;
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

@Named("detalleCompraController")
@SessionScoped
public class DetalleCompraController implements Serializable {

    @EJB
    private control.DetalleCompraFacade ejbFacade;
    private List<DetalleCompra> items = null;
    private List<DetalleCompra> items_eliminados = null;

    public List<DetalleCompra> getItems_eliminados() {
        if (items_eliminados == null) {
            items_eliminados = ejbFacade.Consultar_eliminados();
        }
        return items_eliminados;
    }

    public void setItems_eliminados(List<DetalleCompra> items_eliminados) {
        this.items_eliminados = items_eliminados;
    }
    private DetalleCompra selected;

    public DetalleCompraController() {
    }

    public DetalleCompra getSelected() {
        return selected;
    }

    public void setSelected(DetalleCompra selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetalleCompraFacade getFacade() {
        return ejbFacade;
    }

    public DetalleCompra prepareCreate() {
        selected = new DetalleCompra();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetalleCompraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleCompraUpdated"));
    }

    public void destroy() {
        selected.setStatus(0);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetalleCompraDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleCompraUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetalleCompraDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleCompraUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
        }
    }

    public List<DetalleCompra> getItems() {
        if (items == null) {
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

    public DetalleCompra getDetalleCompra(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DetalleCompra> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetalleCompra> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetalleCompra.class)
    public static class DetalleCompraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleCompraController controller = (DetalleCompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleCompraController");
            return controller.getDetalleCompra(getKey(value));
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
            if (object instanceof DetalleCompra) {
                DetalleCompra o = (DetalleCompra) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleCompra.class.getName()});
                return null;
            }
        }

    }

}
