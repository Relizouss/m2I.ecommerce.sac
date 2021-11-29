package com.m2i.sac_ecommerce.service;

import com.m2i.sac_ecommerce.entities.DetailsCommandeEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.repositories.DetailsCommandeRepository;
import com.m2i.sac_ecommerce.repositories.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class DetailsCommandeService {

    private DetailsCommandeRepository dcrepository;
    private ProduitRepository pr;

    public DetailsCommandeService(DetailsCommandeRepository dcrepository, ProduitRepository pr ){
        this.dcrepository = dcrepository;
        this.pr = pr;
    }

   /* public Page<DetailsCommandeEntity> findAllByPage(Integer pageNo, Integer pageSize, String search) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (search != null && search.length() > 0) {
            return dcrepository.findByNomContains(search, paging);
        }

        return dcrepository.findAll(paging);
    }*/


    public Iterable<DetailsCommandeEntity> findAll(String search) {
        return dcrepository.findAll();
    }

    public void addDetailCommande(DetailsCommandeEntity dc) throws InvalidObjectException {
        dcrepository.save(dc);
    }

    public DetailsCommandeEntity findDetailsCommande(int idCommande) {
        return dcrepository.findById(idCommande).get();
    }

    public void editDetailsCommande( int idCommande, DetailsCommandeEntity dc) throws InvalidObjectException {

        try{
            DetailsCommandeEntity dcExistant = dcrepository.findById(idCommande).get();

            dcExistant.setQuantite(dc.getQuantite());
            dcExistant.setTotal(dc.getTotal());
            dcExistant.setIdProduit(dc.getIdProduit());

            dcrepository.save(dcExistant );

        }catch ( NoSuchElementException e ){
            throw e;
        }
    }

    public void delete(int idCommande) {
        dcrepository.deleteById(idCommande);
    }

}
