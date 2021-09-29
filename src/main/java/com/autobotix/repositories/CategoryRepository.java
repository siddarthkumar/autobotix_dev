package com.autobotix.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.autobotix.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

	@Query(value = "SELECT category_code FROM category",nativeQuery = true)
	Collection<String> findAllCategoryCode();
	
}
