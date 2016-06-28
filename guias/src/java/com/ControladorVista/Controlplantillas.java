/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import static com.ControladorVista.ControlSeccion.toLong;
import com.Dao.ModuloDaoimplement;
import com.Entidades.Modulo;
import com.Entidades.Rol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author luigi
 */
@ManagedBean
@ViewScoped
public class Controlplantillas implements Serializable {

    String INICIO = "usuarios.xhtml";

    @ManagedProperty("#{controlSeccion}")
    private ControlSeccion cs = new ControlSeccion();
    private String modulo = "principal.xhtml";
    private Long moduloid = Long.parseLong("0");
    private String grupo = "principal.xhtml";
    private String subGrupo = "principal.xhtml";
    private String active = "";
    RequestContext context = RequestContext.getCurrentInstance();
    private String contenido = INICIO;
    private List<Modulo> Modulos = new ArrayList<Modulo>();
    private Modulo moduloSelecionado = new Modulo();
    private ModuloDaoimplement ModuloDao = new ModuloDaoimplement();

    /**
     * Creates a new instance of controlplantillas
     */
    public Controlplantillas() {

    }

    public static long toLong(Number number) {
        return number.longValue();
    }
//metodos

    public void selecionarmenu(Modulo modu, Map ver) {
        System.out.println(ver);
        modulo = modu.getSrc();
        moduloSelecionado = modu;
        moduloid= modu.getIdmodulo();
        if (modu.getGrupomodulo() != null) {
            grupo = modu.getGrupomodulo().getNombre();
        } else {
            if (ver.get("tipo").equals("modulo")) {
                grupo = modu.getNombre();
            } else {
                grupo="";
            }

        }
        if (modu.getSubgrupos() != null) {
            subGrupo = modu.getSubgrupos().getNombre();
        } else {
            subGrupo = "";
        }

        if (Modulos.isEmpty()) {
            Modulos.add(modu);
        } else {
            boolean encontro = false;
            for (Modulo Modulo1 : Modulos) {
                if (Modulo1.equals(modu)) {
                    encontro = true;
                }
            }
            if (!encontro) {
                Modulos.add(modu);
//                RequestContext context = RequestContext.getCurrentInstance();
//        context.getCurrentInstance().execute("$(#).dropdown('toggle');");
                
            }
        }

    }

    public void selecionarmenuGrupo(Map ver) {
        grupo = (String) ver.get("nombre");
        Modulo mod = ModuloDao.consultar(Modulo.class, ver.get("id"));
        if (ver.get("tipo").equals("modulo")) {
            selecionarmenu(mod, ver);
        }
    }

    public void selecionarmenuSubGrupo(Modulo modu, Map ver) {
        subGrupo = modu.getSubgrupos().getNombre();
    }

    public void cerrarmodulo(Modulo modu) {
        Modulos.remove(modu);
    }

    public String cargarmodulo(Modulo modu) {
        RequestContext context = RequestContext.getCurrentInstance();
        modulo = modu.getSrc();
        moduloid= modu.getIdmodulo();
        if (modu.getGrupomodulo() != null) {
            grupo = modu.getGrupomodulo().getNombre();
        } else {
                grupo=modu.getNombre();
        }
        if (modu.getSubgrupos() != null) {
            subGrupo = modu.getSubgrupos().getNombre();
        } else {
            subGrupo = "";
        }
        return modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public String getActive(Long mod) {
        String active1 = null;
        if (mod.equals(moduloid)) {
            active1 = "active";
        }
        return active1;
    }

    public String getActiveGrupo(Map ver) {
        String active1 = null;
        if (ver.get("nombre").equals(grupo)) {
            active1 = "active";
        }
        return active1;
    }
    
    public String getDatatogleGrupo(Map ver) {
        String active1 = null;
       
        if (ver.get("tipo").equals("modulo")) {
             active1 = "offcanvas";
        }
        return active1;
    }

    public String getActiveSubgrupo(String subgrup) {
        String active1 = null;
        if (subgrup.equals(subGrupo)) {
            active1 = "active";
        }
        return active1;
    }
    
    public String getActiveSubgrupomodulo(Long modid) {
        String active1 = null;
        if (modid.equals(moduloid)) {
            active1 = "active";
        }
        return active1;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

   

    public ControlSeccion getCs() {
        return cs;
    }

    public void setCs(ControlSeccion cs) {
        this.cs = cs;
    }

    public List<Modulo> getModulos() {
        return Modulos;
    }

    public void setModulos(List<Modulo> Modulos) {
        this.Modulos = Modulos;
    }

    public Modulo getModuloSelecionado() {
        return moduloSelecionado;
    }

    public void setModuloSelecionado(Modulo moduloSelecionado) {
        this.moduloSelecionado = moduloSelecionado;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getSubGrupo() {
        return subGrupo;
    }

    public Long getModuloid() {
        return moduloid;
    }

    public void setModuloid(Long moduloid) {
        this.moduloid = moduloid;
    }
    

}
