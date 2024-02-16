package com.shop.ecomproject.services;

import com.shop.ecomproject.model.Products;

public interface ProductService {
	Products createNewPorduct(Products product);

    Products updateProduct(Products product);

    Products findProductByBrand(String brandName);

    void deleteProductById(int id);

}
