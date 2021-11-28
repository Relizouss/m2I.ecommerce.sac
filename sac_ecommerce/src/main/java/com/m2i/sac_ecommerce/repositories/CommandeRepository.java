package com.m2i.sac_ecommerce.repositories;

import com.m2i.sac_ecommerce.entities.CommandeEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommandeRepository extends PagingAndSortingRepository<CommandeEntity, Integer> {

    public List<CommandeEntity> findByIdClient(String search );

    public Page<CommandeEntity> findByDateCommande(String search , Pageable pageable);

    public Page<CommandeEntity> findAll(Pageable pageable);


}
