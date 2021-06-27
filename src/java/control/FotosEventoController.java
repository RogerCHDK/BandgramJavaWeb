package control;

import modelo.FotosEvento;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.UploadedFile;

@Named("fotosEventoController")
@SessionScoped
public class FotosEventoController implements Serializable {

    @EJB
    private control.FotosEventoFacade ejbFacade;
    private List<FotosEvento> items = null;
    private List<FotosEvento> items_eliminados = null;
    private FotosEvento selected;
    private UploadedFile imagen;
    private String aux;

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }

    public List<FotosEvento> getItems_eliminados() {
        if (items_eliminados == null) {
            items_eliminados = ejbFacade.Consultar_eliminados();
        }
        return items_eliminados;
    }

    public void setItems_eliminados(List<FotosEvento> items_eliminados) {
        this.items_eliminados = items_eliminados;
    }

    public FotosEventoController() {
    }

    public FotosEvento getSelected() {
        return selected;
    }

    public void setSelected(FotosEvento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FotosEventoFacade getFacade() {
        return ejbFacade;
    }

    public FotosEvento prepareCreate() {
        selected = new FotosEvento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        selected.setFoto(aux);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FotosEventoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FotosEventoUpdated"));
    }

    public void destroy() {
        selected.setStatus(0);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FotosEventoDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FotosEventoUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null; 
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FotosEventoDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FotosEventoUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null; 
        }
    }
    
    public void NuevoDocumento(){
        System.out.println("Entre");
        System.out.println("MYME TYPE: "+ getImagen().getContentType());
        System.out.println("TAMAÑO: "+ getImagen().getSize());
        System.out.println("EXTENSIÓN PNG: "+ getImagen().getFileName().endsWith(".png"));
        System.out.println("EXTENSIÓN JPG: "+ getImagen().getFileName().endsWith(".jpg"));
        System.out.println("EXTENSIÓN GIF: "+ getImagen().getFileName().endsWith(".gif"));
        
        if (getImagen().getFileName().endsWith(".png")
                || getImagen().getFileName().endsWith(".jpg")
                ||getImagen().getFileName().endsWith(".gif")
                ) {
            if (SubirArchivo()) {
                create();
                aux= "";
            }else{
                FacesMessage mensaje = new FacesMessage("El archivo no es una imagen.");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                selected = null;
            }
        }
    }
    
    public Boolean SubirArchivo(){
        try {
            aux = "resources/fotoseventos";
            System.out.println("Ruta= "+aux);
            File archivo = new  File(JsfUtil.getPath() + aux); //obtengo la ruta de mi proyecto
            if (!archivo.exists()) { //sino existe la carpeta donde se van a guardar las imagenes, la crea
                archivo.mkdirs();
            }
            copiar_archivo(getImagen().getFileName(), getImagen().getInputstream());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void copiar_archivo(String nombre_archivo, InputStream in){
        try {
            aux = aux + "/" +nombre_archivo;
            System.out.println("se va a guardar");
            System.out.println("Ruta ok: "+aux);
            System.out.println("Ruta real :"+JsfUtil.getPath() + aux);
            OutputStream out = new FileOutputStream(new File(JsfUtil.getPath() + aux));
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = in.read(bytes)) != -1){
                out.write(bytes, 0, read);
            }
            System.out.println("Ya se guardo");
            aux = aux.substring(9);
            System.out.println("Ruta en la base "+ aux);
            in.close();
            out.flush();
            out.close();
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("No se guardo"));
        }
    }

    public List<FotosEvento> getItems() {
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

    public FotosEvento getFotosEvento(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<FotosEvento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FotosEvento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FotosEvento.class)
    public static class FotosEventoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FotosEventoController controller = (FotosEventoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fotosEventoController");
            return controller.getFotosEvento(getKey(value));
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
            if (object instanceof FotosEvento) {
                FotosEvento o = (FotosEvento) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FotosEvento.class.getName()});
                return null;
            }
        }

    }

}
