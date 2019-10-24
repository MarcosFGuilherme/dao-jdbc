package application;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.CreateSellerDao();
		
		System.out.println("=== Teste number One : seller finfById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("=== Teste number Two : seller finfDepartment ===");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findDepartment(department); 
		for (Seller s : list) {
			System.out.println(s);
		}
		
		System.out.println("=== Teste number Tree : seller findAll ===");
		list = sellerDao.findAll(); 
		for (Seller s : list) {
			System.out.println(s);
		}
		
		/*
		System.out.println("=== Teste number Four : seller insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! new id = " + newSeller.getId());
		*/
		
		/*
		System.out.println("=== Teste number Five : seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Martha Waine");
		seller.setEmail("martha@gmail.com");
		seller.setBaseSalary(5200.0);
		sellerDao.update(seller);
		System.out.println("Update completed!");
		*/
		
		System.out.println("=== Teste number Six : seller delet ===");
		System.out.print("Enter id for delete teste:");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
		
		
		sc.close();
	}

}
