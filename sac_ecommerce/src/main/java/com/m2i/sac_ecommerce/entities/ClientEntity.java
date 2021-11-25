package com.m2i.sac_ecommerce.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "m2i_db_ecommerce", catalog = "")
public class ClientEntity {
    private int idClient;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pays;
    private String telephone;
    private String email;
    private String password;
    private String carteNumero;

    @Id
    @Column(name = "id_client", nullable = false)
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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
    @Column(name = "prenom", nullable = false, length = 100)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "date_naissance", nullable = false)
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Basic
    @Column(name = "adresse", nullable = false, length = 100)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "ville", nullable = false, length = 100)
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "code_postal", nullable = false, length = 100)
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Basic
    @Column(name = "pays", nullable = false, length = 100)
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Basic
    @Column(name = "telephone", nullable = false, length = 100)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 300)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "carte_numero", nullable = false, length = 100)
    public String getCarteNumero() {
        return carteNumero;
    }

    public void setCarteNumero(String carteNumero) {
        this.carteNumero = carteNumero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return idClient == that.idClient && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(dateNaissance, that.dateNaissance) && Objects.equals(adresse, that.adresse) && Objects.equals(ville, that.ville) && Objects.equals(codePostal, that.codePostal) && Objects.equals(pays, that.pays) && Objects.equals(telephone, that.telephone) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(carteNumero, that.carteNumero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, nom, prenom, dateNaissance, adresse, ville, codePostal, pays, telephone, email, password, carteNumero);
    }
}
