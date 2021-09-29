package com.autobotix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.autobotix.models.Category;
import com.autobotix.models.Product;
import com.autobotix.repositories.CategoryRepository;
import com.autobotix.repositories.ProductRepository;

@SpringBootApplication
public class AutobotixApplication implements CommandLineRunner{
    
	@Resource
	ProductRepository repository;
	
	@Resource
	CategoryRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(AutobotixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category("cat1","firstcategory","this is a demo category");
		Category cat2 = new Category("cat2","Secondcategory","this is a demo category2");
		Category cat3 = new Category("cat3","Thirdcategory","this is a demo category3");
		repo.save(cat1);
		repo.save(cat2);
		repo.save(cat3);		
	}

}
