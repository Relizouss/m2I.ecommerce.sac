package com.m2i.sac_ecommerce.apiController;


import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.entities.CommandeEntity;
import com.m2i.sac_ecommerce.service.CategorieService;
import com.m2i.sac_ecommerce.service.CommandeService;
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
@RequestMapping("/api/commande")
public class CommandeAPIController {

    CommandeService comservice;

    public CommandeAPIController(CommandeService comservice) {
        this.comservice = comservice;
    }

    @GetMapping(value = "", produces = "application/json")
    public Iterable<CommandeEntity> getAll(HttpServletRequest request) {
        String search = request.getParameter("search");
        System.out.println("Recherche de commande avec param = " + search);
        return comservice.findAll(search);

    }

    @GetMapping(value = "/{idCommande}", produces = "application/json")
    public ResponseEntity<CommandeEntity> get(@PathVariable int idCommande) {
        try {
            CommandeEntity com = comservice.findCommande(idCommande);
            return ResponseEntity.ok(com);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<CommandeEntity> add(@RequestBody CommandeEntity com) {
        System.out.println(com);
        try {
            comservice.addCommande(com);

            // création de l'url d'accès au nouvel objet => http://localhost:8080/api/commande/20
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCommande}").buildAndExpand(com.getIdCommande()).toUri();
            return ResponseEntity.created(uri).body(com);
       // } catch (InvalidObjectException e) {
        } catch (Exception e) {
            //return ResponseEntity.badRequest().build();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/{idCommande}", consumes = "application/json")
    public void update(@PathVariable int idCommande, @RequestBody CommandeEntity com) {
        try {
            comservice.editCommande(idCommande, com);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande introuvable");

//        } catch (InvalidObjectException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
        }
    }

    @DeleteMapping(value = "/{idCommande}")
    public ResponseEntity<Object> delete(@PathVariable int idCommande) {
        // Check sur l'existance de la commande, si ko => 404 not found
        try {
            CommandeEntity commande = comservice.findCommande(idCommande);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        // si on a un problème à ce niveau => sql exception
        try {
            comservice.delete(idCommande);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }



}
