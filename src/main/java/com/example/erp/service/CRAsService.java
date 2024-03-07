package com.example.erp.service;

import com.example.erp.Entity.CRAs;

import java.util.List;

public interface CRAsService {
    CRAs getCRAsById(Long craId);
    List<CRAs> getAllCRAs();
    List<CRAs> getAllCRAsByStatus(String status);
    CRAs createCRAs(CRAs crAs);
    CRAs updateCRAs(CRAs crAs);
    void deleteCRAs(Long craId);
    void  confirmCRAs(CRAs crAs);
    void rejectedCRAs(CRAs crAs);

}
