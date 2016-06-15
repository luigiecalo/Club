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
import com.Entidades.RolModuloPermisoPK;
import com.Entidades.Usuario;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class ConfiguracionInicial implements Serializable {

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
        RolModuloPermiso rmp = new RolModuloPermiso();
        Rol rol = new Rol();
        Usuario usuario = new Usuario();

        //Lita de OBJETOS
        List<Permisos> permisoLista = new ArrayList<Permisos>();
        List<Miembro> miembroLista = new ArrayList<Miembro>();
         List<Rol> rolesLista = new ArrayList<Rol>();
        List<Usuario> usuarioLista = new ArrayList<Usuario>();

        List<RolModuloPermiso> rolmodulospermisos = new ArrayList<RolModuloPermiso>();
        Long id;
        try{
        
//
////                ///CREAR PERMISOS
//        System.out.println("---CREANDO PERMISOS----");
//        //1
//        id = Long.parseLong("1");
//        permiso.setIdpermisos(id);
//        permiso.setNombrePermiso("T");
//        permiso.setDescripcionPermiso("TOTAL");
//        PDao.crear(permiso);
//        
//        //2
//        id = Long.parseLong("2");
//        permiso.setIdpermisos(id);
//        permiso.setNombrePermiso("L");
//        permiso.setDescripcionPermiso("LECTURA");
//        PDao.crear(permiso);
//
//        //3
//        id = Long.parseLong("3");
//        permiso.setIdpermisos(id);
//        permiso.setNombrePermiso("E");
//        permiso.setDescripcionPermiso("ESCRITURA");
//        PDao.crear(permiso);
//        //4
//        id = Long.parseLong("4");
//        permiso.setIdpermisos(id);
//        permiso.setNombrePermiso("M");
//        permiso.setDescripcionPermiso("MODIFICACION");
//        PDao.crear(permiso);
//        //5
//        id = Long.parseLong("5");
//        permiso.setIdpermisos(id);
//        permiso.setNombrePermiso("I");
//        permiso.setDescripcionPermiso("IMPRIMIR");
//        PDao.crear(permiso);
//        System.out.println("REGISTRO DE PERMISOS CREADOS CON EXITO");
//
//        //CREAR ROL
//        System.out.println("---CREANDO ROLE POR DEFECTO----");
//        id = Long.parseLong("1");
//        rol.setIdrol(id);
//        rol.setNombre("ADMINISTRADOR");
//        rol.setActivo(Boolean.TRUE);
//        RDao.crear(rol);//CREAR
//        rolesLista.add(rol);
//        System.out.println("ROL REGISTRADO CON EXITO ");
////        
//        ///CREAR MODULOS
//        System.out.println("---CREANDO MODULO USUARIO POR DEFECTO----");
//        id = Long.parseLong("1");
//        modulo.setIdmodulo(id);
//        modulo.setNombre("Usuario");
//        modulo.setSrc("usuario.xhtm");
//        modulo.setIcono("fa-user");
//        MoDao.crear(modulo);
//        System.out.println("MODULO CREADO CON EXITO ");
//        //
//        System.out.println("---CREANDO MODULO CONFIGURACION POR DEFECTO----");
//        id = Long.parseLong("2");
//        modulo.setIdmodulo(id);
//        modulo.setNombre("Configuracion");
//        modulo.setSrc("configuracion.xhtm");
//        modulo.setIcono("fa-newspaper-o");
//        MoDao.crear(modulo);
//        System.out.println("MODULO CREADO CON EXITO ");
//
//        //REGISTRAR RELACION ROL MODULO PERMISOS 
//        System.out.println("---ESTABLECIENDO RELACION ROL MODULO PERMISOS----");
//        id = Long.parseLong("1");
//        permisoLista.clear();
//        permiso=PDao.consultarC(Permisos.class, id);
//        permisoLista.add(permiso);
//        RMPDao.registrarRolModuloPermisos(rol, modulo, permisoLista);
//        id = Long.parseLong("1");
//        modulo=MoDao.consultar(Modulo.class, id);
//        RMPDao.registrarRolModuloPermisos(rol, modulo, permisoLista);
//        System.out.println("RELACION ROL MODULO PERMISOS EXITOSA");
//
//        
//        //CRAER MIEMBRO CON USUARIO
//        System.out.println("---CREANDO MIEMBRO CON USUARIO POR DEFECTO----");
//        id = Long.parseLong("1");
//        miembro.setIdmiembro(id);
//        miembro.setNombre1("SUPERADMINISTRADOR");
//        miembro.setApellido1("SISTEMA");
//        miembro.setEstado("ACTIVO");
//        miembro.setDocumento("123456");
//        usuario.setId(id);
//        usuario.setRoles(rolesLista);
//        usuario.setLogin("ADMIN");
//        usuario.setPassword("ADMIN");
//        usuario.setEstado(1);
//        miembro.setUsuario(usuario);
//        MDao.crear(miembro);
//        System.out.println("MIEMBRO CON USUARIO CREADOS EXITOSAMENTE");
//        
//        
//        
//        LISTAR USUARIOS
        miembroLista = MDao.consultarTodo(Miembro.class);
        for (Miembro miebro : miembroLista) {
            System.out.println("SE REGISTRO EL MIEMBRO:"+miebro.getNombre1()+"-"+miebro.getApellido1());
            System.out.println("CON USUARIO:"+miebro.getUsuario().getLogin());
            System.out.println("CON "+miebro.getUsuario().getRoles().size()+" ROLES");
            System.out.println("Y PERMISOS EN EL MODULO "+miebro.getUsuario().getRoles().get(0).getRolModuloPermisoList().get(0).getModulo().getNombre()+" ");
        }
        }catch(Exception ex){
        System.err.println("REGISTROS YA REALIZADOS");
        }

    }
}
