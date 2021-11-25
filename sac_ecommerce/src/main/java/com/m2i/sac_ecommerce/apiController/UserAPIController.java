package com.m2i.sac_ecommerce.apiController;


import com.m2i.sac_ecommerce.entities.UserEntity;
import com.m2i.sac_ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InvalidObjectException;
import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class UserAPIController {

    UserService us;

    public UserAPIController( UserService us ){
        this.us = us;
    }

    @GetMapping(value="" , produces = "application/json")
    public Iterable<UserEntity> getAll(){
        return us.findAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserEntity> get(@PathVariable int id) {
        try{
            UserEntity v = us.findUser(id);
            return ResponseEntity.ok(v);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="" , consumes = "application/json")
    public ResponseEntity<UserEntity> add( @RequestBody UserEntity u ){
        System.out.println( u );
        us.addUser( u );

        // création de l'url d'accès au nouvel objet => http://localhost:8080/api/ville/20
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand( u.getIdUser() ).toUri();

        return ResponseEntity.created( uri ).body(u);

    }

    @PutMapping(value="/{id}" , consumes = "application/json")
    public void update( @PathVariable int id , @RequestBody UserEntity u ){
        try{
            us.editUser( id , u );

        }catch ( NoSuchElementException e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "User introuvable" );

        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        // Check sur l'existance de la ville, si ko => 404 not found
        try{
            UserEntity v = us.findUser(id);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }

        // si on a un problème à ce niveau => sql exception
        try{
            us.delete(id);
            return ResponseEntity.ok(null);
        }catch( Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }


}