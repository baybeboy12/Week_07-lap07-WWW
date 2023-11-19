package com.example.backend.repositories;

import com.example.backend.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface EmployeeRepository extends JpaRepository<Employee,Long> , JpaSpecificationExecutor<Employee> {
    @Query("select e from Employee e where e.status = 1 or e.status = 0")
    public Page<Employee> findAll(Pageable pageable);


}
