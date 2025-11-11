package edu.hotel.administrator.contracts.domain.service;

import edu.hotel.administrator.contracts.domain.entity.Contract;
import edu.hotel.administrator.contracts.domain.valueobject.ContractId;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;

public interface ContractDomainService {
    public Contract getContractByEmployee(EmployeeId id);
    
    public Contract[] getContracts();
    
    public Contract getContractById(ContractId id);
    
    public void addEmployeeContract(EmployeeId employee, Contract contract);
}
