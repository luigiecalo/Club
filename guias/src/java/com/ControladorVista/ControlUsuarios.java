/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladorVista;

import Utilidades.Utilidades;
import static com.ControladorVista.ControlSeccion.toLong;
import com.Dao.ModuloDaoimplement;
import com.Dao.UsuarioDaoimplement;
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

    @ManagedProperty("#{controlSeccion}")
    private ControlSeccion cs = new ControlSeccion();
    private Long idmod;
    private Utilidades util= new Utilidades();
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarioLista = new ArrayList<Usuario>();

    private UsuarioDaoimplement UsuDao = new UsuarioDaoimplement();

    /**
     * Creates a new instance of controlplantillas
     */
    public ControlUsuarios() {

    }

//metodos
    public List<Usuario> listarusuarios() {
        usuarioLista = UsuDao.Listar();
        return usuarioLista;
    }
    
    public boolean bootonregistro(Long idrol,Modulo mod){
    return util.registro(idrol, mod);
    }

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

}
