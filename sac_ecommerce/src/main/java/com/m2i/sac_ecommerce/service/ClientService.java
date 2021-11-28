package com.m2i.sac_ecommerce.service;

import com.m2i.sac_ecommerce.entities.ClientEntity;
import com.m2i.sac_ecommerce.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class ClientService {

    private ClientRepository clirepository;

   // @Autowired
    //private PasswordEncoder encoder;

    public ClientService(ClientRepository clirepository ){
        this.clirepository = clirepository;
    }

    public Page<ClientEntity> findAllByPage(Integer pageNo, Integer pageSize, String search) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (search != null && search.length() > 0) {
            return clirepository.findByNomContains(search, paging);
        }

        return clirepository.findAll(paging);
    }


    public Iterable<ClientEntity> findAll(String search) {
        return clirepository.findAll();
    }

    public void addClient(ClientEntity cli) throws InvalidObjectException{
       // cli.setPassword(encoder.encode(cli.getPassword()));
        clirepository.save(cli);
    }

    public ClientEntity findClient(int idClient) {
        return clirepository.findById(idClient).get();
    }

    public void editClient( int idClient, ClientEntity cli) throws InvalidObjectException {

        try{
            ClientEntity cliExistant = clirepository.findById(idClient).get();

            cliExistant.setNom(cli.getNom());
            cliExistant.setPrenom(cli.getPrenom());
            cliExistant.setDateNaissance(cli.getDateNaissance());
            cliExistant.setAdresse(cli.getAdresse());
            cliExistant.setVille(cli.getVille());
            cliExistant.setCodePostal(cli.getCodePostal());
            cliExistant.setPays(cli.getPays());
            cliExistant.setTelephone(cli.getTelephone());
            cliExistant.setEmail(cli.getEmail());
            cliExistant.setPassword(cli.getPrenom());
            cliExistant.setCarteNumero(cli.getCarteNumero());

            //if( u.getPassword().length() > 0 ){
              //  uExistant.setPassword( encoder.encode( u.getPassword() ) );
           // }

            clirepository.save( cliExistant );

        }catch ( NoSuchElementException e ){
            throw e;
        }
    }

    public void delete(int idClient) {
        clirepository.deleteById(idClient);
    }

}
