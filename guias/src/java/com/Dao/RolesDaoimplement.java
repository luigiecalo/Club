/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.DaonInterface.RolesDao;
import com.Entidades.Permisos;
import com.Entidades.Roles;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author LuisGuillermo
 */
public class RolesDaoimplement extends ImplDao<Roles, Long> implements  RolesDao, Serializable {
    EntityManager em=getEmf().createEntityManager();
    public List<Roles> Listar() {
        return em.createNamedQuery("Roles.findAll").getResultList();
    }

}
