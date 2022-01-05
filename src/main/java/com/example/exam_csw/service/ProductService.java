package com.example.exam_csw.service;

import com.example.exam_csw.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product sellProduct(Product product);
    Product getProductById(int id);

}
