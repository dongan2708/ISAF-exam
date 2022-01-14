package com.example.hellospring.service;

import com.example.hellospring.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    boolean delete(Employee employee);

   Employee update(Employee employee);
}
