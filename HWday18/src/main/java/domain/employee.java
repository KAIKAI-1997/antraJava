package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class employee {
    private Integer ID;
    private String employee_name;
    private String employee_salary;
    private String employee_age;
    private String profile_img;
}