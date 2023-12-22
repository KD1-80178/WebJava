package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunbeam.pojos.User;

public class UserDaoImpl extends Dao implements UserDao {
	public UserDaoImpl() throws Exception {

	}

	@Override
	public int save(User u) throws Exception {
		String sql = "INSERT INTO users(id, first_name, last_name, email, mobile, birth, password) VALUES(default, ?, ?, ?, ?, ?, ?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate = sdf.parse(u.getBirth());
		System.out.println(uDate);
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		System.out.println(sDate);
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirst_name());
			stmt.setString(2, u.getLast_name());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getMobile());
			stmt.setDate(5, sDate);
			stmt.setString(6, u.getPassword());
			int count = stmt.executeUpdate();
			return count;
		} // stmt.close();
	}

	@Override
	public int update(User u) throws Exception {
		String sql = "UPDATE users SET first_name=?, last_name=?, mobile=?, birth=? WHERE id=?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate = sdf.parse(u.getBirth());
		System.out.println(uDate);
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirst_name());
			stmt.setString(2, u.getLast_name());
			stmt.setString(3, u.getMobile());
			stmt.setDate(4, sDate);
			stmt.setInt(5, u.getId());
			int count = stmt.executeUpdate();
			return count;
		} // stmt.close();
	}

	@Override
	public int updatePassword(int userId, String newPassword) throws Exception {
		String sql = "UPDATE users SET password=? WHERE id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, newPassword);
			stmt.setInt(2, userId);
			int count = stmt.executeUpdate();
			return count;
		} // stmt.close();
	}

	@Override
	public User findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					email = rs.getString("email");
					String mobile = rs.getString("mobile");
					java.util.Date uDate = rs.getDate("birth");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println(uDate);
					java.sql.Date sDate = new java.sql.Date(uDate.getTime());
					String date = sdf.format(sDate);
					String password = rs.getString("password");
					return new User(id, fname, lname, email, password, mobile, date);
				}

			} // rs.close();
		} // stmt.close();
		return null;
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String email = rs.getString("email");
					String mobile = rs.getString("mobile");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date uDate = rs.getDate("birth");
					System.out.println(uDate);
					java.sql.Date sDate = new java.sql.Date(uDate.getTime());
					String date = sdf.format(sDate);
					String password = rs.getString("password");
					User u = new User(id, fname, lname, email, mobile, date, password);
					list.add(u);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}
}
