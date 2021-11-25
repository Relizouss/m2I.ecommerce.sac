package com.m2i.sac_ecommerce.repositories;


import com.m2i.sac_ecommerce.entities.CategorieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategorieRepository extends PagingAndSortingRepository<CategorieEntity, Integer> {

    public List<CategorieEntity> findByNomContains(String search );

    public Page<CategorieEntity> findByNomContains(String search , Pageable pageable);

    public Page<CategorieEntity> findAll(Pageable pageable);

}