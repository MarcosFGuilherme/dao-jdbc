package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Db.DB;
import Db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJdbc implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			String sql =
					"INSERT INTO  department"
					+ "(Name) "
					+ "VALUES "
					+ "(?) ";
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected.");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatemet(st);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			String sql =
					"UPDATE department "
					+ "SET Name = ? "
					+ "WHERE Id = ? ";
					
			st = conn.prepareStatement(sql);
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatemet(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			String sql =
					"DELETE FROM department " 
					+ "WHERE Id = ? ";
					
			st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatemet(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = 
					"SELECT * FROM coursejdbc.department " 
					+ "WHERE Id = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatemet(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM coursejdbc.department "; 

			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			List<Department> depList = new ArrayList<>();
			
			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				depList.add(dep);
			}
			return depList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatemet(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}
}
