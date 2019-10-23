package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Db.DB;
import Db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJdbc implements SellerDao {

	private Connection conn;

	public SellerDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller sel = instantieteSeller(rs, dep);
				return sel;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatemet(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantieteSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller();
		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBaseSalary(rs.getDouble("BaseSalary"));
		sel.setBirthDate(rs.getDate("BirthDate"));
		sel.setDepartment(dep);
		return sel;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findDepartment(Department departament) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = 
					"SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id " 
					+ "WHERE DepartmentId = ? " 
					+ "ORDER BY Name ";

			st = conn.prepareStatement(sql);
			st.setInt(1, departament.getId());
			rs = st.executeQuery();
			
			List<Seller> selList = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>(); // Ctrl para nao repetir o depto.
			
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentID"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.putIfAbsent(rs.getInt("DepartmentID"), dep);
				}
				
				Seller sel = instantieteSeller(rs, dep);
				selList.add(sel);
			}
			return selList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatemet(st);
			DB.closeResultSet(rs);
		}
	}
}
