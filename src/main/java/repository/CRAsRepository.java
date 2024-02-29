package repository;

import Entity.CRAs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CRAsRepository extends JpaRepository<CRAs,String> {
    List<CRAs> findByStatus(String status);
}
