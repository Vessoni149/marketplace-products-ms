package com.integrador.msproducts.controller;

import com.integrador.msproducts.dto.ProductDto;
import com.integrador.msproducts.model.Product;
import com.integrador.msproducts.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = {
        "https://marketplace-pink-eta.vercel.app/",
        "https://marketplace-hgw3sm957-vessoni149s-projects.vercel.app/"
        }, allowCredentials = "true")
public class ProductController {
    @Autowired
    private IProductService productServ;

    @Value("${server.port}")
    private int serverPort;
    @GetMapping("/products/get")
    public List<Product> getProducts(){
        return productServ.getProducts();
    }

    @GetMapping("/products/get/{code}")
    public Product getProduct(@PathVariable int code){
        Product produ = productServ.findProduct(code);
        return produ;
    }

    @GetMapping("products/get/seller/{sellerId}")
    public List<Product> getSellerProducts(@PathVariable int sellerId){
        return productServ.findSellerProducts(sellerId);
    }

    @PostMapping(value = "/products/create", consumes = { "multipart/form-data" })
    public ResponseEntity<Product> saveProduct(@RequestParam("productName") String productName,
                                               @RequestParam("sellerCode") int sellerCode,
                                               @RequestParam("sellerName") String sellerName,
                                               @RequestParam("brand") String brand,
                                               @RequestParam("price") double price,
                                               @RequestParam("description") String description,
                                               @RequestParam("amount") int amount,
                                               @RequestParam("images") List<MultipartFile> images) throws IOException {

        // Validar caracteres especiales en los parámetros
        if (!isValidInput(productName) || !isValidInput(sellerName) || !isValidInput(brand) || !isValidInput(description)) {
            // Manejar el error o advertir al usuario sobre caracteres no permitidos
            return ResponseEntity.badRequest().build();
        }

        ProductDto productDto = new ProductDto();
        productDto.setProductName(productName);
        productDto.setSellerCode(sellerCode);
        productDto.setSellerName(sellerName);
        productDto.setBrand(brand);
        productDto.setPrice(price);
        productDto.setAmount(amount);
        productDto.setDescription(description);
        productDto.setImages(images);

        return productServ.saveProduct(productDto);
    }

    private boolean isValidInput(String input) {
        // Evitar caracteres especiales y otros caracteres no deseados
        return !Pattern.compile("[`!@#^&*_+\\-=\\[\\]{};'\"\\\\|<>~]").matcher(input).find();
    }



    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productServ.deleteProduct(id);
        return "Product deleted";
    }

    @PutMapping("/products/edit")
    public String editProduct(@RequestBody ProductDto productDto) throws IOException {
        productServ.editProduct(productDto);
        return "Product edited";
    }


}
