package com.integrador.msproducts.service;

import com.integrador.msproducts.model.Image;

import java.util.List;
import java.util.Optional;

public interface IImageService{
    public List<Image> list();
    public Optional<Image> getOne(int id);
    public void save(Image image);
    public void delete(int id);
    public boolean exists(int id);
}
