package com.m2i.sac_ecommerce.service;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.entities.UserEntity;
import com.m2i.sac_ecommerce.repositories.ProduitRepository;
import com.m2i.sac_ecommerce.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class ProduitService {

    private ProduitRepository pr;



    public ProduitService(ProduitRepository pr ){
        this.pr = pr;
    }

    public Iterable<ProduitEntity> findAll(  ) {
        return pr.findAll();
    }

    public ProduitEntity findProduit(int id) {
        return pr.findById(id).get();
    }

    public void addProduit(ProduitEntity p) {
        // u.setPassword( encoder.encode( u.getPassword() ) );
        pr.save(p);
    }

    public void editProduit( int id , ProduitEntity p) throws NoSuchElementException {
        try{
            ProduitEntity pExistant = pr.findById(id).get();

            pExistant.setMarque( p.getMarque() );
            pExistant.setModele( p.getModele() );
            pExistant.setPrixAchat( p.getPrixAchat() );
            pExistant.setPrixVente( p.getPrixVente());
            pExistant.setQuantiteStock( p.getQuantiteStock() );
            pExistant.setIndisponible( p.getIndisponible() );
            pExistant.setPhotoProduit( p.getPhotoProduit());
            pExistant.setCodeCategtorie( p.getCodeCategtorie() );

            pr.save( pExistant );
        }catch ( NoSuchElementException e ){
            throw e;
        }
    }

    public Page<ProduitEntity> findAllByPage(Integer pageNo, Integer pageSize , String search  ) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if( search != null && search.length() > 0 ){
            return pr.findByMarque(search, paging );
        }

        return pr.findAll( paging );
    }

    public void delete(int id) {
        pr.deleteById(id);
    }



}
