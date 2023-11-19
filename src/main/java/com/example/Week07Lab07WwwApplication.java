package com.example;

import com.example.backend.enums.EmployeeStatus;
import com.example.backend.enums.ProductStatus;
import com.example.backend.models.Customer;
import com.example.backend.models.Employee;
import com.example.backend.models.Product;
import com.example.backend.repositories.CustomerRepository;
import com.example.backend.repositories.EmployeeRepository;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.services.ProductService;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Week07Lab07WwwApplication {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    public static void main(String[] args) {
        SpringApplication.run(Week07Lab07WwwApplication.class, args);

    }
//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args -> {
//            Faker faker = new Faker();
//            Device device = faker.device();
//            for (int i =0;i<100;i++){
//                Product product = new Product(
//                        device.modelName(), faker.lorem().paragraph(30),
//                        "piece", device.manufacturer(), ProductStatus.ACTIVE
//                );
//                productRepository.save(product);
//            }
//        };
//    }
//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args -> {
//            Faker faker = new Faker();
//            for (int i =0;i<50;i++){
//                String name = faker.name().fullName();
//                String address = faker.address().fullAddress();
//                String email = faker.internet().emailAddress();
//                String phone = faker.phoneNumber().cellPhone();
//                Customer customer = new Customer(name,email,phone,address);
//                customerRepository.save(customer);
//            }
//        };
//    }
//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args -> {
//            Faker faker = new Faker();
//            for (int i =0;i<20;i++){
//                String name = faker.name().fullName();
//                String address = faker.address().fullAddress();
//                String email = faker.internet().emailAddress();
//                String phone = faker.phoneNumber().cellPhone();
//                LocalDate dob = faker.date().birthday().toLocalDateTime().toLocalDate();
//                Employee employee = new Employee(name,dob,email,phone,address, EmployeeStatus.ACTIVE);
//                employeeRepository.save(employee);
//            }
//        };
//    }
//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args -> {
//            long i = 11;
//            Employee employee = employeeRepository.findById(i);
//
//        };
//    }
}
