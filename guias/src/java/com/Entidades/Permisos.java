/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LuisGuillermo
 */
@Entity
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p"),
    @NamedQuery(name = "Permisos.findByIdpermisos", query = "SELECT p FROM Permisos p WHERE p.idpermisos = :idpermisos"),
    @NamedQuery(name = "Permisos.findByNombrePermiso", query = "SELECT p FROM Permisos p WHERE p.nombrePermiso = :nombrePermiso"),
    @NamedQuery(name = "Permisos.findByDescripcionPermiso", query = "SELECT p FROM Permisos p WHERE p.descripcionPermiso = :descripcionPermiso")})
public class Permisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpermisos")
    private Integer idpermisos;
    @Basic(optional = false)
    @Column(name = "nombre_permiso")
    private String nombrePermiso;
    @Basic(optional = false)
    @Column(name = "descripcion_permiso")
    private String descripcionPermiso;
    @JoinTable(name = "roles_permisos", joinColumns = {
        @JoinColumn(name = "idpermisos", referencedColumnName = "idpermisos")}, inverseJoinColumns = {
        @JoinColumn(name = "idroles", referencedColumnName = "idroles")})
    @ManyToMany
    private List<Roles> rolesList;
    
    public Permisos() {
    }

    public Permisos(Integer idpermisos) {
        this.idpermisos = idpermisos;
    }

    public Permisos(Integer idpermisos, String nombrePermiso, String descripcionPermiso) {
        this.idpermisos = idpermisos;
        this.nombrePermiso = nombrePermiso;
        this.descripcionPermiso = descripcionPermiso;
    }

    public Integer getIdpermisos() {
        return idpermisos;
    }

    public void setIdpermisos(Integer idpermisos) {
        this.idpermisos = idpermisos;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    @XmlTransient
    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpermisos != null ? idpermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idpermisos == null && other.idpermisos != null) || (this.idpermisos != null && !this.idpermisos.equals(other.idpermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entidades.Permisos[ idpermisos=" + idpermisos + " ]";
    }
    
}
