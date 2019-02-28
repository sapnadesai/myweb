package com.lti.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class CustomerDao {//data access object

	//public void addCustomer(int id, String name,String email)//lenghty process
	public void addCustomer(Customer customer)
	{
		Connection con = null;
		PreparedStatement stmt=null;
		try {
			Properties dbprops=new Properties();
			dbprops.load(this.getClass().getClassLoader().getResourceAsStream("dev-db.properties"));
		     Class.forName(dbprops.getProperty("driverClassName"));
		     con = DriverManager.getConnection(dbprops.getProperty("url"),dbprops.getProperty("username"), dbprops.getProperty("password"));
		
		     String sql="insert into Customer values(?,?,?)";
		     stmt=con.prepareStatement(sql);
	
			 stmt.setInt(1, customer.getCustid());
			 stmt.setString(2, customer.getCname());
			 stmt.setString(3, customer.getEmail());
			 
			 stmt.executeUpdate();
				System.out.println("record inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {stmt.close();}catch (Exception e) {}
				try {con.close();}catch (Exception e) { }				
		}		
	}
}
