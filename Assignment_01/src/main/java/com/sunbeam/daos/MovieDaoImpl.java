package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunbeam.pojos.Movie;

public class MovieDaoImpl extends Dao implements MoviesDao
{

	public MovieDaoImpl() throws Exception {
	}

	@Override
	public List<Movie> findAll() throws Exception {
		
		List<Movie> list = new ArrayList<>();
		String sql = "SELECT * FROM movies";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					java.sql.Date date= rs.getDate("releasedate");
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
					String sdate=sdf.format(date);
					Movie m = new Movie(id, title, sdate);
					list.add(m);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}

	@Override
	public Movie findById(int id) throws Exception 
	{
		String sql = "SELECT * FROM movies WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					id = rs.getInt("id");
					String title = rs.getString("title");
					java.sql.Date date= rs.getDate("releasedate");
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
					String sdate=sdf.format(date);
					Movie m = new Movie(id, title, sdate);
					return m;
				}		
			} // rs.close();
		} // stmt.close();
		
		return null;
	}
}	

