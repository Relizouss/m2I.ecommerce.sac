package com.m2i.sac_ecommerce.service;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.entities.ClientEntity;
import com.m2i.sac_ecommerce.entities.CommandeEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.repositories.CommandeRepository;
import com.m2i.sac_ecommerce.repositories.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.NoSuchElementException;

@Service
public class CommandeService {

    private CommandeRepository cr;



    public CommandeService(CommandeRepository cr ){
        this.cr = cr;
    }

    public Iterable<CommandeEntity> findAll(  ) {
        return cr.findAll();
    }
    public Iterable<CommandeEntity> findAll(String search  ) {
        if( search != null && search.length() > 0 ){
            return cr.findByIdClient(search);
        }
        return cr.findAll();
    }
    public CommandeEntity findCommande(int id) {
        return cr.findById(id).get();
    }

    public void addCommande(CommandeEntity c) {
        // u.setPassword( encoder.encode( u.getPassword() ) );
        cr.save(c);
    }

    public void editCommande( int id , CommandeEntity c) throws NoSuchElementException {
        try{
            CommandeEntity cExistant = cr.findById(id).get();

            cExistant.setDateCommande( c.getDateCommande() );
            cExistant.setDateLivraison( c.getDateLivraison() );
            cExistant.setIdClient( c.getIdClient() );

            cr.save( cExistant );
        }catch ( NoSuchElementException e ){
            throw e;
        }
    }

    public Page<CommandeEntity> findAllByPage(Integer pageNo, Integer pageSize , String search  ) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if( search != null && search.length() > 0 ){
            return cr.findByDateCommande(search, paging );
        }

        return cr.findAll( paging );
    }

    public void delete(int id) {
        cr.deleteById(id);
    }






}
