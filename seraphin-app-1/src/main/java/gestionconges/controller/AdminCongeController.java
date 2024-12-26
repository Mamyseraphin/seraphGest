package gestionconges.controller;

import gestionconges.dto.DemandeRejetDTO;
import gestionconges.service.DemandeCongeService;
import gestionconges.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/admin/conges")
public class AdminCongeController {
    @Autowired
    private DemandeCongeService demandeCongeService;

    @Autowired
    private EmailService emailService;

    // Approuver une demande
    @PostMapping("/approve")
    public String approuverDemande(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        demandeCongeService.updateStatut(id, "Approuvé");
        redirectAttributes.addFlashAttribute("successMessage", "Demande de congé approuvée !");
        return "redirect:/admin-page";
    }

    // Rejeter une demande
    @PostMapping("/reject")
    public String rejeterDemande(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        demandeCongeService.updateStatut(id, "Rejeté");
        redirectAttributes.addFlashAttribute("errorMessage", "Demande de congé rejetée !");
        return "redirect:/admin-page";
    }

    @PostMapping("/admin/conges/reject")
    public String rejectDemande(@RequestBody DemandeRejetDTO demandeRejetDTO) {
        // Rejeter la demande dans la base de données
        boolean isRejected = demandeCongeService.rejeterDemande(demandeRejetDTO.getDemandeId(), demandeRejetDTO.getRejectionReason());

        if (isRejected) {

            // Envoyer un email à l'utilisateur
            String subject = "Votre demande de congé a été rejetée";
            String message = "Bonjour, \n\nVotre demande de congé (ID: " + demandeRejetDTO.getDemandeId() + ") a été rejetée.\nRaison: " + demandeRejetDTO.getRejectionReason() + "\n\nMerci de votre compréhension.";

            // Appel correct à la méthode envoyerEmail
            emailService.envoyerEmail(
                    demandeRejetDTO.getDestinataireEmail(), // emailTo
                    subject,                                // subject
                    message,                                // message
                    demandeRejetDTO.getDemandeId()          // demandeId
            );
            return "Demande rejetée avec succès et email envoyé.";
        } else {
            return "Échec du rejet de la demande.";
        }

    }






}