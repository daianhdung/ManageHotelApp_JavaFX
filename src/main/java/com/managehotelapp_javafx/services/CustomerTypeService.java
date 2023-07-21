package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.CustomerDTO;
import com.managehotelapp_javafx.dto.CustomerTypeDTO;
import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.entity.UserRoleEntity;

import java.util.List;

public interface CustomerTypeService {

    List<CustomerTypeDTO> customerTypeDTOList ();
    CustomerTypeDTO findCustomerTypeById(int idCus);
    CustomerTypeDTO findCustomerTypeByType(String cusType);

    boolean insertCustomerType (CustomerTypeDTO customerTypeDTO);
    boolean deleteCustomerType (int idCus);
    boolean updateCustomerType (CustomerTypeDTO customerTypeDTO);

}
