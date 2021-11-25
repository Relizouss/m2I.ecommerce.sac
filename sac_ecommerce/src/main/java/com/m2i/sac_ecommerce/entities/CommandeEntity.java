package com.m2i.sac_ecommerce.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "commande", schema = "m2i_db_ecommerce", catalog = "")
public class CommandeEntity {
    private int idCommande;
    private Date dateCommande;
    private Timestamp dateLivraison;
    private ClientEntity idClient;

    public CommandeEntity(Date dateCommande, Timestamp dateLivraison, ClientEntity idClient) {
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.idClient = idClient;
    }

    public CommandeEntity() {
    }

    @Id
    @Column(name = "id_commande")
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Basic
    @Column(name = "date_commande")
    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Basic
    @Column(name = "date_livraison")
    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandeEntity that = (CommandeEntity) o;
        return idCommande == that.idCommande && Objects.equals(dateCommande, that.dateCommande) && Objects.equals(dateLivraison, that.dateLivraison);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, dateCommande, dateLivraison);
    }

    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", nullable = false)
    public ClientEntity getIdClient() {
        return idClient;
    }

    public void setIdClient(ClientEntity idClient) {
        this.idClient = idClient;
    }
}
