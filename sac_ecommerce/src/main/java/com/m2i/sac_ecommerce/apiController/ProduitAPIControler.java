package com.m2i.sac_ecommerce.apiController;

import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.entities.UserEntity;
import com.m2i.sac_ecommerce.service.ProduitService;
import com.m2i.sac_ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;



@RestController
@RequestMapping("/api/produit")
public class ProduitAPIControler {



    private ProduitService ps;

    public ProduitAPIControler( ProduitService ps ){
        this.ps = ps;
    }

    @GetMapping(value="" , produces = "application/json")
    public Iterable<ProduitEntity> getAll(){
        return ps.findAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProduitEntity> get(@PathVariable int id) {
        try{
            ProduitEntity p = ps.findProduit(id);
            return ResponseEntity.ok(p);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="" , consumes = "application/json")
    public ResponseEntity<ProduitEntity> add( @RequestBody ProduitEntity p ){
        System.out.println( p );
        ps.addProduit( p );

        // création de l'url d'accès au nouvel objet => http://localhost:8080/api/ville/20
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdProduit() ).toUri();

        return ResponseEntity.created( uri ).body(p);

    }

    @PutMapping(value="/{id}" , consumes = "application/json")
    public void update( @PathVariable int id , @RequestBody ProduitEntity p ){
        try{
            ps.editProduit( id , p );

        }catch ( NoSuchElementException e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Produit introuvable" );

        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        // Check sur l'existance de la ville, si ko => 404 not found
        try{
            ProduitEntity p = ps.findProduit(id);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }

        // si on a un problème à ce niveau => sql exception
        try{
            ps.delete(id);
            return ResponseEntity.ok(null);
        }catch( Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

}
