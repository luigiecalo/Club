/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprueba;

import com.Controlador.PermisosJpaController;
import com.Controlador.RolesJpaController;
import com.Dao.MiembroDaoimplement;
import com.Dao.PermisosDaoimplement;
import com.Dao.RolesDaoimplement;
import com.Dao.UsuariosDaoimplement;
import com.Entidades.Miembro;
import com.Entidades.Permisos;
import com.Entidades.Roles;
import com.Entidades.Usuarios;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class prueba implements Serializable {

    public static void main(String[] args) {
        System.out.println("Hola");
        // ----<Controladore JPADAO Controller>-----
        PermisosDaoimplement PDao = new PermisosDaoimplement();
        RolesDaoimplement RDao = new RolesDaoimplement();
        UsuariosDaoimplement UDao = new UsuariosDaoimplement();
        MiembroDaoimplement MDao = new MiembroDaoimplement();
        //OBJETOS
        Permisos permiso = new Permisos();
        Roles rol = new Roles();
        Usuarios usuario = new Usuarios();
        Miembro miembro = new Miembro();

        //Lita de OBJETOS
        List<Permisos> permisoLista=new  ArrayList<Permisos>();
        List<Roles> rolesLista=new  ArrayList<Roles>();
        List<Usuarios> usuarioLista=new  ArrayList<Usuarios>();
        List<Miembro> miembroLista=new  ArrayList<Miembro>();
        
        ///CREAR 
        
        ///CREAR PERMISO
        permiso.setNombrePermiso("E");
        permiso.setDescripcionPermiso("ESCRITURA");
        permiso.setRolesList(null);
        PDao.crear(permiso);
        
        //Crear Rol
        permiso=PDao.consultar(Permisos.class,1);
        rol.setNombreRol("LECTOR");
        rol.setDescripcionRol("SOLO TINE PERMISO E LECTURA");
        permisoLista.add(permiso);
        rol.setPermisosList(permisoLista);
        //RDao.guardarcompleto(rol);
        
        //Buscar Rol
        permiso=PDao.consultar(Permisos.class,2);
        permisoLista.add(permiso);
        rol=RDao.consultar(Roles.class, 2);
        rol.setNombreRol("NUEVO LECTOR");
        RDao.modificar(rol);
    }
}
