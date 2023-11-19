package com.example.backend.services;

import com.example.backend.models.Employee;
import com.example.backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private  EmployeeRepository employeeRepository;
    public Page<Employee> findPaginated(int noPage, int pageSize,String sortBy,String sortName ){
        Sort sort = Sort.by(Sort.Direction.fromString(sortName),sortBy);
        Pageable pageable = PageRequest.of(noPage,pageSize,sort);
        return employeeRepository.findAll(pageable);
    }

}
