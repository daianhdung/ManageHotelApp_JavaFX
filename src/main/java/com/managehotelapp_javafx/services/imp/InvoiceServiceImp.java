package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.entity.InvoiceEntity;
import com.managehotelapp_javafx.entity.InvoiceStatusEntity;
import com.managehotelapp_javafx.repository.CustomerRepository;
import com.managehotelapp_javafx.repository.InvoiceRepository;
import com.managehotelapp_javafx.repository.InvoiceStatusRepository;
import com.managehotelapp_javafx.repository.imp.CustomerRepositoryImp;
import com.managehotelapp_javafx.repository.imp.InvoiceRepositoryImp;
import com.managehotelapp_javafx.repository.imp.InvoiceStatusRepositoryImp;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.CustomerService;
import com.managehotelapp_javafx.services.InvoiceService;
import com.managehotelapp_javafx.services.InvoiceStatusService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceServiceImp implements InvoiceService {

    InvoiceRepository invRepository = new InvoiceRepositoryImp();
    InvoiceStatusRepository invoiceStatusRepository = new InvoiceStatusRepositoryImp();
    CustomerRepository customerRepository = new CustomerRepositoryImp();

    @Override
    public List<InvoiceDTO> getInvoiceDTOList() {
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        invRepository.getInvoiceList().forEach(invoiceEntity -> {
            InvoiceDTO invoiceDTO = new InvoiceDTO();

            invoiceDTO.setId(invoiceEntity.getId());
            invoiceDTO.setCustomer(invoiceEntity.getCustomer().getFullName());
            invoiceDTO.setCheckoutLate(invoiceEntity.isCheckoutLate());
            invoiceDTO.setPaymentAmount(invoiceEntity.getPaymentAmount());
            invoiceDTO.setPaymentDate(invoiceEntity.getPaymentDate());
            invoiceDTO.setInvoiceStatus(invoiceEntity.getInvoiceStatus().getTitle());
            invoiceDTO.setCreatedAt(invoiceEntity.getCreatedAt());


            invoiceDTOList.add(invoiceDTO);
        });
        return invoiceDTOList;
    }

    @Override
    public InvoiceDTO findInvoiceById(int idInv) {
        InvoiceEntity invoiceEntity = invRepository.findInvById(idInv);
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoiceEntity.getId());
        invoiceDTO.setCustomer(invoiceEntity.getCustomer().getFullName());
        invoiceDTO.setCheckoutLate(invoiceEntity.isCheckoutLate());
        invoiceDTO.setPaymentAmount(invoiceEntity.getPaymentAmount());
        invoiceDTO.setPaymentDate(invoiceEntity.getPaymentDate());
        invoiceDTO.setInvoiceStatus(invoiceEntity.getInvoiceStatus().getTitle());
        invoiceDTO.setCreatedAt(invoiceEntity.getCreatedAt());

        return invoiceDTO;
    }

    @Override
    public int insertInvoiceDTO(InvoiceDTO invoiceDTO) {

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setCustomer(customerRepository.findCustomerById(invoiceDTO.getIdCustomer()));
        invoiceEntity.setCheckoutLate(invoiceDTO.isCheckoutLate());
        invoiceEntity.setPaymentAmount(invoiceDTO.getPaymentAmount());
        invoiceEntity.setPaymentDate(invoiceDTO.getPaymentDate());
        invoiceEntity.setInvoiceStatus(invoiceStatusRepository.findInvStatusByTitle(invoiceDTO.getInvoiceStatus()));
        invoiceEntity.setCreatedAt(invoiceDTO.getCreatedAt());
        return invRepository.insertInvoice(invoiceEntity);
    }




    @Override
    public boolean deleteInvoiceDTO(int idInvDTO) {
        return invRepository.deleteInvoice(idInvDTO);
    }

    @Override
    public boolean updateInvoiceDTO(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setId(invoiceDTO.getId());
        invoiceEntity.setCustomer(customerRepository.findCustomerById(Integer.parseInt(invoiceDTO.getCustomer())));
        invoiceEntity.setCheckoutLate(invoiceDTO.isCheckoutLate());
        invoiceEntity.setPaymentAmount(invoiceDTO.getPaymentAmount());
        invoiceEntity.setPaymentDate(invoiceDTO.getPaymentDate());
        invoiceEntity.setInvoiceStatus(invoiceStatusRepository.findInvStatusByTitle(invoiceDTO.getInvoiceStatus()));
        invoiceEntity.setCreatedAt(invoiceDTO.getCreatedAt());

        return invRepository.updateInvoice(invoiceEntity);
    }

    @Override
    public String getRevenueToday() {
        LocalDate todayDate = LocalDate.now();
        LocalDateTime startOfDay = todayDate.atStartOfDay();
        Timestamp timestampToday = Timestamp.valueOf(startOfDay);
        return String.valueOf(invRepository.findByDay(timestampToday).stream().mapToInt(InvoiceEntity::getPaymentAmount).sum());
    }
}
