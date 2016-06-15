/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import static com.Dao.ImplDao.getEmf;
import com.DaonInterface.RolModuloPermisoDao;
import com.Entidades.Modulo;
import com.Entidades.Permisos;
import com.Entidades.Rol;
import com.Entidades.RolModuloPermiso;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author LuisGuillermo
 */
public class RolModuloPermisoDaoimplement extends ImplDao<RolModuloPermiso, Long> implements RolModuloPermisoDao, Serializable {

    EntityManager em = getEmf().createEntityManager();
    private static RolModuloPermisoDaoimplement RMPDao = new RolModuloPermisoDaoimplement();

    public void registrarRolModuloPermisos(Rol rol, Modulo mod, List<Permisos> permisos) {
        List<RolModuloPermiso> rolmoduloPermisos = buscarPermisos(rol.getIdrol(), mod.getIdmodulo());
        if (rolmoduloPermisos == null) {
            for (Permisos permiso : permisos) {
                RolModuloPermiso rolmodulper = new RolModuloPermiso(rol, mod, permiso);
                RMPDao.crear(rolmodulper);
            }
        } else {
            for (RolModuloPermiso rolmoduloPermiso : rolmoduloPermisos) {
                for (Permisos permiso : permisos) {
                    if (!permiso.equals(rolmoduloPermiso.getPermisos())) {
                        RMPDao.eliminar(rolmoduloPermiso);
                    }
                }
            }
           for (Permisos permiso : permisos) {
                RolModuloPermiso rolmodulper = new RolModuloPermiso(rol, mod, permiso);
                RMPDao.crear(rolmodulper);
            }

        }

    }

    public List<RolModuloPermiso> buscarPermisos(Long idrol, Long idmodulo) {
        Query query = em.createNamedQuery(RolModuloPermiso.BUSCAR_PERMISOS_MODULO_ROL);
        query.setParameter("idrol", idrol);
        query.setParameter("idmodulo", idmodulo);
        List<RolModuloPermiso> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
     public List<Long> buscarModulos(Long idrol) {
        Query query = em.createNativeQuery("SELECT DISTINCT r.idmodulo FROM rol_modulo_permiso r WHERE r.idrol='"+idrol+"'" );
        List<Long> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
     
     

}
