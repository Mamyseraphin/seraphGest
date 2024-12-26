package gestionconges.dto;

public class DemandeRejetDTO {

    private Long demandeId;
    private String rejectionReason;
    private String destinataireEmail;

    // Getters et Setters

    public Long getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(Long demandeId) {
        this.demandeId = demandeId;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getDestinataireEmail() {
        return destinataireEmail;
    }

    public void setDestinataireEmail(String destinataireEmail) {
        this.destinataireEmail = destinataireEmail;
    }
}
