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

    public ClientEntity() {
    }

    public ClientEntity(String nom, String prenom, Date dateNaissance, String adresse, String ville, String codePostal, String pays, String telephone, String email, String password, String carteNumero) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.carteNumero = carteNumero;
    }

    @Id
    @Column(name = "id_client")
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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
    @Column(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "date_naissance")
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Basic
    @Column(name = "adresse")
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "ville")
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "code_postal")
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Basic
    @Column(name = "pays")
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "carte_numero")
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
