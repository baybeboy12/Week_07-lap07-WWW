package com.example.backend.services;

import com.example.backend.models.Customer;
import com.example.backend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> findPaginated(int pageNo, int pageSize, String sortBy, String sortName){
        Sort sort = Sort.by(Sort.Direction.fromString(sortName),sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        return  customerRepository.findAll(pageable);
    }
}
