package com.m2i.sac_ecommerce.repositories;

import com.m2i.sac_ecommerce.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


}
