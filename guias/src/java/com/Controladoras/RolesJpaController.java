/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controladoras;

import com.Controladoras.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.Entidades.Permisos;
import com.Entidades.Roles;
import java.util.ArrayList;
import java.util.Collection;
import com.Entidades.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
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
        if (roles.getPermisosCollection() == null) {
            roles.setPermisosCollection(new ArrayList<Permisos>());
        }
        if (roles.getUsuariosCollection() == null) {
            roles.setUsuariosCollection(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Permisos> attachedPermisosCollection = new ArrayList<Permisos>();
            for (Permisos permisosCollectionPermisosToAttach : roles.getPermisosCollection()) {
                permisosCollectionPermisosToAttach = em.getReference(permisosCollectionPermisosToAttach.getClass(), permisosCollectionPermisosToAttach.getIdpermisos());
                attachedPermisosCollection.add(permisosCollectionPermisosToAttach);
            }
            roles.setPermisosCollection(attachedPermisosCollection);
            Collection<Usuarios> attachedUsuariosCollection = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionUsuariosToAttach : roles.getUsuariosCollection()) {
                usuariosCollectionUsuariosToAttach = em.getReference(usuariosCollectionUsuariosToAttach.getClass(), usuariosCollectionUsuariosToAttach.getIdusuarios());
                attachedUsuariosCollection.add(usuariosCollectionUsuariosToAttach);
            }
            roles.setUsuariosCollection(attachedUsuariosCollection);
            em.persist(roles);
            for (Permisos permisosCollectionPermisos : roles.getPermisosCollection()) {
                permisosCollectionPermisos.getRolesCollection().add(roles);
                permisosCollectionPermisos = em.merge(permisosCollectionPermisos);
            }
            for (Usuarios usuariosCollectionUsuarios : roles.getUsuariosCollection()) {
                usuariosCollectionUsuarios.getRolesCollection().add(roles);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
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
            Collection<Permisos> permisosCollectionOld = persistentRoles.getPermisosCollection();
            Collection<Permisos> permisosCollectionNew = roles.getPermisosCollection();
            Collection<Usuarios> usuariosCollectionOld = persistentRoles.getUsuariosCollection();
            Collection<Usuarios> usuariosCollectionNew = roles.getUsuariosCollection();
            Collection<Permisos> attachedPermisosCollectionNew = new ArrayList<Permisos>();
            for (Permisos permisosCollectionNewPermisosToAttach : permisosCollectionNew) {
                permisosCollectionNewPermisosToAttach = em.getReference(permisosCollectionNewPermisosToAttach.getClass(), permisosCollectionNewPermisosToAttach.getIdpermisos());
                attachedPermisosCollectionNew.add(permisosCollectionNewPermisosToAttach);
            }
            permisosCollectionNew = attachedPermisosCollectionNew;
            roles.setPermisosCollection(permisosCollectionNew);
            Collection<Usuarios> attachedUsuariosCollectionNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionNewUsuariosToAttach : usuariosCollectionNew) {
                usuariosCollectionNewUsuariosToAttach = em.getReference(usuariosCollectionNewUsuariosToAttach.getClass(), usuariosCollectionNewUsuariosToAttach.getIdusuarios());
                attachedUsuariosCollectionNew.add(usuariosCollectionNewUsuariosToAttach);
            }
            usuariosCollectionNew = attachedUsuariosCollectionNew;
            roles.setUsuariosCollection(usuariosCollectionNew);
            roles = em.merge(roles);
            for (Permisos permisosCollectionOldPermisos : permisosCollectionOld) {
                if (!permisosCollectionNew.contains(permisosCollectionOldPermisos)) {
                    permisosCollectionOldPermisos.getRolesCollection().remove(roles);
                    permisosCollectionOldPermisos = em.merge(permisosCollectionOldPermisos);
                }
            }
            for (Permisos permisosCollectionNewPermisos : permisosCollectionNew) {
                if (!permisosCollectionOld.contains(permisosCollectionNewPermisos)) {
                    permisosCollectionNewPermisos.getRolesCollection().add(roles);
                    permisosCollectionNewPermisos = em.merge(permisosCollectionNewPermisos);
                }
            }
            for (Usuarios usuariosCollectionOldUsuarios : usuariosCollectionOld) {
                if (!usuariosCollectionNew.contains(usuariosCollectionOldUsuarios)) {
                    usuariosCollectionOldUsuarios.getRolesCollection().remove(roles);
                    usuariosCollectionOldUsuarios = em.merge(usuariosCollectionOldUsuarios);
                }
            }
            for (Usuarios usuariosCollectionNewUsuarios : usuariosCollectionNew) {
                if (!usuariosCollectionOld.contains(usuariosCollectionNewUsuarios)) {
                    usuariosCollectionNewUsuarios.getRolesCollection().add(roles);
                    usuariosCollectionNewUsuarios = em.merge(usuariosCollectionNewUsuarios);
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
            Collection<Permisos> permisosCollection = roles.getPermisosCollection();
            for (Permisos permisosCollectionPermisos : permisosCollection) {
                permisosCollectionPermisos.getRolesCollection().remove(roles);
                permisosCollectionPermisos = em.merge(permisosCollectionPermisos);
            }
            Collection<Usuarios> usuariosCollection = roles.getUsuariosCollection();
            for (Usuarios usuariosCollectionUsuarios : usuariosCollection) {
                usuariosCollectionUsuarios.getRolesCollection().remove(roles);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
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
