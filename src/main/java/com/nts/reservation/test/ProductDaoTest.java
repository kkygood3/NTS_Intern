package com.nts.reservation.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;


public class ProductDaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ProductDao gbDao = ac.getBean(ProductDao.class);
		

		Category c = new Category();
		c.setId(2);
		c.setName("전시");
		System.out.println("4!");
		System.out.println( " ddd : "+ c);
		List<Product> products = gbDao.selectByCategory(c, 1, 20);
		System.out.println("!!");
		int i = 1;
		for (Product product : products) {
			System.out.println(" ## " + i++ + " ## ");
			System.out.println(product.getId());
		}
		
//		CategoryDao lDao = ac.getBean(CategoryDao.class);
//		Category log = new Category();
//		log.setIp("127.0.0.1");
//		log.setMethod("insert");
//		log.setRegdate(new Date());
//		lDao.insert(log);
	}

}
