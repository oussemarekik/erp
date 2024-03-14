package com.example.erp.controllers;

import com.example.erp.Entity.Collaborateur;
import com.example.erp.service.CollaborateurService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class CollaborateurController {
    @Autowired
    private CollaborateurService use;
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(CollaborateurController.class);

    @PostMapping("/create")
    public Long createUser(@RequestBody Collaborateur collaborateur) throws Exception, IOException {
        return use.addUser(collaborateur);
    }
    /*@GetMapping("/AllUsers/{orgId}")
    public ResponseEntity <List<UserRepresentation>> getUsers(@PathVariable("orgId") String orgId) {
        return new ResponseEntity<>(use.getUsers(orgId),HttpStatus.OK);
    }*/
    @GetMapping("/AllUsers")
    public List<Collaborateur> getAllUser(){
        return use.getAll();
    }
    /* @GetMapping("/{orgId}/{userId}")
     public ResponseEntity<UserResource> confirmUserOrganization(@PathVariable ("orgId")String orgId ,@PathVariable ("userId") String userId ) {
         return  new ResponseEntity<>(use.confirmUserOrganization(orgId,userId),HttpStatus.OK);
     }*/
  /*  @GetMapping("/{userId}")
    public org.keycloak.representations.idm.UserRepresentation getUserById(@PathVariable("userId") String userId) {
        return use.getUserById(userId);
    }*/
    @GetMapping("/{userId}")
    public Collaborateur getUserById(@PathVariable("userId") Long userId){
        return  use.getUserById(userId);
    }

    @GetMapping("user/{username}")
    public Collaborateur getUserByUsername(@PathVariable("username") String username){
        return  use.getUserByUserName(username);
    }
  /*  @PostMapping("/login")

    public ResponseEntity<AccessTokenResponse> login(@RequestBody UserDTO userDTO) {
        Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentials(userDTO).build();
        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            LOG.warn("invalid account. User probably hasn't verified email.", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }*/
   /* @PostMapping("/{userId}/qrcode")
    public ResponseEntity<String> generateAndSaveQRCode(@PathVariable String userId) {
        try {
            use.generateAndSaveQRCode(userId);
            return ResponseEntity.ok("QR code generated for user " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate QR code for user " + userId + ": " + e.getMessage());
        }
    }

*/

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser( @RequestBody Collaborateur userDTO) throws IOException, WriterException {
        use.updateUser( userDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            use.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }


}

