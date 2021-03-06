package com.m2i.sac_ecommerce.service;


import com.m2i.sac_ecommerce.entities.UserEntity;
import com.m2i.sac_ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private UserRepository ur;

    // @Autowired
    // private PasswordEncoder encoder;

    public UserService(UserRepository ur ){
        this.ur = ur;
    }

    public Iterable<UserEntity> findAll(  ) {
        return ur.findAll();
    }

    public UserEntity findUser(int idUser) {
        return ur.findById(idUser).get();
    }

    public void addUser(UserEntity u) {
       // u.setPassword( encoder.encode( u.getPassword() ) );
        ur.save(u);
    }

    public void editUser( int idUser , UserEntity u) throws NoSuchElementException {
        try{
            UserEntity uExistant = ur.findById(idUser).get();

            uExistant.setIdentifiant( u.getIdentifiant() );
            uExistant.setNom( u.getNom() );
            uExistant.setEmail( u.getEmail() );
            uExistant.setRole( u.getRole());
            uExistant.setPassword( u.getPassword() );

            //  if( u.getPassword().length() > 0 ){
            //  uExistant.setPassword( encoder.encode( u.getPassword() ) );
            //  }
            ur.save( uExistant );
        }catch ( NoSuchElementException e ){
            throw e;
        }
    }

    public Page<UserEntity> findAllByPage(Integer pageNo, Integer pageSize , String search  ) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if( search != null && search.length() > 0 ){
            return ur.findByNomContains(search, paging );
        }

        return ur.findAll( paging );
    }

    public void delete(int idUser) {
        ur.deleteById(idUser);
    }


}
