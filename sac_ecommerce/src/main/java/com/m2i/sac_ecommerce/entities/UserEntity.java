package com.m2i.sac_ecommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "m2i_db_ecommerce", catalog = "")
public class UserEntity {
    private int idUser;
    private String identifiant;
    private String nom;
    private String email;
    private String role;
    private String password;

    public UserEntity() {
    }

    public UserEntity(String identifiant, String nom, String email, String role, String password) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "identifiant")
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idUser == that.idUser && Objects.equals(identifiant, that.identifiant) && Objects.equals(nom, that.nom) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, identifiant, nom, email, role, password);
    }
}
