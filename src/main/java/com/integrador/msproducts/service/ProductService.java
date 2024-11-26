package com.integrador.msproducts.service;

import com.integrador.msproducts.dto.ProductDto;
import com.integrador.msproducts.model.Image;
import com.integrador.msproducts.model.Product;
import com.integrador.msproducts.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private CloudinaryService cloudinaryService;
    @Override
    public List<Product> getProducts() {
        List<Product> productsList = productRepo.findAll();
        return productsList;
    }


    @Override
    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product findProduct(int id) {
        Product product = productRepo.findById(id).orElse(null);
        return product;
    }

    @Override
    public ResponseEntity<Product> saveProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setSellerCode(productDto.getSellerCode());
        product.setSellerName(productDto.getSellerName());
        product.setBrand(productDto.getBrand());
        product.setAmount(productDto.getAmount());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        // Guardar el producto sin imágenes inicialmente
        Product savedProduct = productRepo.save(product);

        Set<Image> images = new LinkedHashSet<>(); // Usar LinkedHashSet para mantener el orden de inserción

        for (MultipartFile file : productDto.getImages()) {
            // Cargar la imagen en Cloudinary:
            Map<String, String> result = cloudinaryService.upload(file);
            String imageUrl = result.get("url");
            String publicId = result.get("public_id");

            // Crear una nueva instancia de Image y agregarla al set
            Image image = new Image(imageUrl, publicId);
            images.add(image);
        }

        savedProduct.setImage(images);

        // Guardar el producto con las imágenes
        productRepo.save(savedProduct);

        return ResponseEntity.ok(savedProduct);
    }


    @Override
    public String editProduct(ProductDto productDto) throws IOException {
        this.saveProduct(productDto);
        return "Product Edited";
    }

    @Override
    public List<Product> findSellerProducts(int sellerId) {

        return null;
    }
}
