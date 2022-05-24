package HWday14;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "employee")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class employee {
    @Id
    private String ID;

    @Column(name = "salary")
    private String salary;

    @Column(name = "departmentID")
    private String departmentID;

    @Override
    public String toString() {
        return "The employee " +
                ID + " in department " +
                departmentID +
                " has salary" +
                salary;
    }
}

