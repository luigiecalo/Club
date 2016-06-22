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
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import com.Entidades.Usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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
public class ControlSeccion implements Serializable {

    private Miembro miembro = null;
    private Rol rolSeccion = new Rol();
    private List<Modulo> ModulosSeccion = new ArrayList<Modulo>();

    private Map<String, Long> rolesSeccion;
    private Long rolTemp;

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
            miembro = miembroDao.BuscarMiembroUsuario(usuario);
            if (usuario.getRoles().size() == 1) {
                rolselect = usuario.getRoles().get(0).getIdrol();
                selecionRol();
            } else {
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
//                    if (rolSeccion.getIdrol().equals(toLong(1))) {
//                        context.getExternalContext().redirect("superAdministrador.xhtml");
//                    } else {
                    context.getExternalContext().redirect("inicio.xhtml");
//                    }
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
            seccion = false;
            salir();
        } else {
            seccion = true;

            cargarModulos();

        }
    }

    public void validaSeccion() {
        if (miembro == null) {
            seccion = false;
            salir();
        } else {
            seccion = true;
            cargarModulos();
        }
    }

    public void update(String id) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.getCurrentInstance().update(id);
//      context.getCurrentInstance().execute("$('#myModal').modal('show');");
    }

    public void modalRol(String id, String estado) {
        RequestContext context = RequestContext.getCurrentInstance();
//      context.getCurrentInstance().update(id);
        context.getCurrentInstance().execute("$('#" + id + "').modal('" + estado + "');");
    }

    public List<Map> getNewmenu() {
        int i = 1;
        List<Map> menu = new LinkedList<Map>();
        List<Map> menunew = new LinkedList<Map>();
        for (Modulo modulo : ModulosSeccion) {
            Map item = new HashMap<String, String>();
            item.put("src", modulo.getSrc());
            if (modulo.getGrupomodulo() == null) {
                System.out.println("*" + modulo.getNombre() + "*");
                item.put("id", i);
                item.put("icono", modulo.getIcono());
                item.put("nombre", modulo.getNombre());
                item.put("grupo", null);
                item.put("modulos", null);
                item.put("submodulos", null);
                item.put("update", "@form");
                item.put("subupdate", null);
                item.put("subupdate2", null);
                menu.add(item);
                i++;
            } else {
                System.out.println("*" + modulo.getGrupomodulo().getNombre() + "*");
                item.put("id", i);
                item.put("nombre", modulo.getGrupomodulo().getNombre());
                item.put("icono", modulo.getGrupomodulo().getIcono());
                if (modulo.getSubgrupos() == null) {
                    List<Modulo> modulosvalidos = new ArrayList<Modulo>();
                    for (Modulo moduN1 : modulo.getGrupomodulo().getModulos()) {
                        if (moduN1.getSubgrupos() == null) {
                            if (rolMPDao.buscarModulosValido(rolSeccion.getIdrol(), moduN1.getIdmodulo())) {
                                System.out.println(" -" + moduN1.getNombre() + "-");
                                modulosvalidos.add(moduN1);
                            }
                        }
                    }
                    item.put("modulos", modulosvalidos);
                    item.put("grupo", modulo.getGrupomodulo().getNombre());
                    item.put("submodulos", null);
                    item.put("update", null);
                    item.put("subupdate", "@form");
                    item.put("subupdate2", null);
                    menu.add(item);
                    i++;
                } else {
                    List<Modulo> submodulosvalidos = new ArrayList<Modulo>();
                    List<Modulo> modulosvalidos = new ArrayList<Modulo>();
                    for (Modulo moduN1 : modulo.getGrupomodulo().getModulos()) {
                        if (moduN1.getSubgrupos() == null) {
                            if (rolMPDao.buscarModulosValido(rolSeccion.getIdrol(), moduN1.getIdmodulo())) {
                                System.out.println(" -" + moduN1.getNombre() + "-");
                                modulosvalidos.add(moduN1);
                            }
                        } else {
                            if (rolMPDao.buscarModulosValido(rolSeccion.getIdrol(), moduN1.getIdmodulo())) {
                                System.out.println(" -" + moduN1.getNombre() + "-");
                                modulosvalidos.add(moduN1);
                            }
                            for (Modulo moduN2 : moduN1.getSubgrupos().getModulos()) {
                                if (rolMPDao.buscarModulosValido(rolSeccion.getIdrol(), moduN2.getIdmodulo())) {
                                    System.out.println(" -" + moduN2.getNombre() + "-");
                                    submodulosvalidos.add(moduN2);
                                }
                            }
                        }
                    }
                    item.put("modulos", modulosvalidos);
                    item.put("grupo", modulo.getSubgrupos().getNombre());
                    item.put("submodulos", submodulosvalidos);
                    item.put("update", null);
                    item.put("subupdate", null);
                    item.put("subupdate2", "@form");
                    menu.add(item);
                    i++;

                }

//                
//                    }
//              
//
            }
          
            for (Map item2 : menu) {
                if (menunew.isEmpty()) {
                    menunew.add(item2);
                } else {
                    boolean encontro = false;
                    for (Map itemMenu : menunew) {
                        if (itemMenu.get("nombre").equals(item2.get("nombre"))) {
                            encontro = true;
                            break;
                        }
                    }
                    if (!encontro) {
                        menunew.add(item2);
                    }

                }

            }
            
        }

        return menunew;
    }

    public boolean isGrupo(Modulo m) {
        boolean grupo = false;
        if (m.getGrupomodulo() != null) {
            grupo = true;
        }
        return grupo;
    }

    public boolean isSubgrupo(Modulo m) {
        boolean subgrup = false;
        if (m.getSubgrupos() != null) {
            subgrup = true;
        }
        return subgrup;
    }

    public List<Map> getMenu() {
        List<Map> menu = new LinkedList<Map>();
        String nombregrupo = null;

        for (Modulo Modulo : ModulosSeccion) {
            Map item = new HashMap<String, String>();
            if (Modulo.getGrupomodulo() == null) {
                item.put("icono", Modulo.getIcono());
                item.put("src", Modulo.getSrc());
                item.put("nombre", Modulo.getNombre());
                item.put("modulos", null);
                item.put("submodulos", null);
                item.put("update", "@form");
                item.put("subupdate", null);
                menu.add(item);
            } else {
                if (Modulo.getSubgrupos() == null) {
                    if (menu.isEmpty()) {
//                        agregarSubItem(item, Modulo);
                    } else {
//                        agregarSubItem(item, Modulo);
                        boolean encontro = false;
                        for (Map item2 : menu) {
                            if (item.get("nombre").equals(item2.get("nombre"))) {
                                encontro = true;
                            }
                        }
                        if (!encontro) {
                            menu.add(item);
                        }
                    }
                }

            }

        }

        return menu;
    }

    public void agregarSubItem(Map item, Modulo Modulo, List<Map> menu) {
        item.put("icono", Modulo.getIcono());
        item.put("src", Modulo.getSrc());
        item.put("nombre", Modulo.getGrupomodulo().getNombre());
        //grupos
        List<Modulo> modulosvalidos = new ArrayList<Modulo>();
        List<Modulo> submodulosvalidos = new ArrayList<Modulo>();
        for (Modulo modgrup : Modulo.getGrupomodulo().getModulos()) {
            if (rolMPDao.buscarModulosValido(rolSeccion.getIdrol(), modgrup.getIdmodulo())) {
                if (modgrup.getSubgrupos() != null) {
                    for (Modulo modsubgrup : Modulo.getSubgrupos().getModulos()) {
                        if (rolMPDao.buscarModulosValido(rolSeccion.getIdrol(), modsubgrup.getIdmodulo())) {
                            submodulosvalidos.add(modsubgrup);
                        }
                    }
                } else {
                    modulosvalidos.add(modgrup);
                }
            }
        }
        Modulo.getGrupomodulo().setModulos(modulosvalidos);
//        //subGrupos
        if (Modulo.getSubgrupos() != null) {
            item.put("submodulos", Modulo.getSubgrupos().getModulos());
            item.put("subnombre", Modulo.getSubgrupos().getNombre());
        } else {
            item.put("submodulos", null);
            item.put("subnombre", null);
        }
        item.put("modulos", Modulo.getGrupomodulo().getModulos());
        item.put("update", null);
        item.put("subupdate", "@form");
    }

    public void salir() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            ExternalContext externalContext = context.getExternalContext();

            Object session = externalContext.getSession(false);
//            context.getCurrentInstance().getExternalContext().invalidateSession();
            HttpSession httpSession = (HttpSession) session;

            httpSession.invalidate();

            context.getExternalContext().redirect("index.xhtml");

            miembro = null;
            seccion = false;
            rolSeccion = null;
            rolselect = toLong(0);
        } catch (IOException ex) {
            Logger.getLogger(ControlSeccion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cargarModulos() {
        ModulosSeccion = rolMPDao.buscarModulos(rolSeccion.getIdrol());
        rolesSeccion = new HashMap<String, Long>();
        for (Rol rol : miembro.getUsuario().getRoles()) {
            this.rolesSeccion.put(rol.getNombre(), rol.getIdrol());
        }
    }

    public void selecionarrolseccion() {
        rolselect = rolTemp;
        setRolselect(getRolTemp());
        selecionRol();
    }

    public static long toLong(Number number) {
        return number.longValue();
    }

    public void cambiarsikin(String skin) {
        skins = skin;
        selecionRol();
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

    public Map<String, Long> getRolesSeccion() {

        return rolesSeccion;
    }

    public Long getRolTemp() {
        rolTemp = getRolselect();
        return rolTemp;
    }

    public void setRolTemp(Long rolTemp) {
        this.rolTemp = rolTemp;
    }
}
