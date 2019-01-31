package com.nts.reservation.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nts.reservation.config.ApplicationConfig;
import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;


public class CategoryDaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CategoryDao dao = ac.getBean(CategoryDao.class);
		
		List<Category> categoris = dao.selectAll();
		System.out.println("!!");
		int i = 1;
		for (Category category : categoris) {
			System.out.println(category);
		}
	}

}
