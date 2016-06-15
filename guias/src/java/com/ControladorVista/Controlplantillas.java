/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author luigi
 */
@ManagedBean
@SessionScoped
public class Controlplantillas {

    String INGRESOS = "ingresos.xhtml";
    String INICIO = "inicio.html";
    String GASTOS = "gastos.xhtml";
    String ESTADISTICAS = "estadisticas.xhtml";
    String REGISTROS = "RegistrarUsuario.xhtml";

    RequestContext context = RequestContext.getCurrentInstance();
    private String contenido = INICIO;
    private String hader = "INICIO";

    /**
     * Creates a new instance of controlplantillas
     */
    public Controlplantillas() {
      
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

}
