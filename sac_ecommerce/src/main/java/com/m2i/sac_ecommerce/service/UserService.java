package com.m2i.sac_ecommerce.service;


import com.m2i.sac_ecommerce.entities.UserEntity;
import com.m2i.sac_ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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

    public UserEntity findUser(int id) {
        return ur.findById(id).get();
    }

    public void addUser(UserEntity u) {
       // u.setPassword( encoder.encode( u.getPassword() ) );
        ur.save(u);
    }

    public void editUser( int id , UserEntity u) throws NoSuchElementException {
        try{
            UserEntity uExistant = ur.findById(id).get();

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



    public void delete(int id) {
        ur.deleteById(id);
    }


}
