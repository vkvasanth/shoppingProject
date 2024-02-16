package com.shop.ecomproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.ecomproject.Repository.ProductRepository;
import com.shop.ecomproject.model.Products;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements  ProductService{

    @Autowired
    ProductRepository productRepository;

  
    @Override
    public Products createNewPorduct(Products product) {
        String variant = product.getVariant();

           try{
               Products isExist =  productRepository.findByVariant(variant);

               if (isExist == null){
                   return productRepository.save(product);

               }else {
                   throw new Exception("variant already exists " + variant);
               }

           } catch (Exception e) {
               e.printStackTrace();
              return null;
           }
    }

   
    @Override
    public Products updateProduct(Products products) {
        try{
            // Check if the product ID exists in the database
            if(productRepository.findById(products.getId()).isPresent()){
                // Product ID found, update the product in the database
               return  productRepository.save(products);
            }else {
                // Product ID not found, throw an exception
                throw new Exception("product id not found ");

            }
        } catch (Exception e) {
            // Handle the exception (print stack trace for now)
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Products findProductByBrand(String brandName) {
        try{
            // Attempt to find a product with the given brand name
            Optional<Products> productsOptional = productRepository.findByBrand(brandName);

            // Check if a product with the given brand name is found
            if (productsOptional.isPresent()){
                return productsOptional.get();
            }else {
                // Throw an exception if no product is found
                throw new Exception("No such Brand found");
            }
        } catch (Exception e) {
            // Wrap and rethrow any unexpected exception as a runtime exception
            throw new RuntimeException("Error while finding product by brand", e);
        }
    }

    @Override
    public void deleteProductById(int id) {

        try{
            // Attempt to find a product with the given ID
            Optional<Products> productsOptional = productRepository.findById(id);

            // Check if a product with the given ID is found
            if(productsOptional.isPresent()){
                // Delete the product from the database
                productRepository.deleteById(id);
            }else {
                // Throw an exception if no product is found with the specified ID
                throw new Exception("No product found with ID: " + id);
            }
        } catch (Exception e) {
            // Wrap and rethrow any unexpected exception as a runtime exception
            throw new RuntimeException("Error while deleting product", e);
        }

    }

//    @Override
//    public String findByVariant(String variant) {
//        return productRepository.findByVariant(variant);
//    }


}
