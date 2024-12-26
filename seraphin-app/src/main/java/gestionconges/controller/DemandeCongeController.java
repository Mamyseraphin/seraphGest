package gestionconges.controller;

import gestionconges.model.DemandeConge;
import gestionconges.service.DemandeCongeService;
import gestionconges.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DemandeCongeController {

    @Autowired
    private DemandeCongeService demandeCongeService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/admin/approve-conge")
    public String approveDemande(Long demandeId, RedirectAttributes redirectAttributes) {
        DemandeConge demandeConge = demandeCongeService.findById(demandeId);
        if (demandeConge == null) {
            throw new RuntimeException("Demande de congé introuvable !");
        }

        // Mettre à jour le statut
        demandeConge.setStatut("APPROUVÉ");
        demandeCongeService.saveDemande(demandeConge);

        // Envoyer un email au demandeur
        String emailTo = demandeConge.getUser().getEmail(); // Assurez-vous que l'utilisateur a un email valide.
        String subject = "Votre demande de congé a été approuvée";
        String message = "Bonjour " + demandeConge.getUser().getFullname() + ",\n\n" +
                "Votre demande de congé du " + demandeConge.getDateDebut() + " au " + demandeConge.getDateFin() +
                " a été approuvée.\n\nCordialement,\nL'équipe RH";
        emailService.envoyerEmail(emailTo, subject, message, demandeConge.getId());  // Correction ici : passer un String pour le subject

        redirectAttributes.addFlashAttribute("successMessage", "Demande approuvée et email envoyé !");
        return "redirect:/admin/demandes";
    }


    public String rejectDemande(@RequestParam("id") Long demandeId, @RequestParam("rejectionReason") String rejectionReason, RedirectAttributes redirectAttributes) {
        DemandeConge demandeConge = demandeCongeService.findById(demandeId);
        if (demandeConge == null) {
            throw new RuntimeException("Demande de congé introuvable !");
        }

        // Mettre à jour le statut et la raison du rejet
        demandeConge.setStatut("REJETÉ");
        demandeConge.setRejetReason(rejectionReason);
        demandeCongeService.saveDemande(demandeConge);

        // Envoyer un email au demandeur
        String emailTo = demandeConge.getUser().getEmail();
        String subject = "Votre demande de congé a été rejetée";
        emailService.envoyerEmail(emailTo, subject, rejectionReason, demandeConge.getId()); // Passer tous les paramètres nécessaires

        redirectAttributes.addFlashAttribute("successMessage", "Demande rejetée et email envoyé !");
        return "redirect:/admin/demandes";
    }



    @Autowired
    private DemandeCongeService DemandeCongeService;

    @GetMapping("/demander-conge")
    public String showDemandeCongeForm(Model model) {
        model.addAttribute("demandeConge", new DemandeConge());
        return "user-page"; // Assurez-vous que cette vue existe dans resources/templates/user-page.html
    }

    @PostMapping("/demander-conge")
    public String handleDemandeConge(DemandeConge demandeConge, RedirectAttributes redirectAttributes) {
        demandeCongeService.saveDemande(demandeConge);
        redirectAttributes.addFlashAttribute("successMessage", "Demande de congé soumise avec succès !");
        return "redirect:/user-page"; // Cette vue doit aussi exister dans le répertoire des templates
    }
}
