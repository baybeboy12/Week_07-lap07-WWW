package com.example.frontend.controllers;

import com.example.backend.models.Customer;
import com.example.backend.repositories.CustomerRepository;
import com.example.backend.services.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/customers")
    public String showCustomerListPaging(HttpSession session,
                                         Model model,
                                         @RequestParam("page")Optional<Integer> page,
                                         @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Customer> customerPage = customerService.findPaginated(
                currentPage-1,pageSize,"name","asc");
        model.addAttribute("customerPage",customerPage);
        int totalPage =  customerPage.getTotalPages();
        if (totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "customer/listcustomer";
    }
    @GetMapping("/customers/show-add-customer")
    public String add(Model model){
        Customer customer = new Customer();
        model.addAttribute("addCustomer",customer);
        return "customer/addcustomer";
    }
    @PostMapping("/customers/addCustomer")
    public String addCustomer(@ModelAttribute("addCustomer") Customer customer){
        customerRepository.save(customer);
        return "redirect:/admin/customers";
    }
    @GetMapping("/customers/updateCustomer/{id}")
    public String update(@PathVariable("id") Long id,Model model){
            Optional<Customer> op = customerRepository.findById(id);
            if (op.isPresent()){
                model.addAttribute("updateCustomer",op.get());
            }
            return "customer/updatecustomer";
    }
    @PostMapping("/customers/updateCustomer")
    public String updateCustomer(@ModelAttribute("updateCustomer") Customer customer){
        customerRepository.save(customer);
        return "redirect:/admin/customers";
    }
}
