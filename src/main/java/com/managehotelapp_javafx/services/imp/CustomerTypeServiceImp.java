package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.CustomerTypeDTO;
import com.managehotelapp_javafx.entity.CustomerTypeEntity;
import com.managehotelapp_javafx.repository.CustomerTypeRepository;
import com.managehotelapp_javafx.repository.imp.CustomerTypeRepositoryImp;
import com.managehotelapp_javafx.services.CustomerTypeService;

import java.util.ArrayList;
import java.util.List;

public class CustomerTypeServiceImp implements CustomerTypeService {
    CustomerTypeRepository customerTypeRepository = new CustomerTypeRepositoryImp();
    @Override
    public List<CustomerTypeDTO> customerTypeDTOList() {
        List<CustomerTypeDTO> customerTypeDTOList = new ArrayList<>();
        customerTypeRepository.getCustomerTypeList().forEach(customerTypeEntity -> {

            CustomerTypeDTO customerTypeDTO = new CustomerTypeDTO();

            customerTypeDTO.setId(customerTypeEntity.getId());
            customerTypeDTO.setTitle(customerTypeEntity.getTitle());
            customerTypeDTOList.add(customerTypeDTO);
        });
        return customerTypeDTOList ;
    }

    @Override
    public CustomerTypeDTO findCustomerTypeById(int idCus) {
        CustomerTypeEntity customerType = customerTypeRepository.findCustomerTypeById(idCus);
        CustomerTypeDTO customerTypeDTO = new CustomerTypeDTO();
        customerTypeDTO.setId(customerType.getId());
        customerTypeDTO.setTitle(customerType.getTitle());
        return customerTypeDTO;
    }

    @Override
    public CustomerTypeDTO findCustomerTypeByType(String cusType) {
        CustomerTypeEntity customerType = customerTypeRepository.findCustomerTypeByTitle(cusType);
        CustomerTypeDTO customerTypeDTO = new CustomerTypeDTO();
        customerTypeDTO.setId(customerType.getId());
        customerTypeDTO.setTitle(customerType.getTitle());
        return customerTypeDTO;
    }

    @Override
    public boolean insertCustomerType(CustomerTypeDTO customerTypeDTO) {
        return false;
    }

    @Override
    public boolean deleteCustomerType(int idCus) {
        return false;
    }

    @Override
    public boolean updateCustomerType(CustomerTypeDTO customerTypeDTO) {
        return false;
    }
}
