package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> customerDTOList ();

    CustomerDTO findCustomerById(int idCus);

    boolean insertCustomer (CustomerDTO customerDTO);
    boolean deleteCustomer (int idCus);
    boolean updateCustomer (CustomerDTO customerDTO);
}
