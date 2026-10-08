package com.example.productcatalog.controller;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/products/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product("", "", null));
        model.addAttribute("pageTitle", "Add product");
        return "products/form";
    }

    @GetMapping("/products/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("pageTitle", "Edit product");
        return "products/form";
    }

    @PostMapping("/products")
    public String save(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Add product");
            return "products/form";
        }
        productService.save(new Product(product.getName(), product.getDescription(), product.getPrice()));
        return "redirect:/";
    }

    @PostMapping("/products/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Product product,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Edit product");
            return "products/form";
        }
        productService.update(id, product);
        return "redirect:/";
    }

    @PostMapping("/products/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }
}