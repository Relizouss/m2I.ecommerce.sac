package com.m2i.sac_ecommerce.controller;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.service.CategorieService;
import com.m2i.sac_ecommerce.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/produit")
public class ProduitController {


    @Autowired
    private ProduitService produitService;

    @Autowired
    public CategorieService catService;

    //param page : numéro de la page actuelle
    // size : nbre d'élements par page
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        String search = request.getParameter("search");


        Page<ProduitEntity> produits = produitService.findAllByPage(page, size, search);

        model.addAttribute("produits", produits);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        model.addAttribute("search", search);

        model.addAttribute("pageCurrent", page);

        model.addAttribute("pages", new int[produits.getTotalPages()]);

        return "produit/list_produit";
    }

    // http://localhost:8080/produit/add
    @GetMapping(value = "/add")
    public String add(Model model) {
        Iterable<CategorieEntity> categories = catService.findAll();
        model.addAttribute("produit", new ProduitEntity());
        model.addAttribute("categories", categories);
        return "produit/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost(HttpServletRequest request, Model model) {

        // Récupération des paramètres envoyés en POST
        String marque = request.getParameter("marque");
        String modele = request.getParameter("modele");
        double prix_Achat = Double.parseDouble(request.getParameter("prixAchat"));
        double prix_Vente = Double.parseDouble(request.getParameter("prixVente"));
        int quantiteStock = Integer.parseInt(request.getParameter("quantiteStock"));
        //String indisponible = request.getParameter("indisponible ");
        String indisponible = "1";
        String photoProduit = request.getParameter("photoProduit");
        int codeCategorie = Integer.parseInt(request.getParameter("codeCategorie"));

        CategorieEntity categorie = new CategorieEntity();
        categorie.setCodeCategorie(codeCategorie);
        // Préparation de l'entité à sauvegarder
        ProduitEntity produit = new ProduitEntity(marque, modele, prix_Achat, prix_Vente, quantiteStock, indisponible, photoProduit, categorie);

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try {
            produitService.addProduit(produit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("produit", produit);
            model.addAttribute("error", e.getMessage());
            return "produit/add_edit";
        }

        return "redirect:/produit?success=true";
    }


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/edit/{id}")
    public String editGetPost(Model model, @PathVariable int idProduit, HttpServletRequest request) {
        System.out.println("Add Edit User" + request.getMethod());

        if (request.getMethod().equals("POST")) {
            // Récupération des paramètres envoyés en POST
            String marque = request.getParameter("marque");
            String modele = request.getParameter("modele");
            double prix_Achat = Double.parseDouble(request.getParameter("prixAchat"));
            double prix_Vente = Double.parseDouble(request.getParameter("prixVente"));
            int quantiteStock = Integer.parseInt(request.getParameter("quantiteStock"));
            String indisponible = request.getParameter("indisponible");
            String photoProduit = request.getParameter("photoProduit");
            int codeCategorie = Integer.parseInt(request.getParameter("codeCategorie"));

            CategorieEntity categorie = new CategorieEntity();
            categorie.setCodeCategorie(codeCategorie);

            // Préparation de l'entité à sauvegarder
            ProduitEntity produit = new ProduitEntity(marque, modele, prix_Achat, prix_Vente, quantiteStock, indisponible, photoProduit, categorie);


            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try {
                produitService.editProduit(idProduit, produit);
            } catch (Exception e) {
                produit.setIdProduit(-1); // hack
                System.out.println(e.getMessage());
                model.addAttribute("produit", produit);
                model.addAttribute("codeCategories", catService.findAll());
                model.addAttribute("error", e.getMessage());
                return "produit/add_edit";
            }
            return "redirect:/produit?success=true";
        } else {
            try {
                model.addAttribute("produit", produitService.findProduit(idProduit));
            } catch (NoSuchElementException e) {
                return "redirect:/produit?error=produit%20introuvalble";
            }

            return "produit/add_edit";
        }
    }

    @RequestMapping(method = {RequestMethod.GET}, value = "/view_produit/{idProduit}")
    public String viewGet(Model model, @PathVariable int idProduit, HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            try {
                model.addAttribute("produit", produitService.findProduit(idProduit));
            } catch (NoSuchElementException e) {
                return "redirect:/produit?error=produit%20introuvalble";
            }
        }
        return "produit/view_produit";
    }

        @GetMapping(value = "/delete/{id}")
        public String delete ( @PathVariable int idProduit){
            String message = "?success=true";
            try {
                produitService.delete(idProduit);
            } catch (Exception e) {
                message = "?error=produit%20introuvalble";
            }
            return "redirect:/produit" + message;
        }

        public void setProduitService (ProduitService produitService){
            this.produitService = produitService;
        }


    }
