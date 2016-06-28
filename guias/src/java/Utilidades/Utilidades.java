/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import com.Entidades.Modulo;
import com.Entidades.RolModuloPermiso;
import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Utilidades implements Serializable{

   
    public boolean registro(Long idRol, Modulo modulo) {
        boolean result = false;
        for (RolModuloPermiso rolmodulospermiso : modulo.getRolModuloPermisoList()) {
            if(rolmodulospermiso.getRol().getIdrol().equals(idRol)){
            if (rolmodulospermiso.getPermisos().getNombrePermiso().equals("E") || rolmodulospermiso.getPermisos().getNombrePermiso().equals("T")) {
                result = true;
            }
            }
            
        }
        return result;
    }
    
     public static long toLong(Number number) {
        return number.longValue();
    }
}
