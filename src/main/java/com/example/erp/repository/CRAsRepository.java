package com.example.erp.repository;

import com.example.erp.Entity.CRAs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CRAsRepository extends JpaRepository<CRAs,Long> {
    List<CRAs> findByStatus(String status);
}
