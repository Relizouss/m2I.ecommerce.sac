package com.m2i.sac_ecommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "produit", schema = "m2i_db_ecommerce", catalog = "")
public class ProduitEntity {
    private int idProduit;
    private String marque;
    private String modele;
    private double prixAchat;
    private double prixVente;
    private int quantiteStock;
    private String indisponible;
    private String photoProduit;
    private CategorieEntity codeCategtorie;

    public ProduitEntity() {
    }

    public ProduitEntity(String marque, String modele, double prixAchat, double prixVente, int quantiteStock, String indisponible, String photoProduit, CategorieEntity codeCategtorie) {
        this.marque = marque;
        this.modele = modele;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.quantiteStock = quantiteStock;
        this.indisponible = indisponible;
        this.photoProduit = photoProduit;
        this.codeCategtorie = codeCategtorie;
    }

    @Id
    @Column(name = "id_produit")
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Basic
    @Column(name = "marque")
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Basic
    @Column(name = "modele")
    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Basic
    @Column(name = "prix_achat")
    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Basic
    @Column(name = "prix_vente")
    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    @Basic
    @Column(name = "quantite_stock")
    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    @Basic
    @Column(name = "indisponible")
    public String getIndisponible() {
        return indisponible;
    }

    public void setIndisponible(String indisponible) {
        this.indisponible = indisponible;
    }

    @Basic
    @Column(name = "photo_produit")
    public String getPhotoProduit() {
        return photoProduit;
    }

    public void setPhotoProduit(String photoProduit) {
        this.photoProduit = photoProduit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitEntity that = (ProduitEntity) o;
        return idProduit == that.idProduit && Double.compare(that.prixAchat, prixAchat) == 0 && Double.compare(that.prixVente, prixVente) == 0 && quantiteStock == that.quantiteStock && indisponible == that.indisponible && Objects.equals(marque, that.marque) && Objects.equals(modele, that.modele) && Objects.equals(photoProduit, that.photoProduit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduit, marque, modele, prixAchat, prixVente, quantiteStock, indisponible, photoProduit);
    }

    @OneToOne
    @JoinColumn(name = "code_categtorie", referencedColumnName = "code_categorie", nullable = false)
    public CategorieEntity getCodeCategtorie() {
        return codeCategtorie;
    }

    public void setCodeCategtorie(CategorieEntity codeCategtorie) {
        this.codeCategtorie = codeCategtorie;
    }
}
