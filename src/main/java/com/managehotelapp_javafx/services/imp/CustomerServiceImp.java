package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.CustomerDTO;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.repository.CustomerRepository;
import com.managehotelapp_javafx.repository.CustomerTypeRepository;
import com.managehotelapp_javafx.repository.imp.CustomerRepositoryImp;
import com.managehotelapp_javafx.repository.imp.CustomerTypeRepositoryImp;
import com.managehotelapp_javafx.services.CustomerService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImp implements CustomerService {
    CustomerRepository customerRepository = new CustomerRepositoryImp();
    CustomerTypeRepository customerTypeRepository = new CustomerTypeRepositoryImp();
    @Override
    public List<CustomerDTO> customerDTOList() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerRepository.getCustomerList().forEach(customerEntity -> {
            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setId(customerEntity.getId());
            customerDTO.setCustomerType(customerEntity.getCustomerType().getTitle());
            customerDTO.setFullName(customerEntity.getFullName());
            customerDTO.setDob(customerEntity.getDob());
            customerDTO.setEmail(customerEntity.getEmail());
            customerDTO.setGender(customerEntity.getGender());
            customerDTO.setPhone(customerEntity.getPhone());
            customerDTO.setIdentity(customerEntity.getIdentity());
            customerDTO.setPassportNo(customerEntity.getPassportNo());
            customerDTO.setAddress(customerEntity.getAddress());
            customerDTO.setCity(customerEntity.getCity());
            customerDTO.setCountry(customerEntity.getCountry());
            customerDTO.setBookingCount(String.valueOf(customerEntity.getBookingCount()));

            customerDTOList.add(customerDTO);

        });
        return customerDTOList;
    }

    @Override
    public CustomerDTO findCustomerById(int idcus) {

        CustomerEntity customerEntity = customerRepository.findCustomerById(idcus);
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customerEntity.getId());
        customerDTO.setCustomerType(customerEntity.getCustomerType().getTitle());
        customerDTO.setFullName(customerEntity.getFullName());
        customerDTO.setDob(customerEntity.getDob());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setGender(customerEntity.getGender());
        customerDTO.setPhone(customerEntity.getPhone());
        customerDTO.setIdentity(customerEntity.getIdentity());
        customerDTO.setPassportNo(customerEntity.getPassportNo());
        customerDTO.setAddress(customerEntity.getAddress());
        customerDTO.setCity(customerEntity.getCity());
        customerDTO.setCountry(customerEntity.getCountry());
        customerDTO.setBookingCount(String.valueOf(customerEntity.getBookingCount()));

        return customerDTO;
    }

    @Override
    public boolean insertCustomer(CustomerDTO customerDTO) {


        return false;
    }

    @Override
    public boolean deleteCustomer(int idCus) {
        return customerRepository.deleteCustomer(idCus);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customerDTO.getId());
        customerEntity.setCustomerType(customerTypeRepository.findCustomerTypeByTitle(customerDTO.getCustomerType()));
        customerEntity.setFullName(customerDTO.getFullName());
        customerEntity.setDob(customerDTO.getDob());
//        customerEntity.setCreatedAt(Timestamp.valueOf(customerDTO.getCreatedAt()));
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setGender(customerDTO.getGender());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setIdentity(customerDTO.getIdentity());
        customerEntity.setPassportNo(customerDTO.getPassportNo());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setCity(customerDTO.getCity());
        customerEntity.setCountry(customerDTO.getCountry());
        customerEntity.setBookingCount(Integer.parseInt(customerDTO.getBookingCount()));

        return customerRepository.updateCustomer(customerEntity);
    }
}
