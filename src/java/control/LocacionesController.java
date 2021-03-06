package control;

import modelo.Locaciones;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Estados;
import modelo.Municipios;
import modelo.Paises;

@Named("locacionesController")
@SessionScoped
public class LocacionesController implements Serializable {
    
    @EJB
    private PaisesFacade paisesFacade;
    @EJB
    private EstadosFacade entidadesFacade;
    @EJB
    private MunicipiosFacade municipiosFacade;

    @EJB
    private control.LocacionesFacade ejbFacade;
    private List<Locaciones> items = null;
    private List<Locaciones> items_eliminados = null;
    private Locaciones selected;
    private List<Paises> listpaises;
    private List<Estados> listestados;
    private List<Municipios> listmunicipios;

    public List<Paises> getListpaises() {
        return listpaises;
    }

    public void setListpaises(List<Paises> listpaises) {
        this.listpaises = listpaises;
    }

    public List<Estados> getListestados() {
        return listestados;
    }

    public void setListestados(List<Estados> listestados) {
        this.listestados = listestados;
    }

    public List<Municipios> getListmunicipios() {
        return listmunicipios;
    }

    public void setListmunicipios(List<Municipios> listmunicipios) {
        this.listmunicipios = listmunicipios;
    }

    public List<Locaciones> getItems_eliminados() {
        if (items_eliminados == null) {
            items_eliminados = ejbFacade.Consultar_eliminados();
        }
        return items_eliminados;
    }

    public void setItems_eliminados(List<Locaciones> items_eliminados) {
        this.items_eliminados = items_eliminados;
    }

    public LocacionesController() {
    }

    public Locaciones getSelected() {
        return selected;
    }

    public void setSelected(Locaciones selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LocacionesFacade getFacade() {
        return ejbFacade;
    }

    public Locaciones prepareCreate() {
        selected = new Locaciones();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LocacionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LocacionesUpdated"));
    }

    public void destroy() {
        selected.setStatus(0);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LocacionesDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LocacionesUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LocacionesDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LocacionesUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
        }
    }
    
    @PostConstruct
    private void initialize(){
        listpaises = paisesFacade.findAll();
        listestados = entidadesFacade.findAll();
        listmunicipios = municipiosFacade.findAll();
    }
    
    public List<Estados> buscarEstado(AjaxBehaviorEvent event){
        listestados.clear();
        System.out.println(selected.getPaisId().getId().intValue());
        listestados = entidadesFacade.Buscar(selected.getPaisId().getId().intValue());
        FacesContext.getCurrentInstance().renderResponse();
        return listestados;
    }
    
    public List<Municipios> buscarMunicipio(AjaxBehaviorEvent event){
        listmunicipios.clear();
        System.out.println(selected.getEstadoId().getId().intValue());
        listmunicipios = municipiosFacade.Buscar(selected.getEstadoId().getId().intValue());
        FacesContext.getCurrentInstance().renderResponse();
        return listmunicipios;
    }

    public List<Locaciones> getItems() {
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

    public Locaciones getLocaciones(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Locaciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Locaciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Locaciones.class)
    public static class LocacionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LocacionesController controller = (LocacionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "locacionesController");
            return controller.getLocaciones(getKey(value));
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
            if (object instanceof Locaciones) {
                Locaciones o = (Locaciones) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Locaciones.class.getName()});
                return null;
            }
        }

    }

}
