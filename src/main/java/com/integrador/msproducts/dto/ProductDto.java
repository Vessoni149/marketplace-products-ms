package com.integrador.msproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private String productName;
    private int sellerCode;
    private String sellerName;
    private String brand;
    private double price;
    private int amount;
    private String description;
    private List<MultipartFile> images;
}
