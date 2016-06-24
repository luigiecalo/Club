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
    private String active = "";
    RequestContext context = RequestContext.getCurrentInstance();
    private String contenido = INICIO;
    private String hader = "INICIO";
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
        contenido = modu.getSrc();
        hader = modu.getNombre();
        modulo = modu.getSrc();
        moduloSelecionado = modu;
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
            }
        }
    }

    public void selecionarmenuId(Map ver) {
        Modulo mod = ModuloDao.consultar(Modulo.class, ver.get("id"));
        selecionarmenu(mod, ver);
    }

    public void cerrarmodulo(Modulo modu) {
        Modulos.remove(modu);
    }

    public String cargarmodulo(Modulo modu) {
        RequestContext context = RequestContext.getCurrentInstance();
        modulo = modu.getSrc();
//        context.getCurrentInstance().execute(""
//                + "$('#tab-list').append($('<li><a href=\"#tab"+ modu.getIdmodulo() + "\" role=\"tab\" data-toggle=\"tab\">Tab " + modu.getIdmodulo() + "<button class=\"close\" type=\"button\" title=\"Remove this page\">×</button></a></li>"
//                +" "
//                + "'));");
//        System.out.println("---AKI ESTA"
//                + "$('#tab-list').append($('<li><a href=\"#tab"+ modu.getIdmodulo() + "\" role=\"tab\" data-toggle=\"tab\">Tab " + modu.getIdmodulo() + "<button class=\"close\" type=\"button\" title=\"Remove this page\">×</button></a></li>"
//                + "");
        return modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public String getActive(String mod) {
        String active1 = null;
        if (mod.equals(modulo)) {
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

    public String getHader() {
        return hader;
    }

    public void setHader(String hader) {
        this.hader = hader;
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

}
