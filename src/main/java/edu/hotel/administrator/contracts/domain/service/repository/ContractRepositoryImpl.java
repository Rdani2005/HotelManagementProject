package edu.hotel.administrator.contracts.domain.service.repository;

import edu.hotel.administrator.contracts.domain.entity.Contract;
import edu.hotel.administrator.contracts.domain.valueobject.ContractId;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import java.util.Objects;

public class ContractRepositoryImpl implements ContractRepository {

    private Contract[] contracts;
    private int size;

    public ContractRepositoryImpl() {
        this.contracts = new Contract[1];
        this.size = 0;
    }

    @Override
    public Contract getContractById(ContractId id) {
        if (id == null) {
            return null;
        }
        int idx = indexOf(id);
        return (idx >= 0) ? contracts[idx] : null;
    }

    @Override
    public Contract getContractByEmployeeId(EmployeeId id) {
        if (id == null) {
            return null;
        }
        int idx = indexOfByEmployee(id);
        return (idx >= 0) ? contracts[idx] : null;
    }

    @Override
    public Contract[] getAllContracts() {
        Contract[] snapshot = new Contract[size];
        System.arraycopy(contracts, 0, snapshot, 0, size);
        return snapshot;
    }

    @Override
    public boolean employeeHasContract(EmployeeId id) {
        return getContractByEmployeeId(id) != null;
    }

    @Override
    public void addContract(Contract contract) {
        Objects.requireNonNull(contract, "contract must not be null");

        // Upsert por ContractId
        int existingById = indexOf(contract.getId());
        if (existingById >= 0) {
            contracts[existingById] = contract;
            return;
        }

        int existingByEmployee = indexOfByEmployee(contract.getEmployeeId());
        if (existingByEmployee >= 0) {
            contracts[existingByEmployee] = contract;
            return;
        }

        if (size == contracts.length) {
            growByOne();
        }
        contracts[size++] = contract;
    }

    private void growByOne() {
        Contract[] bigger = new Contract[contracts.length + 1];
        System.arraycopy(contracts, 0, bigger, 0, contracts.length);
        contracts = bigger;
    }

    private int indexOf(ContractId id) {
        for (int i = 0; i < size; i++) {
            Contract c = contracts[i];
            if (c != null && c.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int indexOfByEmployee(EmployeeId employeeId) {
        for (int i = 0; i < size; i++) {
            Contract c = contracts[i];
            if (c != null && c.getEmployeeId().equals(employeeId)) {
                return i;
            }
        }
        return -1;
    }

}
