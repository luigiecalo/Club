/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import static com.ControladorVista.ControlSeccion.toLong;
import com.Entidades.Rol;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author luigi
 */
@ManagedBean
@RequestScoped
public class Controlplantillas implements Serializable {

    String INGRESOS = "ingresos.xhtml";
    String INICIO = "inicio.html";
    String GASTOS = "gastos.xhtml";
    String ESTADISTICAS = "estadisticas.xhtml";
    String REGISTROS = "RegistrarUsuario.xhtml";
    private Long rolTemp;
    @ManagedProperty("#{controlSeccion}")
    private ControlSeccion cs = new ControlSeccion();
    private Map<String, Long> rolesSeccion;
    RequestContext context = RequestContext.getCurrentInstance();
    private String contenido = INICIO;
    private String hader = "INICIO";

    public void rolseccion() {
        this.cs.setRolselect(getRolTemp());
        this.cs.selecionRol();
    }

    public void modalCambiarRol() {
        RequestContext context = RequestContext.getCurrentInstance();
//      context.getCurrentInstance().update(id);
        context.getCurrentInstance().execute("$('#mdCambiarRol').modal('show');");
    }

    /**
     * Creates a new instance of controlplantillas
     */
    public Controlplantillas() {
        rolTemp = toLong(0);
        rolesSeccion = new HashMap<String, Long>();
        for (Rol rol : cs.getMiembro().getUsuario().getRoles()) {
            this.rolesSeccion.put(rol.getNombre(), rol.getIdrol());
        }
    }

    public static long toLong(Number number) {
        return number.longValue();
    }

    public void ingresos() {
        contenido = INGRESOS;
        hader = "INGRESOS";

    }

    public void gastos() {
        contenido = GASTOS;
        hader = "GASTOS";

    }

    public void inicio() {
        contenido = INICIO;
        hader = "INICIO";

    }

    public void estadiscas() {
        contenido = ESTADISTICAS;
        hader = "ESTADISTICAS";

    }

    public void registro() {
        contenido = REGISTROS;
        hader = "REGISTROS";

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

    public Long getRolTemp() {
        return rolTemp;
    }

    public void setRolTemp(Long rolTemp) {
        this.rolTemp = rolTemp;
    }

    public ControlSeccion getCs() {
        return cs;
    }

    public void setCs(ControlSeccion cs) {
        this.cs = cs;
    }

    
    public Map<String, Long> getRolesSeccion() {
        
        return rolesSeccion;
    }
}
