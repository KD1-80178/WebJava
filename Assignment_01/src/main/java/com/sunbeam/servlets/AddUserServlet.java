package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

@WebServlet("/register")
public class AddUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user=new User();
		user.setFirst_name(req.getParameter("fname"));
		user.setLast_name(req.getParameter("lname"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("passwd"));
		user.setMobile(req.getParameter("mobileno"));
		user.setBirth(req.getParameter("dob"));
	
		System.out.println(user);
		
		try(UserDao userDao=new UserDaoImpl()){
			int count=userDao.save(user);
			
			if(count==1)
			{
				resp.setContentType("text/html");
				PrintWriter out=resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Signup</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h5>Added Successful</h5>");
				out.println("<a href='index.html'>Login</a>");
				out.println("</body>");
				out.println("</html>");
			}
			else
			{
				
			}
		}catch (Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
