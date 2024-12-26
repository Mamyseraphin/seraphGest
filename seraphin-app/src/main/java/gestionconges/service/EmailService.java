package gestionconges.service;

import gestionconges.entity.EmailLog;
import gestionconges.repositories.EmailLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailLogRepository emailLogRepository;

    public void envoyerEmail(String emailTo, String subject, String message, Long demandeId) {
        EmailLog emailLog = new EmailLog();
        emailLog.setDestinataire(emailTo);
        emailLog.setMessage(message);
        emailLog.setDateEnvoi(LocalDateTime.now());

        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emailTo);
            email.setSubject(subject);
            email.setText(message);
            email.setFrom("seraphinmamys2@gmail.com");

            mailSender.send(email);
            System.out.println("Email envoyé avec succès à " + emailTo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi de l'email.");
        }

        // Enregistrer le log d'email dans la base de données
        emailLogRepository.save(emailLog);
    }
}
