package com.m2i.sac_ecommerce.apiController;

import com.m2i.sac_ecommerce.entities.DetailsCommandeEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.service.DetailsCommandeService;
import com.m2i.sac_ecommerce.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.InvalidObjectException;
import java.net.URI;
import java.util.NoSuchElementException;



@RestController
@RequestMapping("/api/detailscommande")
public class DetailsCommandeAPIController {

    private DetailsCommandeService dcservice;

    public DetailsCommandeAPIController( DetailsCommandeService dcservice ){
        this.dcservice = dcservice;
    }

    @GetMapping(value="" , produces = "application/json")
    public Iterable<DetailsCommandeEntity> getAll(HttpServletRequest request){
        String search = request.getParameter("search");
        return dcservice.findAll(search);
    }


    @GetMapping(value = "/{idCommande}", produces = "application/json")
    public ResponseEntity<DetailsCommandeEntity> get(@PathVariable int idCommande) {
        try{
            DetailsCommandeEntity dc = dcservice.findDetailsCommande(idCommande);
            return ResponseEntity.ok(dc);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="" , consumes = "application/json")
    public ResponseEntity<DetailsCommandeEntity> add( @RequestBody DetailsCommandeEntity dc ){
        System.out.println( dc );
        try {
        dcservice.addDetailCommande( dc );

        // création de l'url d'accès au nouvel objet => http://localhost:8080/api/detailscommande/20
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCommande}").buildAndExpand(dc.getIdCommande() ).toUri();

        return ResponseEntity.created( uri ).body(dc);


        } catch (InvalidObjectException e) {
            //return ResponseEntity.badRequest().build();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value="/{idCommande}" , consumes = "application/json")
    public void update( @PathVariable int idCommande , @RequestBody DetailsCommandeEntity dc ){
        try{
           dcservice.editDetailsCommande( idCommande, dc );

        }catch (NoSuchElementException | InvalidObjectException e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Detail Commande introuvable" );

        }
    }

    @DeleteMapping(value = "/{idCommande}")
    public ResponseEntity<Object> delete(@PathVariable int idCommande) {
        // Check sur l'existance de details commande, si ko => 404 not found
        try{
            DetailsCommandeEntity dc = dcservice.findDetailsCommande(idCommande);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }

        // si on a un problème à ce niveau => sql exception
        try{
            dcservice.delete(idCommande);
            return ResponseEntity.ok(null);
        }catch( Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

}