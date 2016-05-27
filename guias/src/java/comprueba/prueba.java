/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprueba;

import com.Controladoras.PermisosJpaController;
import com.Controladoras.RolesJpaController;
import com.Dao.PermisosDaoimplement;
import com.Dao.RolesDaoimplement;
import com.Entidades.Permisos;
import com.Entidades.Roles;
import com.Entidades.Usuarios;
import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class prueba implements Serializable {

    public static void main(String[] args) {
        System.out.println("Hola");
        PermisosDaoimplement Dao = new PermisosDaoimplement();
        RolesDaoimplement RDao = new RolesDaoimplement();
        Roles r = RDao.consultar(Roles.class,6);
        PermisosJpaController jpa = new PermisosJpaController();
        RolesJpaController Rjpa = new RolesJpaController();
        Permisos p = Dao.consultarC(Permisos.class, 1);
        System.out.println("permiso 1 es:" + p.toString());
        
        Roles rol = new Roles();
        rol.setDescripcionRol("asd");
        rol.setNombreRol("NUEVO");
        rol.setPermisosCollection(Dao.Listar());
        rol.setUsuariosCollection(null);
        //RDao.eliminar(rol);
        RDao.eliminar(r);
       //Rjpa.create(rol);
        
        System.out.println("Registro Exitoso");
    }
}
