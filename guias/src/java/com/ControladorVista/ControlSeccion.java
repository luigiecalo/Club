/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import com.Dao.RolDaoimplement;
import com.Dao.UsuarioDaoimplement;
import com.Entidades.Permisos;
import com.Entidades.Rol;
import java.util.List;
import java.util.ArrayList;
import com.Entidades.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LuisGuillermo
 */
@ManagedBean
@SessionScoped
public class ControlSeccion {

    private Usuario usuario = new Usuario();
    private Rol rol = new Rol();
    private List<Permisos> permisos = new ArrayList<Permisos>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private String usu;
    private String pass;
    private Long rolselect;
    private UsuarioDaoimplement usuarioDao = new UsuarioDaoimplement();
    private RolDaoimplement rolDao = new RolDaoimplement();
    private boolean seccion = true;

    /**
     * Creates a new instance of ControlSeccion
     */
    public ControlSeccion() {
        seccion = true;
    }

    //METODOS
    public void iniciar() {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        usuario = usuarioDao.login(usu, pass);
        if (usuario == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Primer Mensage", "USUARIO NO ENCONTRADO REGISTRADO"));
        } else {
            if(usuario.getRoles().size()==1){
                rolselect=usuario.getRoles().get(0).getId();
                selecionRol();
            }else{
            requestContext.getCurrentInstance().execute("$('.modalPseudoClass').modal();");
            } 
        }

    }

    public void selecionRol() {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        rol = rolDao.consultarC(Rol.class, rolselect);
        if (rol == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Primer Mensage", "SELECIONE UN ROL"));
        } else {
            try {
                seccion = false;
                secccion();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Primer Mensage", "BIENBENIDO " + usuario.getLogin()));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Primer Mensage", "Como :" + rol.getNombre()));
                requestContext.getCurrentInstance().execute("$('.modalPseudoClass').modal('hide');");
                if (rol.getId().equals(toLong(1))) {
                    context.getExternalContext().redirect("superAdministrador.xhtml");
                } else {
                    context.getExternalContext().redirect("AdminLTE-master/index.html");
                }

            } catch (IOException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", ex.getLocalizedMessage()));
            }
        }
    }

    public void secccion() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println(usu);
        if (usu == null) {
            seccion = true;
            //  context.addMessage(null, new FacesMessage("Error de Autenticacion"));
        } else {
            seccion = false;

        }
    }

    public static long toLong(Number number) {
        return number.longValue();
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isSeccion() {
        return seccion;
    }

    public void setSeccion(boolean seccion) {
        this.seccion = seccion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permisos> permisos) {
        this.permisos = permisos;
    }

    public Long getRolselect() {
        return rolselect;
    }

    public void setRolselect(Long rolselect) {
        this.rolselect = rolselect;
    }

}
