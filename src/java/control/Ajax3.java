/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import modelo.Estados;
import modelo.Municipios;
import modelo.Paises;

/**
 *
 * @author rogelio
 */
@Named(value = "ajax3")
@RequestScoped
public class Ajax3 {

    @EJB
    private PaisesFacade paisesFacade;
    @EJB
    private EstadosFacade entidadesFacade;
    @EJB
    private MunicipiosFacade municipiosFacade;
    
    private int id_pais;
    private int id_entidad;
    private int id_municipio; 
    private List<SelectItem> listpaises;
    private Paises pais;

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
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

    public List<SelectItem> getListpaises() {
        return listpaises;
    }

    public void setListpaises(List<SelectItem> listpaises) {
        this.listpaises = listpaises;
    }

    public List<SelectItem> getListentidades() {
        return listentidades;
    }

    public void setListentidades(List<SelectItem> listentidades) {
        this.listentidades = listentidades;
    }

    public List<SelectItem> getListmunicipios() {
        return listmunicipios;
    }

    public void setListmunicipios(List<SelectItem> listmunicipios) {
        this.listmunicipios = listmunicipios;
    }
    private List<SelectItem> listentidades;
    private List<SelectItem> listmunicipios;
    
    public Ajax3() {
    }
    @PostConstruct
    private void initialize(){
        listpaises = new ArrayList<SelectItem>();
        List<Paises> lp = paisesFacade.findAll();
        listpaises.clear();
        for (Paises p: lp) {
            SelectItem paises_item = new SelectItem(p.getId(),p.getNombre());
            listpaises.add(paises_item);
        }
        
        listentidades = new ArrayList<SelectItem>();
        List<Estados> le = entidadesFacade.findAll();
        listentidades.clear();
        for (Estados e : le) {
            SelectItem entidad_item = new SelectItem(e.getId(),e.getNombre());
            listentidades.add(entidad_item);
        }
        
        listmunicipios = new ArrayList<SelectItem>();
        List<Municipios> lm = municipiosFacade.findAll();
        listmunicipios.clear();
        for (Municipios m : lm) {
            SelectItem municipio_item = new SelectItem(m.getId(),m.getNombre());
            listmunicipios.add(municipio_item);
        }
        
    }
    
    public List<SelectItem> buscaentidades(final AjaxBehaviorEvent event){
        
        setPais(paisesFacade.BuscarId(id_pais));
        System.out.println("wwwwwwwwwwww");
        System.out.println(pais.getNombre());
        listentidades = new ArrayList<SelectItem>();
        List<Estados> le = entidadesFacade.Buscar(id_pais);
        listentidades.clear();
        for (Estados e : le) {
            SelectItem entidad_item = new SelectItem(e.getId(),e.getNombre());
            listentidades.add(entidad_item);
        }
        FacesContext.getCurrentInstance().renderResponse();
        return listentidades;
    }
    
    
    
    public List<SelectItem> buscamunicipios(final AjaxBehaviorEvent event){
        listmunicipios = new ArrayList<SelectItem>();
        List<Municipios> lm = municipiosFacade.Buscar(id_entidad);
        listmunicipios.clear();
        for (Municipios m : lm) {
            SelectItem municipio_item = new SelectItem(m.getId(),m.getNombre());
            listmunicipios.add(municipio_item);
        }
        FacesContext.getCurrentInstance().renderResponse();
        return listmunicipios;
    }
    
}
