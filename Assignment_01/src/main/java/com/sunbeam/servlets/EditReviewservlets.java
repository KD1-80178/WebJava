package com.sunbeam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.ReviewDao;
import com.sunbeam.daos.ReviewDaoImpl;
import com.sunbeam.pojos.Review;

public class EditReviewservlets extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String  
		
		try(ReviewDao revDao=new ReviewDaoImpl()){
			Review r = revDao.findById(0);
		} catch (Exception e) {
			e.printStackTrace();
			new ServletException(e);
		}
	}

}
