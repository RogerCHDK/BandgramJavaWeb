package control;

import modelo.Users;
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Estados;
import modelo.Municipios;
import modelo.Paises;

@Named("usersController")
@SessionScoped
public class UsersController implements Serializable {

    @EJB
    private PaisesFacade paisesFacade;
    @EJB
    private EstadosFacade entidadesFacade;
    @EJB
    private MunicipiosFacade municipiosFacade;
    
    @EJB
    private control.UsersFacade ejbFacade;
    private List<Users> items = null;
    private List<Users> items_eliminados = null;
    private List<Paises> listpaises;
    private List<Estados> listestados;
    private List<Municipios> listmunicipios;
    private int id_pais;
    private int id_entidad;
    private int id_municipio; 
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

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

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(int id_entidad) {
        this.id_entidad = id_entidad;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    

    public List<Users> getItems_eliminados() {
        if (items_eliminados == null) {
            items_eliminados = ejbFacade.Consultar_eliminados();
        }
        return items_eliminados;
    }

    public void setItems_eliminados(List<Users> items_eliminados) {
        this.items_eliminados = items_eliminados;
    }
    private Users selected;

    public UsersController() {
    }

    public Users getSelected() {
        return selected;
    }

    public void setSelected(Users selected) { 
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsersFacade getFacade() {
        return ejbFacade;
    }

    public Users prepareCreate() {
        selected = new Users();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        /*selected.setPaisId(miPais);
        System.out.println("Entreeeeeeee");
        System.out.println(selected.getNombre());
        System.out.println("Pais");
        System.out.println(miPais.getNombre());*/
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsersCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void create2() {
        selected.setStatus(1);
        /*selected.setPaisId(miPais);
        System.out.println("Entreeeeeeee");
        System.out.println(selected.getNombre());
        System.out.println("Pais");
        System.out.println(miPais.getNombre());*/
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsersCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario registrado correctamente",null));
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsersUpdated"));
    }

    public void destroy() {
        selected.setStatus(0);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsersDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsersUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsersDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsersUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
        }
    }

    public List<Users> getItems() {
        if (items == null) {
            items = ejbFacade.Consultar_activos();
        }
        return items;
    }
    
    @PostConstruct
    private void initialize(){
        listpaises = paisesFacade.findAll();
        listestados = entidadesFacade.findAll();
        listmunicipios = municipiosFacade.findAll();
        mensaje = "";
    }
    
    public List<Estados> buscarEstado(AjaxBehaviorEvent event){
        listestados.clear();
        listmunicipios.clear();
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
    
    public void buscarCorreo(AjaxBehaviorEvent event){
       Users aux = ejbFacade.BuscarEmail(selected.getEmail());
        if (aux != null) {
            mensaje = "Correo invalido";
        }else{
            mensaje = "Correo valido";
        }
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

    public Users getUsers(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Users> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Users> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Users.class)
    public static class UsersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsersController controller = (UsersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usersController");
            return controller.getUsers(getKey(value));
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
            if (object instanceof Users) {
                Users o = (Users) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Users.class.getName()});
                return null;
            }
        }

    }

}
