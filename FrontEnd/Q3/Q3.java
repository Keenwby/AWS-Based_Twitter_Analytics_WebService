package com.Q3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class query2
 */
///@WebServlet("/q3")
public class Q3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Q3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID = request.getParameter("userid").toString();
		//System.out.println(ID);
		
		Connection conn = null;
		Statement st = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception ex){
			System.out.println("Driver error!\n");
		}

		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=root");
			//System.out.println(conn == null);
		}catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		try{
			st = conn.createStatement();
			
			String query = "SELECT return_value FROM q3 WHERE user_id=\""+ID+"\";";
			//System.out.println(query);
			
			ResultSet rs = st.executeQuery(query);
			response.setContentType("text/plain");
            response.setContentType("charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
			
			PrintWriter pr = new PrintWriter(response.getOutputStream());
			pr.println("Uncle_cat,2538-7447-2180,3207-1716-8663,4769-4709-3812");
			
			while (rs.next()){
				String return_value = rs.getString("return_value");
				return_value = return_value.replaceAll(":", "\n");
				pr.print(return_value);
			}
			pr.flush();
			rs.close();
            st.close();
            conn.close();
			
		}catch(SQLException ex){
			// handle any errors
		    System.out.println("SQLException 222: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}finally{}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
