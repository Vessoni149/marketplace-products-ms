package com.integrador.msproducts.service;

import com.integrador.msproducts.dto.ProductDto;
import com.integrador.msproducts.model.Product;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    public List<Product> getProducts();
    public ResponseEntity<Product> saveProduct(ProductDto productDto) throws IOException;
    public void deleteProduct(int id);
    public Product findProduct(int id);
    public String editProduct(ProductDto productDto) throws IOException;

    List<Product> findSellerProducts(int sellerId);
}
