/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprueba;

import com.Controlador.PermisosJpaController;
import com.Controlador.RolesJpaController;
import com.Dao.PermisosDaoimplement;
import com.Dao.RolesDaoimplement;
import com.Entidades.Permisos;
import com.Entidades.Roles;

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
        // ----<Controladore JPAController>-----
        //Buscar perimisos 
        PermisosJpaController Pjpa = new PermisosJpaController();

        //Buscar Roles
        RolesJpaController Rjpa = new RolesJpaController();

        // ----<Controladore JPA>-----
        //Buscar perimisos 
        PermisosDaoimplement PDao = new PermisosDaoimplement();
        Permisos p = PDao.consultarC(Permisos.class, 4);
       // System.out.println("permiso Encontrado es:" + p.toString());

        //Buscar Roles
        RolesDaoimplement RDao = new RolesDaoimplement();
        Roles r = RDao.consultar(Roles.class, 16);

//        System.out.println("Lista de Roles  Encontrado es:" + RDao.Listar());
        //Crear Roles
        List<Permisos> permisos = new ArrayList<Permisos>();
        permisos.add(p);
       // p = PDao.consultarC(Permisos.class, 3);
       // permisos.add(p);
       // p = PDao.consultarC(Permisos.class, 4);
       // permisos.add(p);
        Roles rol = r;

        for (Permisos permiso : permisos) {

        }

        rol.setDescripcionRol("asd");
        rol.setNombreRol("JUAN");
        rol.setPermisosList(permisos);
        rol.setUsuariosList(null);
      //  System.out.println("Rol Encontrado es:" + rol.toString());
        try {
            Rjpa.edit(rol); //CREAR CON JPA
            // Rjpa.destroy(rol.getIdroles()); //ELIMINAR CON JPACONTROLLER
           // Rjpa.create(rol); //CREAR CON JPACONTROLLER
        } catch (Exception ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Crear Permisos
        Permisos per = new Permisos();
        per.setDescripcionPermiso("NUEVA DESCRIPCION");
        per.setNombrePermiso("OTRO");

        //PDao.crear(per); //CREAR CON JPA
        //RDao.eliminar(r); //ELIMINAR CON JPA
        //Rjpa.create(rol); //CREAR CON JPACONTROLLER
        System.out.println("Elimino Exitoso");
    }
}
