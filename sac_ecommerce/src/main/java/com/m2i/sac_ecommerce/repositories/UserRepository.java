package com.m2i.sac_ecommerce.repositories;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    public List<UserEntity> findByNomContains(String search );

    public Page<UserEntity> findByNomContains(String search , Pageable pageable);

    public Page<UserEntity> findAll(Pageable pageable);

}
