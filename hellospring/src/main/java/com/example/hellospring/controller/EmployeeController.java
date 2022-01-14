package com.example.hellospring.controller;

import com.example.hellospring.entity.Employee;
import com.example.hellospring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //get list
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> read(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10")int limit )
    {
        HashMap<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("data", employeeService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    //get details
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id){
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()){
            return new ResponseEntity<>(optionalEmployee.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    //update
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Employee updateEmployee){
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setName(updateEmployee.getName());
            employee.setAge(updateEmployee.getAge());
            employee.setSalary(updateEmployee.getSalary());
            employeeService.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //delete
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<Object>  delete(@PathVariable int id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeService.delete(optionalEmployee.get());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}