/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import com.Entidades.Miembro;
import java.util.List;
import java.util.ArrayList;
import com.Entidades.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author LuisGuillermo
 */
@ManagedBean
@SessionScoped
public class ControlSeccion {

    
    private Usuario uaurio;
    private List<Usuario> uaurios= new ArrayList<Usuario>();
    private String usu;
    private String pass;
    /**
     * Creates a new instance of ControlSeccion
     */
    public ControlSeccion() {
    }

    //METODOS
    public void iniciar(){}
    
    
    
    
    
    //GET & SET
    
    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public Usuario getUaurio() {
        return uaurio;
    }

    public void setUaurio(Usuario uaurio) {
        this.uaurio = uaurio;
    }

    public List<Usuario> getUaurios() {
        return uaurios;
    }

    public void setUaurios(List<Usuario> uaurios) {
        this.uaurios = uaurios;
    }
    
    
    
}
