package com.m2i.sac_ecommerce.service;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.repositories.CategorieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class CategorieService {

    private CategorieRepository catrepository;

    public CategorieService(CategorieRepository catrepository) {
        this.catrepository = catrepository;
    }

    public Page<CategorieEntity> findAllByPage(Integer pageNo, Integer pageSize, String search) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (search != null && search.length() > 0) {
            return catrepository.findByNomContains(search, paging);
        }

        return catrepository.findAll(paging);
    }


    public Iterable<CategorieEntity> findAll(String search) {
        if (search != null && search.length() > 0) {
            return catrepository.findByNomContains(search);
        }
        return catrepository.findAll();
    }

    public CategorieEntity findCategorie(int codeCategorie) {
        return catrepository.findById(codeCategorie).get();
    }

    public void addCategorie(CategorieEntity cat) throws InvalidObjectException {

        catrepository.save(cat);
    }

    public void editCategorie(int codeCategorie, CategorieEntity cat) throws InvalidObjectException {
        try {
            CategorieEntity catExistante = catrepository.findById(codeCategorie).get();

            catExistante.setNom(cat.getNom());
            catExistante.setDescription(cat.getDescription());
            catrepository.save(catExistante);

        } catch (NoSuchElementException e) {
            throw e;
        }

    }

    public void delete(int codeCategorie) {
        catrepository.deleteById(codeCategorie);
    }

}
