package studentInfosManagement;

import java.sql.Connection; // have to be closed explicitly
import java.sql.DriverManager; // does connection to database, codes that convert it
import java.sql.ResultSet; // get result from the query
import java.sql.SQLException; // throws error
import java.sql.Statement; // have to be closed explicitly, send to sql server
import java.util.ArrayList;
import java.util.List;

// this class recieve and 

public class MySQLConnector {
	
	String url = "jdbc:mysql://localhost:3306/university";
	String uname = ""; //user name here
	String password = ""; //password here
	
	MySQLConnector() {
		
	}
	
	//use for updating, modifying the tables
	void request(String command, boolean[] error) {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement statement = con.createStatement(); 
						
			int result = statement.executeUpdate(command);
			
			 if (result > 0) 
	                System.out.println("successfully inserted"); 
	  
	            else
	                System.out.println( 
	                    "unsucessful insertion "); 
	
		}
		catch (SQLException e) {
			e.printStackTrace();
			error[0] = true;
		}
	}
	
	//use for reading datas, pass in 2d array and query command
	void query(Object[][] data, String query) {
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				data[i][j] = "";
			}
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
	
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement statement = con.createStatement(); 
			ResultSet result = statement.executeQuery(query);
			
			int size = 0; 
			while(result.next()) {
				 String UniversityData = "";
				 for (int i = 1; i <= 6; i++) {
					 UniversityData += result.getString(i) + ":"; 
					 data[size][i - 1] = result.getString(i);
				 }
				 System.out.println(UniversityData);
				 size++;
			}

			System.out.println(size);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

