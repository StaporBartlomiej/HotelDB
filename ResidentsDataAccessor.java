package hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ResidentsDataAccessor {
	  // in real life, use a connection pool....
	Connection connection = null;
	ResidentsDataAccessor(String user, String password)
	{
		try
		{
		//	Properties connectionProps = new Properties();
		  //  connectionProps.put("user", user);
		   // connectionProps.put("password", password);
			Class.forName("com.mysql.jdbc.Driver"); 
		    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb",user,password);
		     
		    
		    System.out.println("Connected to database");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	    
	}
	
	
	void pushDataIntoTable(TextField reservationID, TextField customerId, TextField roomID, DatePicker checkInDate, DatePicker checkOutDate, TextField firstName, TextField lastName,TextField email)
	{
		try
		{
			
			
			Statement stmnt = connection.createStatement();
			/*System.out.println("insert into residents"
            		+ "(Reservation_id,CustomerId,Room_ID,Check_in_Date,Check_out_Date,FName,LastName)"
            		+ "values(" + reservationID.getText() + ",'" + customerId.getText() + "'," + roomID.getText() + ",'" + checkInDate.getValue() + "','" + checkOutDate.getValue() 
            		+ "','" + firstName.getText() + "','" + lastName.getText() + "');");*/
            int rs = stmnt.executeUpdate("insert into residents"
            		+ "(Reservation_id,CustomerId,Room_ID,Check_in_Date,Check_out_Date,FName,LastName,email)"
            		+ "values(" + reservationID.getText() + ",'" + customerId.getText() + "'," + roomID.getText() + ",'" + checkInDate.getValue() + "','" + checkOutDate.getValue() 
            		+ "','" + firstName.getText() + "','" + lastName.getText() + "','" + email.getText() + "');");
            		
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Residents> getResidents() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from residents");
        ){
            List<Residents> residentsList = new ArrayList<>();
            while (rs.next()) {
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("LastName");
                String email = rs.getString("email");
                String customerId = rs.getString("CustomerId");
                int roomID = rs.getInt("Room_ID");
             //   String roomID = roomIDtemp.toString();
                
                Residents resident = new Residents(firstName, lastName, email,customerId,roomID);
                residentsList.add(resident);
            }
            return residentsList ;
        } 
    }

    // other methods, eg. addPerson(...) etc
}
