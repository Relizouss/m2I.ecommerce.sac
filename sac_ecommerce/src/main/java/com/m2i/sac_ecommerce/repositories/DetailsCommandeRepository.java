package com.m2i.sac_ecommerce.repositories;

import com.m2i.sac_ecommerce.entities.DetailsCommandeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DetailsCommandeRepository extends PagingAndSortingRepository<DetailsCommandeEntity, Integer> {

    //public List<DetailsCommandeEntity> findByNomContains(String search );

   // public Page<DetailsCommandeEntity> findByNomContains(String search , Pageable pageable);

    public Page<DetailsCommandeEntity> findAll(Pageable pageable);


}
