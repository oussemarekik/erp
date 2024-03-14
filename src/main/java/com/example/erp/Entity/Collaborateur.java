package com.example.erp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collaborateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] qrCode;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String numTel;
    private String role;
    private String typeContrat;
    private String salaire;
    private String dateEmbauche;
    private String responsable;
}
