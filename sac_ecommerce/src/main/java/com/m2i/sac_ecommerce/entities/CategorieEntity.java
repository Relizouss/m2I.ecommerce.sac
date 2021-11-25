package com.m2i.sac_ecommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorie", schema = "m2i_db_ecommerce", catalog = "")
public class CategorieEntity {
    private int codeCategorie;
    private String nom;
    private String description;

    @Id
    @Column(name = "code_categorie", nullable = false)
    public int getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(int codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 100)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieEntity that = (CategorieEntity) o;
        return codeCategorie == that.codeCategorie && Objects.equals(nom, that.nom) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeCategorie, nom, description);
    }
}
