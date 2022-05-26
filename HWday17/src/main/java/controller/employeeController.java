package controller;

import damain.db;
import damain.employee;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * http://localhost/user  GET  search all user
 * http://localhost/user/1  GET  search specific user
 * http://localhost/user  POST   insert user
 * http://localhost/user  PUT  Modify user
 * http://localhost/user/1  DELETE  delete specific user
 */


@Controller
@RequestMapping("/employees")
public class employeeController {
    @RequestMapping("/test")
    @ResponseBody
    public String save(){
        System.out.println("test success!");
        return "{'status':'200'}";
    }

    //return all employees
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result getAllEmployees(){
        List<employee> res = db.getAllEmployees();
        Integer status=(res==null)?code.GET_OK:code.GET_ERR;
        return new Result(status, res);
    }

    //retrieve by department name
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<employee> getStudentsByDepartmentId(String departmentName){
        List<employee> res = db.getStudentsByDepartmentId(departmentName);
        return res;
    }

    //return specific employees
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public employee getEmployee(@PathVariable String id){
        employee res = db.getEmployeeById(id);
        return res;
    }

    //insert employee
    @RequestMapping( method = RequestMethod.POST)
    @ResponseBody
    public String insertEmployee(employee employee){
        db.insertToemployee(employee);
        return "{'status':'200'}";
    }

    //modify employee
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String modifyEmployee(employee employee){
        db.modifyEmployee(employee);
        return "{'status':'200'}";
    }

    //Delete specific employee
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployee(@PathVariable String id){
        db.removeEmployee(id);
    }
}
