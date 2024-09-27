package com.Offre_Emploi.Back.Service;

import com.Offre_Emploi.Back.Entity.Postulation;
import com.Offre_Emploi.Back.Repository.CandidatRepository;
import com.Offre_Emploi.Back.Repository.OffrePriveRepository;
import com.Offre_Emploi.Back.Repository.PostulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostulationService {

    @Autowired
    private PostulationRepository postulationRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OffrePriveRepository offrePriveRepository;

    public Postulation addPostulation(Postulation postulation){
        return postulationRepository.save(postulation);
    }

    public Postulation findPostulationById(Long idPostulation){
        return postulationRepository.findById(idPostulation).orElse(null);
    }

    public void deletePostulation(Long id){
        postulationRepository.deleteById(id);
    }

    @Transactional
    public Postulation updatePostulation(Postulation postulation) {
        Postulation postulationUpdate = postulationRepository.findById(postulation.getId()).orElse(null);
        postulationUpdate.setDecision_recruteur(postulation.getDecision_recruteur());
        var offre = offrePriveRepository.getOffresByPostulationsId(postulation.getId());
        var candidat = postulationUpdate.getCandidat();
        System.out.println("notification is sent");
        notificationService.sendNotificationApplicationStatus(offre,candidat);
        return postulationUpdate;
    }

    @Transactional
    public Postulation updatePostulationCV(Postulation postulation) {
        Postulation postulationUpdate = postulationRepository.findById(postulation.getId()).orElse(null);
        postulationUpdate.setCv(postulation.getCv());
        return postulationUpdate;
    }

    @Transactional
    public Postulation updatePostulationCVUserCV(Postulation postulation) {
        Postulation postulationUpdate = postulationRepository.findById(postulation.getId()).orElse(null);
        postulationUpdate.setCv(postulation.getCv());
        return postulationUpdate;
    }



}
