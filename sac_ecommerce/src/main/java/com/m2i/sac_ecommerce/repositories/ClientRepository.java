package com.m2i.sac_ecommerce.repositories;

import com.m2i.sac_ecommerce.entities.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Integer> {

    public List<ClientEntity> findByNomContains(String search );

    public Page<ClientEntity> findByNomContains(String search , Pageable pageable);

    public Page<ClientEntity> findAll(Pageable pageable);


}