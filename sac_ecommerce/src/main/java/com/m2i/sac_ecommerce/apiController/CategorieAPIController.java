package com.m2i.sac_ecommerce.apiController;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.service.CategorieService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.InvalidObjectException;
import java.net.URI;
import java.sql.SQLException;
import java.util.NoSuchElementException;

    @RestController
    @RequestMapping("/api/categorie")
    public class CategorieAPIController {

        CategorieService catservice;

        public CategorieAPIController(CategorieService catservice) {
            this.catservice= catservice;
        }

        @GetMapping(value = "", produces = "application/json")
        public Iterable<CategorieEntity> getAll(HttpServletRequest request) {
            String search = request.getParameter("search");
            System.out.println("Recherche de categorie avec param = " + search);
            return catservice.findAll(search);

        }

        @GetMapping(value = "/{codeCategorie}", produces = "application/json")
        public ResponseEntity<CategorieEntity> get(@PathVariable int codeCategorie) {
            try {
                CategorieEntity cat = catservice.findCategorie(codeCategorie);
                return ResponseEntity.ok(cat);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping(value = "", consumes = "application/json")
        public ResponseEntity<CategorieEntity> add(@RequestBody CategorieEntity cat) {
            System.out.println(cat);
            try {
                catservice.addCategorie(cat);

                // création de l'url d'accès au nouvel objet => http://localhost:8080/api/categorie/20
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codeCategorie}").buildAndExpand(cat.getCodeCategorie()).toUri();

                return ResponseEntity.created(uri).body(cat);

            } catch (InvalidObjectException e) {
                //return ResponseEntity.badRequest().build();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }


        }

        @PutMapping(value = "/{codeCategorie}", consumes = "application/json")
        public void update(@PathVariable int codeCategorie, @RequestBody CategorieEntity cat) {
            try {
                catservice.editCategorie(codeCategorie, cat);

            } catch (NoSuchElementException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie introuvable");

            } catch (InvalidObjectException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @DeleteMapping(value = "/{codeCategorie}")
        public ResponseEntity<Object> delete(@PathVariable int codeCategorie) {
            // Check sur l'existance de la ville, si ko => 404 not found
            try {
                CategorieEntity cat = catservice.findCategorie(codeCategorie);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }

            // si on a un problème à ce niveau => sql exception
            try {
                catservice.delete(codeCategorie);
                return ResponseEntity.ok(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }

    }
