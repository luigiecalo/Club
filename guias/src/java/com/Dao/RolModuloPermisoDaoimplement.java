/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import static com.Dao.ImplDao.getEmf;
import com.DaonInterface.RolModuloPermisoDao;
import com.Entidades.Rol;
import com.Entidades.RolModuloPermiso;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author LuisGuillermo
 */
public class RolModuloPermisoDaoimplement extends ImplDao<RolModuloPermiso, Long> implements RolModuloPermisoDao, Serializable {
  EntityManager em = getEmf().createEntityManager();

  

}
