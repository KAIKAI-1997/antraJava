package repository;

import domain.employee;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    Map<String, employee> employeeMap=new HashMap<>();

    @PostConstruct
    public void init() {
        String url = "http://dummy.restapiexample.com/api/v1/employees";
        JSONParser jParser = new JSONParser();
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            JSONArray a = (JSONArray) parser.parse(inputLine);

            // Loop through each item
            for (Object o : a) {
                employeeMap.put(o[employee_age],o.toString());
            }
        }
        in.close();
    }

    public employee getEmployeeById(String id) {
        return employeeMap.get(id);
    }

    public List<Object> getAllEmployees() {
        return employeeMap.values().stream().collect(Collectors.toList());
    }


}
