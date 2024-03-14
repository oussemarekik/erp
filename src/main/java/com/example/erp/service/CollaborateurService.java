package com.example.erp.service;

import com.example.erp.Entity.Collaborateur;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface CollaborateurService {
    Long addUser(Collaborateur collaborateur) throws WriterException, IOException;
    void updateUser(Collaborateur collaborateur) throws WriterException, IOException;
    List<Collaborateur> getAll();
    Collaborateur getUserById(Long userId);
    Collaborateur getUserByUserName(String username);
    void deleteUser(Long userId) throws IOException, WriterException;

}
