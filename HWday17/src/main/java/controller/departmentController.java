package controller;

import damain.db;
import damain.departmentIndex;
import damain.employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class departmentController {
    //return all departments
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<departmentIndex> getAlldepartments(){
        List<departmentIndex> res = db.getAllDepartments();
        return res;
    }

    //return specific department
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public departmentIndex getEmployee(@PathVariable String name){
        departmentIndex res = db.getDepartmentsById(name);
        return res;
    }

    //insert department
    @RequestMapping( method = RequestMethod.POST)
    @ResponseBody
    public void insertEmployee(departmentIndex departmentIndex){
        db.insertTodepartment_id(departmentIndex);
    }

    //Delete specific department
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteDepartment(@PathVariable String name){
        db.removeDepartmentOnly(name);
    }
}
