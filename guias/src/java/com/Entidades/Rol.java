/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entidades;

/**
 *
 * @author LuisGuillermo
 */
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author softcomputo
 */
@Entity
@Table(name = "rol")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByNombre", query = "SELECT r FROM Rol r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Rol.findByid", query = "SELECT r FROM Rol r WHERE r.id = :id")})
public class Rol implements Serializable, Comparable<Rol> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private Long id;

    @Column(name = "nombre", length = 30, unique = true, nullable = false)
    private String nombre;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Usuario> usuarios;
    
    @ManyToMany
    @JoinTable(
            name = "rol_perm",
            joinColumns = {
                @JoinColumn(name = "rol_id", referencedColumnName = "idrol")},
            inverseJoinColumns = {
                @JoinColumn(name = "perm_id", referencedColumnName = "idpermisos")})
    private List<Permisos> permisos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permisos> permisos) {
        this.permisos = permisos;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rol other = (Rol) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

  

    @Override
    public int compareTo(Rol o) {
        return this.getNombre().compareTo(o.getNombre());
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", nombre=" + nombre + ", activo=" + activo +'}';
    }
    
    

}
