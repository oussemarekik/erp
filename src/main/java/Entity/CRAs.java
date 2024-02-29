package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class CRAs {
     @Id
     private String crasId;
     private String timeSpent;
     private String description;
     private LocalDate startDate;
     private LocalDate endDate;
     private String idProject;
     private String idResponsible;
     private String idCollaborator;
     private String comment;
     private String status;
     private double productivity;
}
