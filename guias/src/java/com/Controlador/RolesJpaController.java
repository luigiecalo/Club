/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.Entidades.Permisos;
import com.Entidades.Roles;
import java.util.ArrayList;
import java.util.List;
import com.Entidades.Usuarios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LuisGuillermo
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController() {
      this.emf = Persistence.createEntityManagerFactory("guiasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) {
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

    public void edit(Roles roles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getIdroles());
            List<Permisos> permisosListOld = persistentRoles.getPermisosList();
            List<Permisos> permisosListNew = roles.getPermisosList();
            List<Usuarios> usuariosListOld = persistentRoles.getUsuariosList();
            List<Usuarios> usuariosListNew = roles.getUsuariosList();
            List<Permisos> attachedPermisosListNew = new ArrayList<Permisos>();
            for (Permisos permisosListNewPermisosToAttach : permisosListNew) {
                permisosListNewPermisosToAttach = em.getReference(permisosListNewPermisosToAttach.getClass(), permisosListNewPermisosToAttach.getIdpermisos());
                attachedPermisosListNew.add(permisosListNewPermisosToAttach);
            }
            permisosListNew = attachedPermisosListNew;
            roles.setPermisosList(permisosListNew);
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getIdusuarios());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            roles.setUsuariosList(usuariosListNew);
            roles = em.merge(roles);
            for (Permisos permisosListOldPermisos : permisosListOld) {
                if (!permisosListNew.contains(permisosListOldPermisos)) {
                    permisosListOldPermisos.getRolesList().remove(roles);
                    permisosListOldPermisos = em.merge(permisosListOldPermisos);
                }
            }
            for (Permisos permisosListNewPermisos : permisosListNew) {
                if (!permisosListOld.contains(permisosListNewPermisos)) {
                    permisosListNewPermisos.getRolesList().add(roles);
                    permisosListNewPermisos = em.merge(permisosListNewPermisos);
                }
            }
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    usuariosListOldUsuarios.getRolesList().remove(roles);
                    usuariosListOldUsuarios = em.merge(usuariosListOldUsuarios);
                }
            }
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    usuariosListNewUsuarios.getRolesList().add(roles);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roles.getIdroles();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getIdroles();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            List<Permisos> permisosList = roles.getPermisosList();
            for (Permisos permisosListPermisos : permisosList) {
                permisosListPermisos.getRolesList().remove(roles);
                permisosListPermisos = em.merge(permisosListPermisos);
            }
            List<Usuarios> usuariosList = roles.getUsuariosList();
            for (Usuarios usuariosListUsuarios : usuariosList) {
                usuariosListUsuarios.getRolesList().remove(roles);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Roles findRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Roles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
