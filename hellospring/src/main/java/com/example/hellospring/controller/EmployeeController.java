package com.example.hellospring.controller;

import com.example.hellospring.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    private static ArrayList<Employee> listEmployee;
    static {
        listEmployee = new ArrayList<>();
        listEmployee.add(new Employee(1, "Huong", 21, 10000));
        listEmployee.add(new Employee(2, "Nhat", 23, 20000));
        listEmployee.add(new Employee(3, "Minh", 24, 30000));
        listEmployee.add(new Employee(4, "Hoang", 26, 40000));
        listEmployee.add(new Employee(5, "Duc", 25, 50000));
    }

    //create
    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee){
        listEmployee.add(employee);
        return employee;
    }

    //getList
    @RequestMapping(method = RequestMethod.GET)
    public HashMap<String, Object> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit){
        HashMap<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("data", listEmployee);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    //getDetail
    public Employee getDetail(@PathVariable int id){
        Employee employee = null;
        for (int i = 0; i < listEmployee.size(); i++) {
            if (listEmployee.get(i).getId() == id){
                employee = listEmployee.get(i);
                break;
            }
        }
        return employee;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    //update
    public Employee update(@PathVariable int id, @RequestBody Employee updatedEmployee){
        Employee employee = null;
        // tìm kiếm trong danh sách
        for (int i = 0; i < listEmployee.size(); i++) {
            if (listEmployee.get(i).getId() == id){
                employee = listEmployee.get(i);
                break;
            }
        }
        if (employee != null){
            employee.setName(updatedEmployee.getName());
            employee.setAge(updatedEmployee.getAge());
            employee.setSalary(updatedEmployee.getSalary());
        }
        return employee;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    //delete
    public boolean delete(@PathVariable int id){
        int indexItem = -1;
        for (int i = 0; i < listEmployee.size(); i++) {
            if (listEmployee.get(i).getId() == id){
                indexItem = i;
            }
        }
        if (indexItem == -1){
            return false;
        }
        listEmployee.remove(indexItem);
        return true;
    }

}