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
        usuario=UDao.login("ADMIN","ADMIN");
        System.out.println("USUARIO:"+usuario.getLogin());
        miembro= MDao.BuscarMiembroUsuario(usuario);
        System.out.println("Mienbro:"+miembro.getNombre1());
        
        List<Modulo>modulos=RMPDao.buscarModulos(usuario.getRoles().get(0).getIdrol());
        for (Modulo modulo1 : modulos) {
            System.out.println("Los modulos son:"+modulo1.getNombre()+" Con direcion: "+modulo1.getSrc());
        }
    }
}
