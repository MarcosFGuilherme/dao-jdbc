package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.CreateSellerDao();
		
		System.out.println("=== Teste number One : seller finfById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("=== Teste number Two : seller finfDepartment ===");
		Department departament = new Department(2,null);
		List<Seller> list = sellerDao.findDepartment(departament); 
		for (Seller s : list) {
			System.out.println(s);
		}
		
		System.out.println("=== Teste number Tree : seller findAll ===");
		list = sellerDao.findAll(); 
		for (Seller s : list) {
			System.out.println(s);
		}
		
	}

}
