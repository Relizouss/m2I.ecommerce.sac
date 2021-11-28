package com.m2i.sac_ecommerce.repositories;

import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProduitRepository extends PagingAndSortingRepository<ProduitEntity, Integer> {

    public List<ProduitEntity> findByMarque(String search );

    public Page<ProduitEntity> findByMarque(String search , Pageable pageable);

    public Page<ProduitEntity> findAll(Pageable pageable);


}
