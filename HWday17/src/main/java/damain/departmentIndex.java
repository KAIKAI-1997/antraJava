package damain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "department_id")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class departmentIndex {
    @Id
    private String departmentName;

    @Column
    private String departmentId;

    @OneToMany(mappedBy = "departmentID", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<employee> employees = new ArrayList<>();

    @Override
    public String toString() {
        return "The department: " +
                departmentName+ "the Id is: "+
                departmentId;
    }
}
