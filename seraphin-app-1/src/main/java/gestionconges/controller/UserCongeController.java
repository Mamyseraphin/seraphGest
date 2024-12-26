package gestionconges.controller;

import gestionconges.model.DemandeConge;
import gestionconges.service.DemandeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/demandes")
public class UserCongeController {
    @Autowired
    private DemandeCongeService demandeCongeService;

    @GetMapping
    public String afficherHistorique(Model model) {
        // Récupérer l'utilisateur connecté
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Récupérer les demandes de l'utilisateur
        List<DemandeConge> demandes = demandeCongeService.getDemandesByCurrentUser(email);
        model.addAttribute("demandes", demandes);

        return "user-historique";
    }
}
