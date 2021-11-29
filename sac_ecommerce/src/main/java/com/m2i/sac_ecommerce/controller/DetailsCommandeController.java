package com.m2i.sac_ecommerce.controller;

import com.m2i.sac_ecommerce.entities.DetailsCommandeEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.service.DetailsCommandeService;
import com.m2i.sac_ecommerce.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/detailscommande")
public class DetailsCommandeController {

    @Autowired
    private DetailsCommandeService dcservice;

    @Autowired
    private ProduitService pservice;

    // http://localhost:8080/detailscommande
    @GetMapping(value = "")
    public String list(Model model , HttpServletRequest req ){
        String search = req.getParameter("search");
        model.addAttribute("detailscommandes" , dcservice.findAll( search ) );
        model.addAttribute("error" , req.getParameter("error") );
        model.addAttribute("success" , req.getParameter("success") );
        model.addAttribute("search" , search );
        return "detailscommande/list_detailscommande";
    }

    // http://localhost:8080/detailscommande/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        model.addAttribute("produits" , pservice.findAll() );
        model.addAttribute("detailscommande" , new DetailsCommandeEntity() );
        return "detailscommande/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){
        // Récupération des paramètres envoyés en POST
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        double total = Double.parseDouble(request.getParameter("total"));
        int produit = Integer.parseInt(request.getParameter("produit"));

        // Préparation de l'entité à sauvegarder
        ProduitEntity p = new ProduitEntity();
        p.setIdProduit(produit);
        DetailsCommandeEntity dc = new DetailsCommandeEntity( quantite, total, p);

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            dcservice.addDetailCommande(dc);
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("detailscommande" , dc);
            model.addAttribute("produits" , pservice.findAll() );
            model.addAttribute("error" , e.getMessage() );
            return "detailscommande/add_edit";
        }
        return "redirect:/detailscommande?success=true";
    }

    @GetMapping(value = "/edit/{idCommande}")
    public String edit( Model model , @PathVariable int idCommande){
        model.addAttribute("produits" , pservice.findAll() );
        try {
            model.addAttribute("detailscommande", dcservice.findDetailsCommande(idCommande));
        }catch( NoSuchElementException e){
            return "redirect:/detailscommande?error=DetailsCommandes%20introuvalble";
        }

        return "detailscommande/add_edit";
    }

    @PostMapping(value = "/edit/{idCommande}")
    public String editPost(  Model model , HttpServletRequest request , @PathVariable int idCommande ){
        // Récupération des paramètres envoyés en POST

        int quantite = Integer.parseInt(request.getParameter("quantite"));
        double total = Double.parseDouble(request.getParameter("total"));
        int produit = Integer.parseInt(request.getParameter("produit"));

        // Préparation de l'entité à sauvegarder
        ProduitEntity p = new ProduitEntity();
        p.setIdProduit(produit);
        DetailsCommandeEntity dc = new DetailsCommandeEntity( quantite, total, p);


        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            dcservice.editDetailsCommande( idCommande , dc );
        }catch( Exception e ){
            model.addAttribute("produits" , pservice.findAll() );
            System.out.println( e.getMessage() );
            model.addAttribute( "detailscommande" , dc );
            model.addAttribute("error" , e.getMessage());
            return "detailscommande/add_edit";
        }
        return "redirect:/detailscommande?success=true";
    }

    @GetMapping(value = "/delete/{idCommande}")
    public String delete( @PathVariable int idCommande ){
        String message = "?success=true";
        try{
            dcservice.delete(idCommande);
        }catch( Exception e ){
            message = "?error=DetailsCommande%20introuvable";
        }

        return "redirect:/detailscommande" + message;
    }

    public DetailsCommandeService getDcservice() {
        return dcservice;
    }

    public void setDcservice(DetailsCommandeService dcservice) {
        this.dcservice = dcservice;
    }

}