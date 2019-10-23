package model.dao;

import model.dao.Impl.SellerDaoJdbc;

public class DaoFactory {
	public static SellerDao CreateSellerDao() {
		return new SellerDaoJdbc();
	}
}
