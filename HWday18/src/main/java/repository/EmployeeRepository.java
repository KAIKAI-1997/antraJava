package repository;

import domain.employee;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface EmployeeRepository {
    Map<String, employee> employeeMap=new HashMap<>();

    public void init();

    public employee getEmployeeById(String id);

    public List<Object> getAllEmployees() ;
}
