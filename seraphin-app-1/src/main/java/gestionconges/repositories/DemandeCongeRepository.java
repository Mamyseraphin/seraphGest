package gestionconges.repositories;
 


import gestionconges.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gestionconges.model.DemandeConge;

import java.util.List;

@Repository
public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Long> {
    @Query(value = "SELECT dc.id AS demande_id, dc.date_debut, dc.date_fin, dc.motif, " +
            "dc.statut, dc.type_conge, u.id AS user_id, u.fullname AS user_fullname, u.email AS user_email " +
//            " u.matricule AS user_matricule" +
//            ", u.poste AS user_poste, u.service AS user_service" +
            "FROM demande_conge dc " +
            "INNER JOIN users u ON dc.user_id = u.id "
            , nativeQuery = true)
//            "WHERE dc.statut = :statut", nativeQuery = true)
    List<Object[]> findDemandesByStatutNative(@Param("statut") String statut);
    List<DemandeConge> findByUser(User user);
}
