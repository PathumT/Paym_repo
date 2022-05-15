package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class payment 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_database", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertpayment(String code, String name, String price, String desc){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into payments (`paymentID`,`paymentCode`,`paymentName`,`paymentPrice`,`paymentDescription`)"+" values (?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, code); 
						preparedStmt.setString(3, name); 
						preparedStmt.setDouble(4, Double.parseDouble(price)); 
						preparedStmt.setString(5, desc); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newpayments = readpayments(); 
						output = "{\"status\":\"success\",\"data\":\""+newpayments+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readpayments() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr><th>payment Code</th>"
		 		+ "<th>payment Name</th><th>payment Price</th>"
		 		+ "<th>payment Description</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from payments"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String paymentID = Integer.toString(rs.getInt("paymentID")); 
		 String paymentCode = rs.getString("paymentCode"); 
		 String paymentName = rs.getString("paymentName"); 
		 String paymentPrice = Double.toString(rs.getDouble("paymentPrice")); 
		 String paymentDescription = rs.getString("paymentDescription"); 
		 // Add into the html table
		 output += "<tr><td><input id='hidpaymentIDUpdate' name='hidpaymentIDUpdate' type='hidden' value='"+paymentID+"'>"+paymentCode+"</td>"; 
		 output += "<td>" + paymentName + "</td>"; 
		 output += "<td>" + paymentPrice + "</td>"; 
		 output += "<td>" + paymentDescription + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-paymentid='" + paymentID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-paymentid='" + paymentID + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the payments."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updatepayment(String ID, String code, String name, String price, String desc){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE payments SET paymentCode=?,paymentName=?,paymentPrice=?,paymentDescription=? WHERE paymentID=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, code); 
							preparedStmt.setString(2, name); 
							preparedStmt.setDouble(3, Double.parseDouble(price)); 
							preparedStmt.setString(4, desc); 
							preparedStmt.setInt(5, Integer.parseInt(ID)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newpayments = readpayments(); 
							output = "{\"status\":\"success\",\"data\":\""+newpayments+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the payment.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deletepayment(String paymentID){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from payments where paymentID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newpayments = readpayments(); 
						 output = "{\"status\":\"success\",\"data\":\""+newpayments+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the payment.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
