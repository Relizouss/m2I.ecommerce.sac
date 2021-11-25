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

    @Id
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "identifiant", nullable = false, length = 50)
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 150)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 100)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 250)
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
