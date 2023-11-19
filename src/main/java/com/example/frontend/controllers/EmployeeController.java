package com.example.frontend.controllers;

import com.example.backend.enums.EmployeeStatus;
import com.example.backend.models.Employee;
import com.example.backend.repositories.EmployeeRepository;
import com.example.backend.services.EmployeeService;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/employees")
    public String employeeListPaging(HttpSession session,
                                     Model model,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Employee> employeePage = employeeService.findPaginated(
                currentPage - 1,pageSize,"fullname","asc");
        model.addAttribute("employeePage",employeePage);
        int totalPage = employeePage.getTotalPages();
        if (totalPage > 0 ){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "employee/listemployee";
    }
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        Optional<Employee> op = employeeRepository.findById(id);
        if (op.isPresent()){
           Employee employee = op.get();
           employee.setStatus(EmployeeStatus.TERMINATED);
           employeeRepository.save(employee);
        }
        return "redirect:/admin/employees";
    }
    @GetMapping("/employees/show-add-form")
    public String add(Model model){
        Employee employee = new Employee();
        model.addAttribute("addEmployee",employee);
        return "employee/addemployee";
    }
    @PostMapping("/employees/addEmployee")
    public String addEmployee(@ModelAttribute("addEmployee")Employee employee){
        employeeRepository.save(employee);
        return "redirect:/admin/employees";
    }
    @GetMapping("/employees/updateEmployee/{id}")
    public String update(@PathVariable("id")long id,Model model){
        Optional<Employee> op = employeeRepository.findById(id);
        if (op.isPresent()){
            model.addAttribute("updateEmployee",op.get());
        }
        return "employee/updateemployee";
    }
    @PostMapping("/employees/updateEmployee")
    public String updateEmployee(@ModelAttribute("updateEmployee") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/admin/employees";
    }
}
