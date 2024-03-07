package com.example.erp.service;


import com.example.erp.Entity.CRAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erp.repository.CRAsRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CRAsServiceImpl implements CRAsService {

    @Autowired
    private CRAsRepository crasRepository;
    @Override
    public CRAs getCRAsById(Long craId) {
        return crasRepository.findById(craId).orElse(null);
    }

    @Override
    public List<CRAs> getAllCRAs() {
        return crasRepository.findAll();
    }

    @Override
    public List<CRAs> getAllCRAsByStatus(String status) {
        return crasRepository.findByStatus(status);
    }
    @Override
    public CRAs createCRAs(CRAs crAs) {
        LocalDate startDate = crAs.getStartDate();
        LocalDate endDate = crAs.getEndDate();

        long difference = ChronoUnit.DAYS.between(startDate, endDate);
        // Lors de la création, définissez le statut sur "created"
        crAs.setStatus("created");
        crAs.setTimeSpent(String.valueOf(difference+" Jours"));
        return crasRepository.save(crAs);
    }

    @Override
    public CRAs updateCRAs(CRAs crAs) {
        // Implémentation de la méthode pour mettre à jour un compte rendu d'activités
        Optional<CRAs> existingCRAs = crasRepository.findById(crAs.getCrasId());
        if (existingCRAs.isPresent()) {
            CRAs updatedCRAs = existingCRAs.get();
            updatedCRAs.setTimeSpent(crAs.getTimeSpent());
            updatedCRAs.setDescription(crAs.getDescription());
            updatedCRAs.setStartDate(crAs.getStartDate());
            updatedCRAs.setEndDate(crAs.getEndDate());
            updatedCRAs.setIdProject(crAs.getIdProject());
            updatedCRAs.setIdResponsible(crAs.getIdResponsible());
            updatedCRAs.setIdCollaborator(crAs.getIdCollaborator());
            updatedCRAs.setComment(crAs.getComment());
            updatedCRAs.setProductivity(crAs.getProductivity());
            updatedCRAs.setStatus("Modif");
            return crasRepository.save(updatedCRAs);
        } else {
            throw new RuntimeException("CRAs with ID " + crAs.getCrasId() + " not found");
        }
    }
    @Override
    public void deleteCRAs(Long craId) {
        crasRepository.deleteById(craId);
    }

    @Override
    public void confirmCRAs(CRAs crAs) {
        // Mettre à jour le statut pour confirmer
        crAs.setStatus("confirmed");
        crasRepository.save(crAs);
    }

    @Override
    public void rejectedCRAs(CRAs crAs) {
        // Mettre à jour le statut pour rejeter
        crAs.setStatus("rejected");
        crasRepository.save(crAs);
    }

}
