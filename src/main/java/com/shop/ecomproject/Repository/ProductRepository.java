package com.shop.ecomproject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.ecomproject.model.Products;

@Repository
public interface ProductRepository extends JpaRepository <Products,Integer> {
	  Products findByVariant(String variant);
	    Optional<Products> findByBrand(String brandName);
}
