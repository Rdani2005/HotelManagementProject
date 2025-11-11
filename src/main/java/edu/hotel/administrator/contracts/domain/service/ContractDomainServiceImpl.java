package edu.hotel.administrator.contracts.domain.service;

import edu.hotel.administrator.contracts.domain.entity.Contract;
import edu.hotel.administrator.contracts.domain.exception.ContractDomainException;
import edu.hotel.administrator.contracts.domain.exception.ContractNotFoundDomainException;
import edu.hotel.administrator.contracts.domain.service.repository.ContractRepository;
import edu.hotel.administrator.contracts.domain.valueobject.ContractId;
import edu.hotel.administrator.employees.domain.exception.EmployeeNotFoundException;
import edu.hotel.administrator.employees.domain.service.EmployeeDomainService;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import java.util.Objects;

public class ContractDomainServiceImpl implements ContractDomainService {
    private final EmployeeDomainService employeeService;
    private final ContractRepository repository;

    public ContractDomainServiceImpl(EmployeeDomainService employeeService, ContractRepository repository) {
        this.employeeService = employeeService;
        this.repository = repository;
    }
    
     @Override
    public Contract getContractByEmployee(EmployeeId id) {
        Objects.requireNonNull(id, "EmployeeId must not be null");

        Contract contract = repository.getContractByEmployeeId(id);
        if (contract == null) {
            throw new ContractNotFoundDomainException("Contract for the given employee was not found.");
        }
        return contract;
    }

    @Override
    public Contract[] getContracts() {
        return repository.getAllContracts();
    }

    @Override
    public Contract getContractById(ContractId id) {
        Objects.requireNonNull(id, "ContractId must not be null");

        Contract c = repository.getContractById(id);
        if (c == null) {
            throw new ContractNotFoundDomainException("Contract was not found.");
        }
        return c;
    }

    @Override
    public void addEmployeeContract(EmployeeId employeeId, Contract contract) {
        Objects.requireNonNull(employeeId, "EmployeeId must not be null");
        Objects.requireNonNull(contract, "Contract must not be null");

        // 1) El empleado debe existir
        boolean exists = employeeService.validateEmployeeExists(employeeId);
        if (!exists) {
            throw new EmployeeNotFoundException("Employee does not exist.");
        }

        if (repository.employeeHasContract(employeeId)) {
            throw new ContractDomainException("Employee already has a contract.");
        }

        if (!employeeId.equals(contract.getEmployeeId())) {
            throw new IllegalArgumentException("Contract's employeeId does not match the provided EmployeeId.");
        }

        repository.addContract(contract);
    }
}
