/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import static com.ControladorVista.ControlSeccion.toLong;
import com.Entidades.Modulo;
import com.Entidades.Rol;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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
    private String modulo = "usuarios.xhtml";
    private String active = "";
    RequestContext context = RequestContext.getCurrentInstance();
    private String contenido = INICIO;
    private String hader = "INICIO";

    /**
     * Creates a new instance of controlplantillas
     */
    public Controlplantillas() {

    }

    public static long toLong(Number number) {
        return number.longValue();
    }
//metodos

    public void selecionarmenu(Modulo modu) {
        contenido = modu.getSrc();
        hader = modu.getNombre();
        modulo = modu.getSrc();

    }

    public String cargarmodulo(Modulo modu) {
        RequestContext context = RequestContext.getCurrentInstance();
        modulo = modu.getSrc();
        context.getCurrentInstance().execute(""
                + "$('#tab-list').append($('<li><a href=#tab' "+ modu.getIdmodulo() +" '\" role=\"tab\" data-toggle=\"tab\">Tab ' "+ modu.getIdmodulo() +" '<button class=\"close\" type=\"button\" title=\"Remove this page\">×</button></a></li>'));"
                + "");
        return modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public String getActive(Modulo mod) {
        String active1 = null;
        if (mod.getSrc().equals(modulo)) {
            active1 = "active";
        } else {
            if (mod == null) {
                active1 = "active";
            }
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

}
