package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.InvoiceStatusDTO;
import com.managehotelapp_javafx.entity.InvoiceStatusEntity;
import com.managehotelapp_javafx.repository.InvoiceStatusRepository;
import com.managehotelapp_javafx.repository.imp.InvoiceStatusRepositoryImp;
import com.managehotelapp_javafx.services.InvoiceStatusService;

import java.util.ArrayList;
import java.util.List;

public class InvoiceStatusServiceImp implements InvoiceStatusService {

    InvoiceStatusRepository invoiceStatusRepository = new InvoiceStatusRepositoryImp();
    @Override
    public List<InvoiceStatusDTO> invStatusDTOList() {
        List<InvoiceStatusDTO> invoiceStatusDTOList = new ArrayList<>();
        invoiceStatusRepository.getInvStatusList().forEach(invoiceStatusEntity -> {
            InvoiceStatusDTO invoiceStatusDTO = new InvoiceStatusDTO();
            invoiceStatusDTO.setId(invoiceStatusEntity.getId());
            invoiceStatusDTO.setTitle(invoiceStatusEntity.getTitle());
            invoiceStatusDTOList.add(invoiceStatusDTO);
        });
        return invoiceStatusDTOList ;
    }

    @Override
    public InvoiceStatusDTO findInvStatusById(int idInvStatus) {
        InvoiceStatusEntity invoiceStatusEntity = invoiceStatusRepository.findInvStatusById(idInvStatus);

        InvoiceStatusDTO invoiceStatusDTO = new InvoiceStatusDTO();
        invoiceStatusDTO.setId(invoiceStatusEntity.getId());
        invoiceStatusDTO.setTitle(invoiceStatusEntity.getTitle());

        return invoiceStatusDTO;
    }

    @Override
    public InvoiceStatusDTO findInvStatusByTitle(String invStatus) {
        InvoiceStatusEntity invoiceStatusEntity = invoiceStatusRepository.findInvStatusByTitle(invStatus);

        InvoiceStatusDTO invoiceStatusDTO = new InvoiceStatusDTO();
        invoiceStatusDTO.setId(invoiceStatusEntity.getId());
        invoiceStatusDTO.setTitle(invoiceStatusEntity.getTitle());

        return invoiceStatusDTO;
    }

    @Override
    public boolean insertInvStatus(InvoiceStatusDTO invoiceStatusDTO) {
        return false;
    }

    @Override
    public boolean deleteInvStatus(int idInvStatus) {
        return false;
    }

    @Override
    public boolean updateInvStatus(InvoiceStatusDTO invoiceStatusDTO) {
        return false;
    }
}
