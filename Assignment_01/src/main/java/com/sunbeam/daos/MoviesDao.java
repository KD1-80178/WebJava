package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunbeam.pojos.Movie;

public interface MoviesDao extends AutoCloseable
{
  public List<Movie>findAll() throws Exception;
  public Movie findById(int id) throws Exception;
  
}