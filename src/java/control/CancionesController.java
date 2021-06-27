package control;

import modelo.Canciones;
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
import javax.servlet.http.HttpServletRequest;
import modelo.Artistas;
import org.primefaces.model.UploadedFile;

@Named("cancionesController")
@SessionScoped
public class CancionesController implements Serializable {

    @EJB
    private control.CancionesFacade ejbFacade;
    private List<Canciones> items = null;
    private List<Canciones> items_eliminados = null;
    private List<Canciones> items_artista = null;
    private Canciones selected;
     private HttpServletRequest httpservlet;

    public List<Canciones> getItems_artista() {
        if (items_artista == null) {
            httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Artistas artista = (Artistas) httpservlet.getSession().getAttribute("artista");
            items_artista = ejbFacade.Consultar_por_artista(artista.getId().intValue());
        }
        return items_artista;
    }

    public void setItems_artista(List<Canciones> items_artista) {
        this.items_artista = items_artista;
    }
    private UploadedFile cancion;
    private String aux;
    private UploadedFile imagen;
    private String aux2;

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public String getAux2() {
        return aux2;
    }

    public void setAux2(String aux2) {
        this.aux2 = aux2;
    }

    public UploadedFile getCancion() {
        return cancion;
    }

    public void setCancion(UploadedFile cancion) {
        this.cancion = cancion;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }

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
        selected.setRuta(aux);
      selected.setFoto(aux2);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CancionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void create2() {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Artistas artista = (Artistas) httpservlet.getSession().getAttribute("artista");
        selected.setStatus(1);
        selected.setRuta(aux);
          selected.setFoto(aux2);
        selected.setArtistaId(artista);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CancionesCreated"));
        items_artista = null;
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
            items_eliminados = null;
            items_artista = null;// Invalidate list of items to trigger re-query.
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
            items_artista = null;
        }
    }
    
    public void NuevoDocumento(){
        if (SubirArchivo()) {
            create();
            aux = "";
            aux2 = "";
        }
    }
    public void NuevoDocumento2(){
        if (SubirArchivo()) {
            create2();
            aux = "";
            aux2 = "";
        }
    }
    
    public Boolean SubirArchivo(){
        try {
            aux = "resources/musica";
            aux2 = "resources/fotosmusica";
            //System.out.println("Ruta= "+aux);
            File archivo = new  File(JsfUtil.getPath() + aux); //obtengo la ruta de mi proyecto
            File archivo2 = new  File(JsfUtil.getPath() + aux2);
            if (!archivo.exists()) { //sino existe la carpeta donde se van a guardar las imagenes, la crea
                archivo.mkdirs();
            }
            if (!archivo2.exists()) { //sino existe la carpeta donde se van a guardar las imagenes, la crea
                archivo2.mkdirs();
            }
            copiar_archivo(getCancion().getFileName(), getCancion().getInputstream());
            copiar_archivo2(getImagen().getFileName(), getImagen().getInputstream());
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
    
    public void copiar_archivo2(String nombre_archivo, InputStream in){
        try {
            aux2 = aux2 + "/" +nombre_archivo;
            System.out.println("se va a guardar");
            System.out.println("Ruta ok: "+aux2);
            System.out.println("Ruta real :"+JsfUtil.getPath() + aux2);
            OutputStream out = new FileOutputStream(new File(JsfUtil.getPath() + aux2));
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = in.read(bytes)) != -1){
                out.write(bytes, 0, read);
            }
            System.out.println("Ya se guardo");
            aux2 = aux2.substring(9);
            System.out.println("Ruta en la base "+ aux2);
            in.close();
            out.flush();
            out.close();
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("No se guardo"));
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
