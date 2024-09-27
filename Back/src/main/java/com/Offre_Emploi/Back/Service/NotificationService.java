package com.Offre_Emploi.Back.Service;

import com.Offre_Emploi.Back.Entity.*;
import com.Offre_Emploi.Back.Repository.NotificationRepository;
import com.Offre_Emploi.Back.Repository.OffrePriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private int e1;
    private int e2;
    private int e3;


    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private CandidatService candidatService;
    @Autowired
    private OffresPriveService offresPriveService;
    @Autowired
    private OffrePriveRepository offrePriveRepository;


    public List<Notification> getNotificationsByIdCandidat(Long idCandidat) {
        return notificationRepository.getNotificationsByCandidat_Id(idCandidat);
    }

    @Transactional
    public void NotificationVu(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        notification.setVu(true);
    }

    public void sendNotifications(Long id) {
        Offres offre = offrePriveRepository.findById(id).orElseThrow();
        List<Candidat> candidat_notification = new ArrayList<>();
        List<Candidat> candidats = candidatService.getCondidats();
        System.out.println("salam");

        Neurone n = new Neurone(0.6, 0.3, 0.8, 0.6);

        for (Candidat candidat : candidats) {
            // Initialize flags
            int e1 = 0;
            int e2 = 0;
            int e3 = 0;

            for (Competance c : candidat.getCompetances()) {
                if (offre.getTitre().toLowerCase().contains(candidat.getFonction().toLowerCase())) {
                    e3 = 1;
                }
                if (offre.getDescription().toLowerCase().contains(c.getNom().toLowerCase())) {
                    e1 = 1;
                }
                if (offre.getDescription().toLowerCase().contains(c.getNom().toLowerCase()) && (c.getNiveau() > 40)) {
                    e2 = 1;
                }
            }

            if (n.evaluer(e1, e2, e3)) {
                if (!candidat_notification.contains(candidat)) {
                    candidat_notification.add(candidat);
                }
            }
        }

        for (Candidat c : candidat_notification) {
            if (c.getMailNotifications()) {
                Notification notification = new Notification();
                notification.setVu(false);
                notification.setDate(LocalDate.now());
                notification.setOffres(offre);
                notification.setCandidat(c);
                notificationRepository.save(notification);
            }
        }
    }

    public void sendNotifications(Offres offre) {
        List<Candidat> candidat_notification = new ArrayList<>();
        List<Candidat> candidats = candidatService.getCondidats();
        System.out.println("salam");

        Neurone n = new Neurone(0.6, 0.3, 0.8, 0.6);

        for (Candidat candidat : candidats) {
            // Initialize flags
            int e1 = 0;
            int e2 = 0;
            int e3 = 0;

            for (Competance c : candidat.getCompetances()) {
                if (offre.getTitre().toLowerCase().contains(candidat.getFonction().toLowerCase())) {
                    e3 = 1;
                }
                if (offre.getDescription().toLowerCase().contains(c.getNom().toLowerCase())) {
                    e1 = 1;
                }
                if (offre.getDescription().toLowerCase().contains(c.getNom().toLowerCase()) && (c.getNiveau() > 40)) {
                    e2 = 1;
                }
            }

            if (n.evaluer(e1, e2, e3)) {
                if (!candidat_notification.contains(candidat)) {
                    candidat_notification.add(candidat);
                }
            }
        }

        for (Candidat c : candidat_notification) {
            if (c.getMailNotifications()) {
                Notification notification = new Notification();
                notification.setVu(false);
                notification.setDate(LocalDate.now());
                notification.setOffres(offre);
                notification.setCandidat(c);
                notificationRepository.save(notification);
            }
        }
    }
    public void sendNotificationApplicationStatus(Offres offre, Candidat candidat) {
        Notification notification = new Notification();
        notification.setVu(false);
        notification.setDate(LocalDate.now());
        notification.setOffres(offre);
        notification.setCandidat(candidat);
        notificationRepository.save(notification);
        System.out.println("notification is sent");
    }
}
