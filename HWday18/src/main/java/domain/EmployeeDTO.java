package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer ID;
    private String employee_name;
    private String employee_salary;
    private String employee_age;
    private String profile_img;

    public EmployeeDTO(employee employee) {
        this.ID = employee.getID();
        this.employee_name = employee.getEmployee_name();
        this.employee_salary = employee.getEmployee_salary();
        this.employee_age = employee.getEmployee_age();
        this.profile_img = employee.getProfile_img();
    }
}
