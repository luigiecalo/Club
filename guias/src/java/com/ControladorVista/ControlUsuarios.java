/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import Utilidades.Utilidades;
import static com.ControladorVista.ControlSeccion.toLong;
import com.Dao.MiembroDaoimplement;
import com.Dao.ModuloDaoimplement;
import com.Dao.UsuarioDaoimplement;
import com.Entidades.Miembro;
import com.Entidades.Modulo;
import com.Entidades.Rol;
import com.Entidades.Usuario;
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
public class ControlUsuarios implements Serializable {

    private Long idmod;
    private Utilidades util = new Utilidades();
    private Usuario usuario = new Usuario();
    private Miembro miembro = new Miembro();
    private List<Usuario> usuarioLista = new ArrayList<Usuario>();
    private List<Miembro> miembros = new ArrayList<Miembro>();
    private boolean Registrar = false;
    private UsuarioDaoimplement UsuDao = new UsuarioDaoimplement();
    private MiembroDaoimplement MiemDao = new MiembroDaoimplement();

    /**
     * Creates a new instance of controlplantillas
     */
    public ControlUsuarios() {

    }

//metodos
    public void listarusuarios() {
        usuarioLista= UsuDao.Listar();
    }
    
     public void listarMiembros() {
        miembros= MiemDao.consultarTodo(Miembro.class);
    }

    public boolean bootonregistro(Long idrol, Modulo mod) {
        return util.registro(idrol, mod);
    }

    public void registroModulo() {
        Registrar = true;
    }

    public void consultaModulo() {
        Registrar = false;
    }

//    public String moduloRegistro() {
//        if (Registrar) {
//            return "visible";
//        } else {
//            return "hidden";
//        }
//    }
//
//    public String moduloreConsulta() {
//        if (Registrar) {
//            return "hidden";
//        } else {
//            return "visible";
//        }
//    }

    //GET AND SET
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarioLista() {
        return usuarioLista;
    }

    public void setUsuarioLista(List<Usuario> usuarioLista) {
        this.usuarioLista = usuarioLista;
    }

    public boolean isRegistrar() {
        return Registrar;
    }

    public void setRegistrar(boolean Registrar) {
        this.Registrar = Registrar;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

}
