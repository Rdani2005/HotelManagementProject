package edu.hotel.administrator.contracts.domain.service.repository;

import edu.hotel.administrator.contracts.domain.entity.Contract;
import edu.hotel.administrator.contracts.domain.valueobject.ContractId;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;

public interface ContractRepository {
    public Contract getContractById(ContractId id);
    
    public Contract getContractByEmployeeId(EmployeeId id);
    
    public Contract[] getAllContracts();
    
    public boolean employeeHasContract(EmployeeId id);
    
    public void addContract(Contract contract);
}
