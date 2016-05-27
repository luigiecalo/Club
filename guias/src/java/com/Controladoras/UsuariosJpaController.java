/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controladoras;

import com.Controladoras.exceptions.IllegalOrphanException;
import com.Controladoras.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.Entidades.Roles;
import java.util.ArrayList;
import java.util.Collection;
import com.Entidades.Miembro;
import com.Entidades.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController() {
      this.emf = Persistence.createEntityManagerFactory("guiasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getRolesCollection() == null) {
            usuarios.setRolesCollection(new ArrayList<Roles>());
        }
        if (usuarios.getMiembroCollection() == null) {
            usuarios.setMiembroCollection(new ArrayList<Miembro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Roles> attachedRolesCollection = new ArrayList<Roles>();
            for (Roles rolesCollectionRolesToAttach : usuarios.getRolesCollection()) {
                rolesCollectionRolesToAttach = em.getReference(rolesCollectionRolesToAttach.getClass(), rolesCollectionRolesToAttach.getIdroles());
                attachedRolesCollection.add(rolesCollectionRolesToAttach);
            }
            usuarios.setRolesCollection(attachedRolesCollection);
            Collection<Miembro> attachedMiembroCollection = new ArrayList<Miembro>();
            for (Miembro miembroCollectionMiembroToAttach : usuarios.getMiembroCollection()) {
                miembroCollectionMiembroToAttach = em.getReference(miembroCollectionMiembroToAttach.getClass(), miembroCollectionMiembroToAttach.getMiembroPK());
                attachedMiembroCollection.add(miembroCollectionMiembroToAttach);
            }
            usuarios.setMiembroCollection(attachedMiembroCollection);
            em.persist(usuarios);
            for (Roles rolesCollectionRoles : usuarios.getRolesCollection()) {
                rolesCollectionRoles.getUsuariosCollection().add(usuarios);
                rolesCollectionRoles = em.merge(rolesCollectionRoles);
            }
            for (Miembro miembroCollectionMiembro : usuarios.getMiembroCollection()) {
                Usuarios oldUsuariosOfMiembroCollectionMiembro = miembroCollectionMiembro.getUsuarios();
                miembroCollectionMiembro.setUsuarios(usuarios);
                miembroCollectionMiembro = em.merge(miembroCollectionMiembro);
                if (oldUsuariosOfMiembroCollectionMiembro != null) {
                    oldUsuariosOfMiembroCollectionMiembro.getMiembroCollection().remove(miembroCollectionMiembro);
                    oldUsuariosOfMiembroCollectionMiembro = em.merge(oldUsuariosOfMiembroCollectionMiembro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdusuarios());
            Collection<Roles> rolesCollectionOld = persistentUsuarios.getRolesCollection();
            Collection<Roles> rolesCollectionNew = usuarios.getRolesCollection();
            Collection<Miembro> miembroCollectionOld = persistentUsuarios.getMiembroCollection();
            Collection<Miembro> miembroCollectionNew = usuarios.getMiembroCollection();
            List<String> illegalOrphanMessages = null;
            for (Miembro miembroCollectionOldMiembro : miembroCollectionOld) {
                if (!miembroCollectionNew.contains(miembroCollectionOldMiembro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Miembro " + miembroCollectionOldMiembro + " since its usuarios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Roles> attachedRolesCollectionNew = new ArrayList<Roles>();
            for (Roles rolesCollectionNewRolesToAttach : rolesCollectionNew) {
                rolesCollectionNewRolesToAttach = em.getReference(rolesCollectionNewRolesToAttach.getClass(), rolesCollectionNewRolesToAttach.getIdroles());
                attachedRolesCollectionNew.add(rolesCollectionNewRolesToAttach);
            }
            rolesCollectionNew = attachedRolesCollectionNew;
            usuarios.setRolesCollection(rolesCollectionNew);
            Collection<Miembro> attachedMiembroCollectionNew = new ArrayList<Miembro>();
            for (Miembro miembroCollectionNewMiembroToAttach : miembroCollectionNew) {
                miembroCollectionNewMiembroToAttach = em.getReference(miembroCollectionNewMiembroToAttach.getClass(), miembroCollectionNewMiembroToAttach.getMiembroPK());
                attachedMiembroCollectionNew.add(miembroCollectionNewMiembroToAttach);
            }
            miembroCollectionNew = attachedMiembroCollectionNew;
            usuarios.setMiembroCollection(miembroCollectionNew);
            usuarios = em.merge(usuarios);
            for (Roles rolesCollectionOldRoles : rolesCollectionOld) {
                if (!rolesCollectionNew.contains(rolesCollectionOldRoles)) {
                    rolesCollectionOldRoles.getUsuariosCollection().remove(usuarios);
                    rolesCollectionOldRoles = em.merge(rolesCollectionOldRoles);
                }
            }
            for (Roles rolesCollectionNewRoles : rolesCollectionNew) {
                if (!rolesCollectionOld.contains(rolesCollectionNewRoles)) {
                    rolesCollectionNewRoles.getUsuariosCollection().add(usuarios);
                    rolesCollectionNewRoles = em.merge(rolesCollectionNewRoles);
                }
            }
            for (Miembro miembroCollectionNewMiembro : miembroCollectionNew) {
                if (!miembroCollectionOld.contains(miembroCollectionNewMiembro)) {
                    Usuarios oldUsuariosOfMiembroCollectionNewMiembro = miembroCollectionNewMiembro.getUsuarios();
                    miembroCollectionNewMiembro.setUsuarios(usuarios);
                    miembroCollectionNewMiembro = em.merge(miembroCollectionNewMiembro);
                    if (oldUsuariosOfMiembroCollectionNewMiembro != null && !oldUsuariosOfMiembroCollectionNewMiembro.equals(usuarios)) {
                        oldUsuariosOfMiembroCollectionNewMiembro.getMiembroCollection().remove(miembroCollectionNewMiembro);
                        oldUsuariosOfMiembroCollectionNewMiembro = em.merge(oldUsuariosOfMiembroCollectionNewMiembro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdusuarios();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdusuarios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Miembro> miembroCollectionOrphanCheck = usuarios.getMiembroCollection();
            for (Miembro miembroCollectionOrphanCheckMiembro : miembroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Miembro " + miembroCollectionOrphanCheckMiembro + " in its miembroCollection field has a non-nullable usuarios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Roles> rolesCollection = usuarios.getRolesCollection();
            for (Roles rolesCollectionRoles : rolesCollection) {
                rolesCollectionRoles.getUsuariosCollection().remove(usuarios);
                rolesCollectionRoles = em.merge(rolesCollectionRoles);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
