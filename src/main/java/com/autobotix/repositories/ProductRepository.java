package com.autobotix.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.autobotix.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

	@Query(value = "SELECT product_code FROM product",nativeQuery = true)
			Collection<String> findAllProductCode();
	
	@Query(value = "SELECT * FROM product INNER JOIN  product_category_list ON product_category_list.category_list LIKE :cat AND product_category_list.product_product_code=product.product_code",nativeQuery = true)
	Collection<Product> findProductsByCategory(@Param("cat") String code);
}
