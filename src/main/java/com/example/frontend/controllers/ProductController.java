package com.example.frontend.controllers;

import com.example.backend.enums.ProductStatus;
import com.example.backend.models.Product;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.services.ProductService;
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
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProductListPaging(
            HttpSession session,
            Model model,
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size")Optional<Integer> size
            ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Product> productPage = productService.findPaginated(currentPage-1,pageSize,
                "name","asc");
        model.addAttribute("productPage",productPage);
        int totalPages = productPage.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "product/listproduct";
    }
    @GetMapping("/products/delete/{productId}")
    public String deleteProduct (@PathVariable("productId") Long productId){
        Optional<Product> op = productRepository.findById(productId);
        if (op.isPresent()){
            Product product = op.get();
            product.setStatus(ProductStatus.TERMINATED);
            productRepository.save(product);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/products/show-add-form")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("addProduct", product);
        return "product/addproduct";
    }
    @PostMapping("/products/addProduct")
    public String addProduct (@ModelAttribute("addProduct") Product product){
        productRepository.save(product);
        return  "redirect:/admin/products";
    }
    @GetMapping("/products/updateProduct/{productId}")
    public String update(@PathVariable("productId") Long productId,
                         Model model) {
        Optional<Product> op = productRepository.findById(productId);
        if (op.isPresent()) {
            model.addAttribute("updateProduct", op.get());
        }
        return "product/updateproduct";
    }
    @PostMapping("/products/updateProduct")
    public String  updateProduct (@ModelAttribute("updateProduct") Product product){
        productRepository.save(product);
        return  "redirect:/admin/products";
    }
}
