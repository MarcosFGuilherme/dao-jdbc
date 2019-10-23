package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department department = new Department(1,"Books");
		
		System.out.println(department);
		
		Seller seller = new Seller(21,"Bob","bob@gmail.com",new Date(), 3000.0, department);
		
		SellerDao sellerDao = DaoFactory.CreateSellerDao();
		
		System.out.println(seller);
	}

}
