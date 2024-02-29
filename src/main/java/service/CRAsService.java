package service;

import Entity.CRAs;

import java.util.List;
import java.util.concurrent.CompletableFuture;
public interface CRAsService {
    CRAs getCRAsById(String craId);
    List<CRAs> getAllCRAs();
    List<CRAs> getAllCRAsByStatus(String status);
    CRAs createCRAs(CRAs crAs);
    CRAs updateCRAs(CRAs crAs);
    void deleteCRAs(String craId);
    void  confirmCRAs(CRAs crAs);
    void rejectedCRAs(CRAs crAs);

}
