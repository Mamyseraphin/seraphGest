package gestionconges.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import gestionconges.model.DemandeConge;
import gestionconges.model.User;
import gestionconges.repositories.DemandeCongeRepository;
import gestionconges.repositories.UserRepository;

import java.util.List;

@Service
public class DemandeCongeService {
    @Autowired
    private DemandeCongeRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<DemandeConge> getAllConges() {
        return repository.findAll();
    }
    @Autowired
    private DemandeCongeRepository demandeCongeRepository;


    public void saveDemande(DemandeConge demandeConge) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        String email;
        if (principal instanceof CustomUserDetail) {
            email = ((CustomUserDetail) principal).getUsername();
        } else {
            throw new IllegalStateException("Utilisateur non authentifié");
        }

        // Associer l'utilisateur à la demande
        User user = userRepository.findByEmail(email)
                //.orElseThrow(() -> new RuntimeException("Utilisateur introuvable"))
                ;

        demandeConge.setUser(user);
        demandeCongeRepository.save(demandeConge);
    }
//    Les nouvelles methodes
    public List<Object[]> getDemandesByStatut(String statut) {
        return demandeCongeRepository.findDemandesByStatutNative(statut);
    }
    public void updateStatut(Long id, String nouveauStatut) {
        DemandeConge demande = demandeCongeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée avec l'ID : " + id));
        demande.setStatut(nouveauStatut);
        demandeCongeRepository.save(demande);
    }
    // Récupérer les demandes d'un utilisateur connecté
    public List<DemandeConge> getDemandesByCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                //.orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email : " + email))
                ;
        return demandeCongeRepository.findByUser(user);
    }

    public void updateRejectionReason(Long demandeId, String rejectionReason) {
        DemandeConge demande = demandeCongeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée avec l'ID : " + demandeId));
        demande.setStatut("REJETÉ");
        demande.setRejetReason(rejectionReason); // Mise à jour de la justification
        demandeCongeRepository.save(demande);
    }




    public DemandeConge findById(Long id) {
        return demandeCongeRepository.findById(id).orElse(null);
    }

    public boolean rejeterDemande(Long demandeId, String rejectionReason) {
        DemandeConge demande = demandeCongeRepository.findById(demandeId).orElse(null);
        if (demande != null) {
            demande.setStatut("Rejetée");
            demande.setMotif(rejectionReason); // Assurez-vous d'avoir ce champ dans votre modèle
            demandeCongeRepository.save(demande);
            return true;
        }
        return false;
    }

}
