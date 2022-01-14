package com.example.hellospring.service;

import com.example.hellospring.entity.Employee;
import com.example.hellospring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceimpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public boolean delete(Employee employee) {
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }
}
