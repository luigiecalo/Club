/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprueba;

import com.Dao.MiembroDaoimplement;
import com.Dao.PermisosDaoimplement;
import com.Dao.RolDaoimplement;
import com.Dao.UsuarioDaoimplement;
import com.Entidades.Miembro;
import com.Entidades.Permisos;
import com.Entidades.Rol;
import com.Entidades.Usuario;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class prueba implements Serializable {

    public static void main(String[] args) {
        System.out.println("Hola");
        // ----<Controladore JPADAO Controller>-----
        PermisosDaoimplement PDao = new PermisosDaoimplement();
        MiembroDaoimplement MDao = new MiembroDaoimplement();
        RolDaoimplement RDao = new RolDaoimplement();
        UsuarioDaoimplement UDao = new UsuarioDaoimplement();
        //OBJETOS
        Permisos permiso = new Permisos();
        Miembro miembro = new Miembro();
        Rol rol = new Rol();
        Usuario usuario = new Usuario();

        //Lita de OBJETOS
        List<Permisos> permisoLista = new ArrayList<Permisos>();
        List<Miembro> miembroLista = new ArrayList<Miembro>();
        List<Rol> rolesLista = new ArrayList<Rol>();
        List<Usuario> usuarioLista = new ArrayList<Usuario>();
        Long id = Long.parseLong("1");

//                ///CREAR PERMISO
//        permiso.setNombrePermiso("L");
//        permiso.setDescripcionPermiso("LECTURA");
//        permiso.setRoles(null);
//        PDao.crear(permiso);
//        System.out.println("REGISTRO DE PERMISO CON USUARIO EXITOSO");
//
        //OPERACION ROL
//       rol = RDao.consultarC(Rol.class,id);
//        permiso = PDao.consultar(Permisos.class, id);
//        permisoLista.add(permiso);
//        // -----------------------
//        permisoLista = PDao.consultarTodo(Permisos.class);
//        //-----------------------
//        rol.setNombre("SUPERADMINISTRADOR");
//        rol.setActivo(Boolean.TRUE);
//        rol.setPermisos(permisoLista);
//     // RDao.crear(rol);//CREAR
//     // RDao.modificar(rol);//MODIFICAR
//        System.out.println("OPERACION DE ROL CON PERMISO EXITOSO");
//        
        ///OPERACION USUARIO
        //  usuario = UDao.consultarC(Usuario.class, id);
//        rol = RDao.consultar(Rol.class, id);
//        rolesLista.add(rol);
//         -----------------------
        rolesLista = RDao.consultarTodo(Rol.class);
//        -----------------------
        usuario.setLogin("ROLANDL");
        usuario.setPassword("Ronald");
        
        usuario.setRoles(rolesLista);
//        UDao.crear(usuario); //CREAR
//        UDao.modificar(usuario); //MODIFICAR
//        System.out.println("OPERACION DE USUARIO EXITOSO");
//        
        //CRAER MIEMBRO
        miembro.setNombre1("Luis");
        miembro.setNombre2("Guillermo");
        miembro.setApellido1("Castro");
        miembro.setApellido2("Lora");
        miembro.setEstado("ACTIVO");
       miembro.setUsuario(usuario);
        MDao.crear(miembro);
        System.out.println("OPERACION DE MIEMBRO EXITOSA");

        //LISTAR USUARIOS
        usuarioLista = UDao.Listar();
        for (Usuario usuari : usuarioLista) {
            System.out.println(usuari.getLogin());
        }

        //BUSCAR USUARIOS
//        Long id= Long.parseLong("1");
//        usuario=nUDao.consultar(Usuario.class,id);
//         System.out.println("Se ENCONTRO EL USUARIO: "+usuario.toString());
    }
}
