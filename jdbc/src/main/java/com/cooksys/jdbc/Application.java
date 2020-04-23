package com.cooksys.jdbc;

import java.util.List;

import com.cooksys.jdbc.dao.BossDAO;
import com.cooksys.jdbc.dao.GruntDAO;

import entity.Boss;
import entity.Grunt;

public class Application {

	public static void main(String[] args) {
		
		BossDAO bossDAO = new BossDAO();
		GruntDAO gruntDAO = new GruntDAO(bossDAO);
		
//		Boss b1 = bossDAO.create(new Boss("Bob", 500));
//		Boss b2 = bossDAO.create(new Boss("Ted", 250, b1));
//		System.out.println(b2);
		
//		Boss b2 = bossDAO.getById(4);
		
//		b2.setSalary(400);
//		
//		bossDAO.update(b2);
		
//		bossDAO.delete(1);
//		bossDAO.delete(2);
		
//		Boss b1 = bossDAO.getById(3);
//		
//		bossDAO.create(new Boss("Will", 10, b1));
//		bossDAO.create(new Boss("Peter", 100, b1));
//		gruntDAO.create(new Grunt("Frank", 1000, b1));
//		gruntDAO.create(new Grunt("Karen", 1000, b1));
//		gruntDAO.create(new Grunt("Yolanda", 1000, b1));
//		
//		System.out.println(bossDAO.getChildren(b1));

		List<Boss> bosses = bossDAO.getAll();
		
		for (Boss b : bosses) {
			System.out.println(b);
		}
	}
	
}