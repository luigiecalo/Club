/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprueba;

import com.Dao.MiembroDaoimplement;
import com.Dao.ModuloDaoimplement;
import com.Dao.PermisosDaoimplement;
import com.Dao.RolDaoimplement;
import com.Dao.RolModuloPermisoDaoimplement;
import com.Dao.UsuarioDaoimplement;
import com.Entidades.Miembro;
import com.Entidades.Modulo;
import com.Entidades.Permisos;
import com.Entidades.Rol;
import com.Entidades.RolModuloPermiso;
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
        ModuloDaoimplement MoDao = new ModuloDaoimplement();
        RolModuloPermisoDaoimplement RMPDao = new RolModuloPermisoDaoimplement();
        

        //OBJETOS
        Permisos permiso = new Permisos();
        Miembro miembro = new Miembro();
        Modulo modulo = new Modulo();
   
        Rol rol = new Rol();
        Usuario usuario = new Usuario();

        //Lita de OBJETOS
        List<Permisos> permisoLista = new ArrayList<Permisos>();
        List<Miembro> miembroLista = new ArrayList<Miembro>();
        List<Rol> rolesLista = new ArrayList<Rol>();
        List<Usuario> usuarioLista = new ArrayList<Usuario>();
       
        List<RolModuloPermiso> rolmodulospermisos = new ArrayList<RolModuloPermiso>();
        Long id;

//                ///CREAR PERMISO
//        id = Long.parseLong("1");
//        permiso = PDao.consultar(Permisos.class, id);
//        permiso.setNombrePermiso("T");
//        permiso.setDescripcionPermiso("TOTAL");
//        PDao.crear(permiso);
//        permiso.setNombrePermiso("L");
//        permiso.setDescripcionPermiso("LECTURA");
//        PDao.crear(permiso);
//        permiso.setNombrePermiso("E");
//        permiso.setDescripcionPermiso("ESCRITURA");
//        PDao.crear(permiso);
//        permiso.setNombrePermiso("M");
//        permiso.setDescripcionPermiso("MODIFICACION");
//        PDao.crear(permiso);
//        System.out.println("REGISTRO DE PERMISOS FUE UN EXITO");
//
        //OPERACION ROL
//        rol = RDao.consultarC(Rol.class, id);
//        id = Long.parseLong("11");
//        permiso = PDao.consultar(Permisos.class, id);
//        permisoLista.add(permiso);
////        // -----------------------
////        permisoLista = PDao.consultarTodo(Permisos.class);
////        //-----------------------
//        rol.setNombre("ADMINISTRADOR");
//        rol.setActivo(Boolean.TRUE);
//        RDao.crear(rol);//CREAR
//     // RDao.modificar(rol);//MODIFICAR
//        System.out.println("OPERACION DE ROL CON PERMISO EXITOSO");
//        
        ///OPERACION MODULO
//        modulo = MoDao.consultarC(Modulo.class, id);
//          modulo.setDescripcion("Aca");
//        modulo.setIcono("spiner");
//        MoDao.crear(modulo);
//        System.out.println("OPERACION DE MODULO PERMISOS EXITOSA");

//        RMP.setRol(rol);
//        RMP.setModulo(modulo);
//        RMPDao.crear(RMP);
        System.out.println("OPERACION DE MODULO PERMISOS EXITOSA");

        ///OPERACION USUARIO
//        id = Long.parseLong("4");
//        rol = RDao.consultar(Rol.class, id);
//        rolesLista.add(rol);
//         -----------------------
//        rolesLista = RDao.consultarTodo(Rol.class);
////        -----------------------
//        usuario.setEstado(1);
//        usuario.setRoles(rolesLista);
        //   UDao.crear(usuario); //CREAR
//        UDao.modificar(usuario); //MODIFICAR
//        System.out.println("OPERACION DE USUARIO EXITOSO");
//        
        //CRAER MIEMBRO
//        id = Long.parseLong("1");
//        miembro = MDao.consultar(Miembro.class, id);
//        usuario = miembro.getUsuario();
//        usuario.setLogin("LUIGIE");
//        usuario.setPassword("LUIGIE");
//        miembro.setUsuario(usuario);
//        MDao.modificar(miembro);
//        System.out.println("OPERACION DE MIEMBRO EXITOSA");
        //LISTAR USUARIOS
//        usuarioLista = UDao.Listar();
//        for (Usuario usuari : usuarioLista) {
//            System.out.println(usuari.getLogin());
//        }
//        id=Long.parseLong("2");
//        usuario= UDao.buscarId1(id);
//        System.out.println(usuario.getRoles().size());
        //BUSCAR USUARIOS
//        Long id= Long.parseLong("1");
//        usuario=nUDao.consultar(Usuario.class,id);
//         System.out.println("Se ENCONTRO EL USUARIO: "+usuario.toString());
    }
}
