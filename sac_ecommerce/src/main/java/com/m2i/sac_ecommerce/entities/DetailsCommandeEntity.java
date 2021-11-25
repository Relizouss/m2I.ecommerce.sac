package com.m2i.sac_ecommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "details_commande", schema = "m2i_db_ecommerce", catalog = "")
public class DetailsCommandeEntity {
    private int idCommande;
    private int quantite;
    private double total;

    @Id
    @Column(name = "id_commande", nullable = false)
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Basic
    @Column(name = "quantite", nullable = false)
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsCommandeEntity that = (DetailsCommandeEntity) o;
        return idCommande == that.idCommande && quantite == that.quantite && Double.compare(that.total, total) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, quantite, total);
    }
}
