package com.example.erp.service;

import com.example.erp.Entity.Collaborateur;
import com.example.erp.repository.CollaborateurRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class CollaborateurServiceImpl implements CollaborateurService {

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Override
    public Long addUser(Collaborateur collaborateur) throws WriterException, IOException {
        // Définir la date d'embauche comme la date actuelle
        collaborateur.setDateEmbauche(new Date().toString());

        // Enregistrer le collaborateur dans la base de données

        // Générer la chaîne de caractères encodée
        String encodedString = collaborateur.getId() + "\n UserName:" + collaborateur.getUsername() + "\n Email:" + collaborateur.getEmail() + "\n First Name:" + collaborateur.getFirstName() + "\n Last Name:" + collaborateur.getLastName() + "\n Numero Téléphone:" + collaborateur.getNumTel() + "\n Type de contrat:" + collaborateur.getTypeContrat() + "\n Salaire:" + collaborateur.getSalaire() + "\n Responsable:" + collaborateur.getResponsable() + "\n Role:" + collaborateur.getRole() + "\n Date d'embauche:" + collaborateur.getDateEmbauche();

        // Encodage de la chaîne de caractères dans le code QR
        ByteArrayOutputStream outputStream = generateQRCode(encodedString);

        // Sauvegarder le code QR et mettre à jour le collaborateur dans la base de données
        collaborateur.setQrCode(outputStream.toByteArray());
        collaborateurRepository.save(collaborateur);

        // Retourner l'ID du collaborateur nouvellement créé
        return collaborateur.getId(); // ou bien, vous pouvez générer un ID ici si nécessaire
    }

    @Override
    public void updateUser(Collaborateur collaborateur) throws WriterException, IOException {
        // Vérifier si le collaborateur existe dans la base de données
        Collaborateur existCollaborateur = collaborateurRepository.findById(collaborateur.getId()).orElseThrow(() -> new IllegalArgumentException("Collaborateur with ID " + collaborateur.getId() + " does not exist"));

        // Mettre à jour les propriétés du collaborateur
        updateCollaborateurProperties(existCollaborateur, collaborateur);

        // Générer la chaîne de caractères encodée
        String encodedString = generateEncodedString(collaborateur);

        // Encodage de la chaîne de caractères dans le code QR
        ByteArrayOutputStream outputStream = generateQRCode(encodedString);

        // Mettre à jour le code QR dans l'objet Collaborateur
        existCollaborateur.setQrCode(outputStream.toByteArray());

        // Enregistrer les modifications dans la base de données
        collaborateurRepository.save(existCollaborateur);
    }

    @Override
    public List<Collaborateur> getAll() {
        // Renvoie tous les collaborateurs
        return collaborateurRepository.findAll();
    }

    @Override
    public Collaborateur getUserById(Long userId) {
        // Renvoie le collaborateur par son ID
        Optional<Collaborateur> collaborateurOptional = collaborateurRepository.findById(userId);
        return collaborateurOptional.orElse(null);
    }

    @Override
    public Collaborateur getUserByUserName(String username) {
        return collaborateurRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        collaborateurRepository.deleteById(userId);
    }

    // Méthode pour générer le code QR à partir d'une chaîne de caractères encodée
    private ByteArrayOutputStream generateQRCode(String encodedString) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix bitMatrix = qrCodeWriter.encode(encodedString, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        return outputStream;
    }

    // Méthode pour mettre à jour les propriétés du collaborateur
    private void updateCollaborateurProperties(Collaborateur existingCollaborateur, Collaborateur newCollaborateur) {
        existingCollaborateur.setUsername(newCollaborateur.getUsername());
        existingCollaborateur.setFirstName(newCollaborateur.getFirstName());
        existingCollaborateur.setLastName(newCollaborateur.getLastName());
        existingCollaborateur.setEmail(newCollaborateur.getEmail());
        existingCollaborateur.setNumTel(newCollaborateur.getNumTel());
        existingCollaborateur.setRole(newCollaborateur.getRole());
        existingCollaborateur.setTypeContrat(newCollaborateur.getTypeContrat());
        existingCollaborateur.setSalaire(newCollaborateur.getSalaire());
        existingCollaborateur.setResponsable(newCollaborateur.getResponsable());
    }

    // Méthode pour générer la chaîne de caractères encodée
    private String generateEncodedString(Collaborateur collaborateur) {
        return collaborateur.getId() + "\n UserName:" + collaborateur.getUsername() + "\n Email:" + collaborateur.getEmail() + "\n First Name:" + collaborateur.getFirstName() + "\n Last Name:" + collaborateur.getLastName() + "\n Numero Téléphone:" + collaborateur.getNumTel() + "\n Type de contrat:" + collaborateur.getTypeContrat() + "\n Salaire:" + collaborateur.getSalaire() + "\n Responsable:" + collaborateur.getResponsable() + "\n Role:" + collaborateur.getRole() + "\n Date d'embauche:" + collaborateur.getDateEmbauche();
    }
}
