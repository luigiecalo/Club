/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import com.Dao.MiembroDaoimplement;
import com.Dao.RolDaoimplement;
import com.Dao.RolModuloPermisoDaoimplement;
import com.Dao.UsuarioDaoimplement;
import com.Entidades.Miembro;
import com.Entidades.Modulo;
import com.Entidades.Permisos;
import com.Entidades.Rol;
import com.Entidades.RolModuloPermiso;
import java.util.List;
import java.util.ArrayList;
import com.Entidades.Usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LuisGuillermo
 */
@ManagedBean
@SessionScoped
public class ControlSeccion {

    private Miembro miembro = null;
    private Rol rolSeccion = new Rol();
    private List<Modulo> ModulosSeccion = new ArrayList<Modulo>();
    
    private Map<String,Long> rolesSeccion;

    private String usu;
    private String pass;
    private String skins = "blue";
    private Long rolselect;
    private UsuarioDaoimplement usuarioDao = new UsuarioDaoimplement();
    private MiembroDaoimplement miembroDao = new MiembroDaoimplement();
    private RolModuloPermisoDaoimplement rolMPDao = new RolModuloPermisoDaoimplement();
    private RolDaoimplement rolDao = new RolDaoimplement();

    private boolean seccion = false;

    /**
     * Creates a new instance of ControlSeccion
     */
    public ControlSeccion() {
        rolselect = toLong(0);
    }

    //METODOS
    public void iniciar() {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        Usuario usuario = usuarioDao.login(usu, pass);
        if (usuario == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Primer Mensage", "USUARIO NO ENCONTRADO REGISTRADO"));
        } else {
            
            if (usuario.getRoles().size() == 1) {
                rolselect = usuario.getRoles().get(0).getIdrol();
                miembro = miembroDao.BuscarMiembroUsuario(usuario);
                selecionRol();
            } else {
                 miembro = miembroDao.BuscarMiembroUsuario(usuario);
                requestContext.getCurrentInstance().execute("$('.modalPseudoClass').modal();");
            }
        }

    }

    public void selecionRol() {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (rolselect.equals(toLong(0))) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Primer Mensage", "SELECIONE UN ROL"));
            requestContext.getCurrentInstance().execute("$('.modalPseudoClass').modal('hide');");
        } else {
            rolSeccion = rolDao.consultarC(Rol.class, rolselect);
            if (rolSeccion != null) {
                try {

                    secccion();
                    
                    requestContext.getCurrentInstance().execute("$('.modalPseudoClass').modal('hide');");
                    if (rolSeccion.getIdrol().equals(toLong(1))) {
                        context.getExternalContext().redirect("superAdministrador.xhtml");
                    } else {
                        context.getExternalContext().redirect("superAdministrador.xhtml");
                    }
                    usu = "";
                    pass = "";
                } catch (IOException ex) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", ex.getLocalizedMessage()));
                }
            }
        }

    }

    public void secccion() {
        if (miembro == null) {
            this.seccion = false;
            salir();
        } else {
            this.seccion = true;
            cargarModulos();

        }
    }

    public void salir() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            ExternalContext externalContext = context.getExternalContext();

            Object session = externalContext.getSession(false);

            HttpSession httpSession = (HttpSession) session;

            httpSession.invalidate();

            context.getExternalContext().redirect("index.xhtml");
           
            this.miembro = null;
            this.seccion = false;
            this.rolSeccion = null;
            this.rolselect = toLong(0);
        } catch (IOException ex) {
            Logger.getLogger(ControlSeccion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cargarModulos() {
        ModulosSeccion = rolMPDao.buscarModulos(miembro.getUsuario().getId());
        rolesSeccion= new HashMap<String, Long>();
        for (Rol rol : miembro.getUsuario().getRoles()) {
            rolesSeccion.put(rol.getNombre(),rol.getIdrol());
        }
    }

   

    public static long toLong(Number number) {
        return number.longValue();
    }

    public void cambiarsikin(String skin) {
        skins = skin;
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



    public boolean isSeccion() {
        return seccion;
    }

    public void setSeccion(boolean seccion) {
        this.seccion = seccion;
    }

    public Rol getRol() {
        return rolSeccion;
    }

    public void setRol(Rol rol) {
        this.rolSeccion = rol;
    }

    public Long getRolselect() {
        return rolselect;
    }

    public void setRolselect(Long rolselect) {
        this.rolselect = rolselect;
    }

    public String getSkins() {
        return skins;
    }

    public void setSkins(String skins) {
        this.skins = skins;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public Rol getRolSeccion() {
        return rolSeccion;
    }

    public List<Modulo> getModulosSeccion() {
        return ModulosSeccion;
    }

    public Map<String,Long> getRolesSeccion() {
        return rolesSeccion;
    }


}
