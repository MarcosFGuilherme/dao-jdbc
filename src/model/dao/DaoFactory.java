package model.dao;

import Db.DB;
import model.dao.Impl.SellerDaoJdbc;

public class DaoFactory {
	public static SellerDao CreateSellerDao() {
		return new SellerDaoJdbc(DB.getConnection());
	}
}
