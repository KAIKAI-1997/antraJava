package Controller;

import domain.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employees")
public class controller {
    @Autowired
    public void EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getAllEmployeesByBound(@PathVariable String Bound) {
        return new ResponseEntity<>(employeeService.getAllEmployeesByBound(Bound), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getAllEmployeesbyGroup(@PathVariable String aim) {
        return new ResponseEntity<>(employeeService.getAllEmployeesbyGroup(aim), HttpStatus.OK);
    }
}
