package control;

import modelo.Videos;
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

@Named("videosController")
@SessionScoped
public class VideosController implements Serializable {

    @EJB
    private control.VideosFacade ejbFacade;
    private List<Videos> items = null;
    private List<Videos> items_eliminados = null;
    private List<Videos> items_artista = null;
    private HttpServletRequest httpservlet;
    private UploadedFile video;

    public UploadedFile getVideo() {
        return video;
    }

    public void setVideo(UploadedFile video) {
        this.video = video;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }
    private String aux;

    public List<Videos> getItems_artista() {
        if (items_artista == null) {
            httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Artistas artista = (Artistas) httpservlet.getSession().getAttribute("artista");
            items_artista = ejbFacade.Consultar_por_artista(artista.getId().intValue());
        }
        return items_artista;
    }

    public void setItems_artista(List<Videos> items_artista) {
        this.items_artista = items_artista;
    }

    public List<Videos> getItems_eliminados() {
        if (items_eliminados == null) {
            items_eliminados = ejbFacade.Consultar_eliminados();
        }
        return items_eliminados;
    }

    public void setItems_eliminados(List<Videos> items_eliminados) {
        this.items_eliminados = items_eliminados;
    }
    private Videos selected;

    public VideosController() {
    }

    public Videos getSelected() {
        return selected;
    }

    public void setSelected(Videos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VideosFacade getFacade() {
        return ejbFacade;
    }

    public Videos prepareCreate() {
        selected = new Videos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VideosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void create2() {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Artistas artista = (Artistas) httpservlet.getSession().getAttribute("artista");
        selected.setStatus(1);
        selected.setArtistaId(artista);
        selected.setRuta(aux);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VideosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        items_artista = null;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VideosUpdated"));
    }

    public void destroy() {
        selected.setStatus(0);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VideosDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VideosUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
            items_artista = null;
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VideosDeleted"));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VideosUpdated"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items_eliminados = null;
            items_artista = null;
        }
    }
    
    public void NuevoDocumento(){
        if (SubirArchivo()) {
            create();
            aux = "";
        }
    }
    
    public void NuevoDocumento2(){
        if (SubirArchivo()) {
            create2();
            aux = "";
        }
    }
    
    public Boolean SubirArchivo(){
        try {
            aux = "resources/videos";
            System.out.println("Ruta= "+aux);
            File archivo = new  File(JsfUtil.getPath() + aux); //obtengo la ruta de mi proyecto
            if (!archivo.exists()) { //sino existe la carpeta donde se van a guardar las imagenes, la crea
                archivo.mkdirs();
            }
            copiar_archivo(getVideo().getFileName(), getVideo().getInputstream());
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

    public List<Videos> getItems() {
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

    public Videos getVideos(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Videos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Videos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Videos.class)
    public static class VideosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VideosController controller = (VideosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "videosController");
            return controller.getVideos(getKey(value));
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
            if (object instanceof Videos) {
                Videos o = (Videos) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Videos.class.getName()});
                return null;
            }
        }

    }

}
