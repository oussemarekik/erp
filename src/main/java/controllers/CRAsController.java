package controllers;

import Entity.CRAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CRAsService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/CRAs")
public class CRAsController {

    @Autowired
    private CRAsService crasService;
    @GetMapping(path="/{id}")
    public CRAs getCRAs(@PathVariable String id) {
        return crasService.getCRAsById(id);
    }

    @GetMapping(path="/AllCRAs")
    public List<CRAs> getAll() {
        return crasService.getAllCRAs();
    }

    @GetMapping(path="AllCRAsByStatus")
    public List<CRAs> getAllByStatus(@PathVariable String status) {
        return crasService.getAllCRAsByStatus(status);
    }
    @PostMapping("/create")
    public CRAs createCRAs(@RequestBody CRAs crAs) {
        return crasService.createCRAs(crAs);
    }

    @PutMapping("/update")
    public CRAs updateCRAs(@RequestBody CRAs crAs) {
        return crasService.updateCRAs(crAs);
    }

    @DeleteMapping("/delete/{craId}")
    public void deleteCRAs(@PathVariable String craId) {
        crasService.deleteCRAs(craId);
    }

    @PostMapping("/confirm")
    public void confirmCRAs(@PathVariable String craId) {
        CRAs crAs = crasService.getCRAsById(craId); // Suppose que vous avez une méthode pour récupérer un CRAs par ID
        if (crAs != null) {
            crasService.confirmCRAs(crAs);
        } else {
            // Gérer le cas où l'entité n'existe pas, par exemple en lançant une exception ou en retournant un message d'erreur
        }
    }

    @PostMapping("/rejected")
    public void rejectCRAs(@PathVariable String craId) {
        CRAs crAs = crasService.getCRAsById(craId); // Suppose que vous avez une méthode pour récupérer un CRAs par ID
        if (crAs != null) {
            crasService.rejectedCRAs(crAs);
        } else {
            // Gérer le cas où l'entité n'existe pas, par exemple en lançant une exception ou en retournant un message d'erreur
        }
    }

}

