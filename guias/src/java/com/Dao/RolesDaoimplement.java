/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.DaonInterface.RolesDao;
import com.Entidades.Permisos;
import com.Entidades.Roles;
import com.Entidades.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author LuisGuillermo
 */
public class RolesDaoimplement extends ImplDao<Roles, Long> implements RolesDao, Serializable {

    EntityManager em = getEmf().createEntityManager();

    public List<Roles> Listar() {
        return em.createNamedQuery("Roles.findAll").getResultList();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void guardarcompleto(Roles roles) {
        if (roles.getPermisosList() == null) {
            roles.setPermisosList(new ArrayList<Permisos>());
        }
        if (roles.getUsuariosList() == null) {
            roles.setUsuariosList(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Permisos> attachedPermisosList = new ArrayList<Permisos>();
            for (Permisos permisosListPermisosToAttach : roles.getPermisosList()) {
                permisosListPermisosToAttach = em.getReference(permisosListPermisosToAttach.getClass(), permisosListPermisosToAttach.getIdpermisos());
                attachedPermisosList.add(permisosListPermisosToAttach);
            }
            roles.setPermisosList(attachedPermisosList);
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : roles.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getIdusuarios());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            roles.setUsuariosList(attachedUsuariosList);
            em.persist(roles);
            for (Permisos permisosListPermisos : roles.getPermisosList()) {
                permisosListPermisos.getRolesList().add(roles);
                permisosListPermisos = em.merge(permisosListPermisos);
            }
            for (Usuarios usuariosListUsuarios : roles.getUsuariosList()) {
                usuariosListUsuarios.getRolesList().add(roles);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public void editarCompleto(Roles roles) {
        RolesDaoimplement RolesDao = new RolesDaoimplement();
        RolesDao.eliminar(roles);
        guardarcompleto(roles);
    }
}
