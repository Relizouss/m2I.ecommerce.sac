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
    private byte indisponible;
    private String photoProduit;
    private CategorieEntity categorieByCodeCategtorie;

    @Id
    @Column(name = "id_produit", nullable = false)
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Basic
    @Column(name = "marque", nullable = false, length = 100)
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Basic
    @Column(name = "modele", nullable = false, length = 100)
    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Basic
    @Column(name = "prix_achat", nullable = false, precision = 0)
    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Basic
    @Column(name = "prix_vente", nullable = false, precision = 0)
    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    @Basic
    @Column(name = "quantite_stock", nullable = false)
    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    @Basic
    @Column(name = "indisponible", nullable = false)
    public byte getIndisponible() {
        return indisponible;
    }

    public void setIndisponible(byte indisponible) {
        this.indisponible = indisponible;
    }

    @Basic
    @Column(name = "photo_produit", nullable = false, length = 500)
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

    @ManyToOne
    @JoinColumn(name = "code_categtorie", referencedColumnName = "code_categorie", nullable = false)
    public CategorieEntity getCategorieByCodeCategtorie() {
        return categorieByCodeCategtorie;
    }

    public void setCategorieByCodeCategtorie(CategorieEntity categorieByCodeCategtorie) {
        this.categorieByCodeCategtorie = categorieByCodeCategtorie;
    }
}
