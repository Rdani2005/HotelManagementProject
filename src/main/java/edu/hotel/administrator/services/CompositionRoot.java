package edu.hotel.administrator.services;

import edu.hotel.administrator.contracts.domain.service.ContractDomainService;
import edu.hotel.administrator.contracts.domain.service.ContractDomainServiceImpl;
import edu.hotel.administrator.contracts.domain.service.repository.ContractRepository;
import edu.hotel.administrator.contracts.domain.service.repository.ContractRepositoryImpl;
import edu.hotel.administrator.employees.domain.service.EmployeeDomainService;
import edu.hotel.administrator.employees.domain.service.EmployeeDomainServiceImpl;
import edu.hotel.administrator.employees.domain.service.repository.EmployeeRepository;
import edu.hotel.administrator.users.domain.service.UserAplicationService;
import edu.hotel.administrator.users.domain.service.UserApplicationServiceImpl;
import edu.hotel.administrator.users.domain.service.repository.UserRepository;
import edu.hotel.administrator.users.domain.service.repository.UserRepositoryImpl;
import edu.hotel.administrator.employees.domain.service.repository.EmployeeRepositoryImpl;
import java.util.Objects;
import java.util.function.Supplier;

public class CompositionRoot {
    private final UserRepository userRepository;
    private final UserAplicationService userService;
    
    private final EmployeeRepository employeeRepository;
    private final EmployeeDomainService employeeService;
    
    private final ContractRepository contractRepository;
    private final ContractDomainService contractService;
    
    public UserAplicationService userService() {
        return userService;
    }
    
    public EmployeeDomainService employeeService() {
        return employeeService;
    }
    
    public ContractDomainService contractService() { return contractService; }
    
    public EmployeeRepository employeeRepository() {
        return employeeRepository;
    }
    
    private static Supplier<UserRepository> userRepositoryProvider = UserRepositoryImpl::new;
    private static Supplier<EmployeeRepository> employeeRepositoryProvider = EmployeeRepositoryImpl::new;
    private static Supplier<ContractRepository> contractRepositoryProvider = ContractRepositoryImpl::new;

    public static void setProviders(
            Supplier<UserRepository> userRepoProvider,
            Supplier<EmployeeRepository> employeeRepoProvider,
            Supplier<ContractRepository> contractRepoProvider
    ) {
        if (isInitialized()) {
            throw new IllegalStateException("CompositionRoot already initialized");
        }
        userRepositoryProvider = Objects.requireNonNull(userRepoProvider, "userRepoProvider must not be null");
        employeeRepositoryProvider = Objects.requireNonNull(employeeRepoProvider, "employeeRepoProvider must not be null");
        contractRepositoryProvider = Objects.requireNonNull(contractRepoProvider, "contractRepoProvider must not be null");
    }

    private static final class Holder {
        private static final CompositionRoot INSTANCE = new CompositionRoot();
    }
    
    public static CompositionRoot getInstance() {
        return Holder.INSTANCE;
    }
    
    private static boolean isInitialized() {
        try {
            Class.forName(Holder.class.getName());
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    private CompositionRoot() {
        this.userRepository = userRepositoryProvider.get();
        this.userService = new UserApplicationServiceImpl(userRepository);
        this.employeeRepository = employeeRepositoryProvider.get();
        this.employeeService = new EmployeeDomainServiceImpl(employeeRepository);
        
        this.contractRepository = contractRepositoryProvider.get();
        this.contractService = new ContractDomainServiceImpl(employeeService, contractRepository);
    }
    
}
