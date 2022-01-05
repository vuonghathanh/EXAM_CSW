package com.example.exam_csw.controller;

import com.example.exam_csw.entity.Product;
import com.example.exam_csw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class WebController {
    @Autowired
    ProductService productService;

    @GetMapping({"/", "index"})
    public String index(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product",  new Product());
        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam Map<String, String> params){
        Product product = new Product(
                params.get("name"),
                Integer.parseInt(params.get("price")),
                Integer.parseInt(params.get("quantity"))
        );
        productService.addProduct(product);
        return "redirect:/index";
    }

    @GetMapping( "/edit")
    public String edit(@RequestParam int id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam Map<String, String> params){
        if (params.get("id") != null){
            Product product = new Product(
                    Integer.parseInt(params.get("id")),
                    params.get("name"),
                    Integer.parseInt(params.get("price")),
                    Integer.parseInt(params.get("quantity"))
            );
            productService.sellProduct(product);
        }
        return "redirect:/index";
    }
}
