package com.m2i.sac_ecommerce.apiController;

import com.m2i.sac_ecommerce.entities.ClientEntity;
import com.m2i.sac_ecommerce.service.ClientService;
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
    @RequestMapping("/api/client")
    public class ClientAPIController {

        ClientService cliservice;

        public ClientAPIController(ClientService cliservice) {
            this.cliservice = cliservice;
        }

        @GetMapping(value = "", produces = "application/json")
        public Iterable<ClientEntity> getAll(HttpServletRequest request) {
            String search = request.getParameter("search");
            System.out.println("Recherche de categorie avec param = " + search);
            return cliservice.findAll(search);

        }

        @GetMapping(value = "/{idClient}", produces = "application/json")
        public ResponseEntity<ClientEntity> get(@PathVariable int idClient) {
            try {
                ClientEntity cli = cliservice.findClient(idClient);
                return ResponseEntity.ok(cli);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping(value = "", consumes = "application/json")
        public ResponseEntity<ClientEntity> add(@RequestBody ClientEntity cli) {
            System.out.println(cli);
            try {
                cliservice.addClient(cli);

                // création de l'url d'accès au nouvel objet => http://localhost:8080/api/client/20
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idClient}").buildAndExpand(cli.getIdClient()).toUri();

                return ResponseEntity.created(uri).body(cli);

            } catch (InvalidObjectException e) {
                //return ResponseEntity.badRequest().build();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @PutMapping(value = "/{idClient}", consumes = "application/json")
        public void update(@PathVariable int idClient, @RequestBody ClientEntity cat) {
            try {
                cliservice.editClient(idClient, cat);

            } catch (NoSuchElementException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client introuvable");

            } catch (InvalidObjectException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @DeleteMapping(value = "/{idClient}")
        public ResponseEntity<Object> delete(@PathVariable int idClient) {
            // Check sur l'existance de la ville, si ko => 404 not found
            try {
                ClientEntity cli = cliservice.findClient(idClient);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }

            // si on a un problème à ce niveau => sql exception
            try {
                cliservice.delete(idClient);
                return ResponseEntity.ok(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
    }

