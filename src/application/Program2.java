package application;

import java.util.List;
import java.util.Scanner;

import general.Utility;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DepartmentDao depDao = DaoFactory.CreateDepartmentDao();
		String opc = " ";
		
		while (!opc.substring(0,1).toUpperCase().equals("E")) {

			switch (opc) {
			case "S":
				showDepartment(0,depDao);
				break;
			case "F":
				find(sc, depDao);
				break;	
			case "I":
				insertDepartment(sc, depDao);
				break;	
			case "U":
				updateDepartment(sc, depDao);
				break;	
			case "D":
				deleteDepartment(sc, depDao);
				break;		
			default:
				if (!opc.equals(" ")) {
					System.out.println("Option choose [" + opc + "] is invalid !!!");
				}
			}
			opc = UI.menuScreen(sc);
		}
		sc.close();
	}
	public static void insertDepartment(Scanner sc, DepartmentDao depDao ) {
		System.out.print("Enter with new Department: ");
		String name = sc.next();
		Department newDepartment = new Department(null, name);
		depDao.insert(newDepartment);
		System.out.println("=== New department id=[" + newDepartment.getId() + "] ==");
		showDepartment(newDepartment.getId(), depDao);
	}
	public static void updateDepartment(Scanner sc, DepartmentDao depDao) {
		System.out.print("Enter Department Id for update: ");
		int id = sc.nextInt();
		Department dep = depDao.findById(id);
		if (dep != null) {
			showDepartment(id, depDao);
			System.out.print("Change name this department for :");
			String name = sc.next();
			dep.setName(name);
			depDao.update(dep);
			System.out.println("=== Update department id=[" + dep.getId() + "] ==");
			showDepartment(dep.getId(), depDao);
		}	
		else {
			System.out.print("Department not exists!!");
		}
	}
	public static void deleteDepartment(Scanner sc, DepartmentDao depDao) {
		System.out.print("Enter Department Id for delete: ");
		Integer id = sc.nextInt();
		depDao.deleteById(id);
		showDepartment(0, depDao);
	}
	public static void showDepartment(Integer id, DepartmentDao depDao) {
		System.out.println(Utility.stringFix("" , 40, "="));
		System.out.println(Utility.stringFix("ID" , 10, " ") +  Utility.stringFix(" " + "NAME" , 30, " " ));
		System.out.println(Utility.stringFix("" , 10, "-") + Utility.stringFix(" " , 30, "-"));
		if(id > 0) {
			Department dep = depDao.findById(id);
			System.out.println(dep);
		}
		else {
			List<Department> list = depDao.findAll(); 
			for (Department d : list) {
				System.out.println(d);
			}
		}
		System.out.println(Utility.stringFix("" , 40, "="));
	}
	public static void find(Scanner sc, DepartmentDao depDao) {
		System.out.print("Enter Department Id of find(id=0 show all Departments): ");
		int id = sc.nextInt();
		showDepartment(id, depDao);
	}
}
