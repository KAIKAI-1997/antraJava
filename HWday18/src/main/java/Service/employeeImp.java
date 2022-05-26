package Service;

import domain.EmployeeDTO;
import domain.employee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repository.EmployeeRepository;
import repository.EmployeeRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {
    private final Log logger = LogFactory.getLog(EmployeeServiceImpl.class);

    private final RestTemplate restTemplate;
    private final repository.EmployeeRepository EmployeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository EmployeeRepository, RestTemplate restTemplate) {
        this.EmployeeRepository = EmployeeRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) {
        employee employee = EmployeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new RuntimeException(id + ": employee is null");
        }
        return new EmployeeDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return EmployeeRepository
                .getAllEmployees()
                .stream()
                .map(e -> new EmployeeDTO((employee) e))
                .collect(Collectors.toList());

    }

    public List<EmployeeDTO> getAllEmployeesByBound(Integer bound) {
        return EmployeeRepository
                .getAllEmployees()
                .stream()
                .filter(e -> {
                    new EmployeeDTO((employee) e);
                    return getEmployeeById().getEmployee_age()>bound;
                })
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getAllEmployeesbyGroup(Integer aim) {
        return EmployeeRepository
                .getAllEmployees()
                .stream()
                .filter(e -> {
                    new EmployeeDTO((employee) e);
                    return getEmployeeById().getEmployee_age()==aim;
                })
                .collect(Collectors.toList());
    }
}
