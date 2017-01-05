package hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
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
	
	void comboBoxFiller(ComboBox<Integer> roomID)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select Room_ID from room where status='Available';");
			final ObservableList<Integer> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				Integer id = rs.getInt("Room_ID");
				System.out.println(id);
				data.add(id);
			}
			roomID = new ComboBox(data);
			//roomID = new ComboBox(data);
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		
		}
		
		
		
	}
	
	
	void pushDataIntoTable( TextField customerId, ComboBox roomID, DatePicker checkInDate, DatePicker checkOutDate, TextField firstName, TextField lastName,TextField email)
	{
		try
		{
			
			
			Statement stmnt = connection.createStatement();
		/*	System.out.println("insert into residents"
            		+ "(CustomerId,Room_ID,Check_in_Date,Check_out_Date,FName,LastName,email)"
            		+ "values('" + customerId.getText() + "'," + roomID.getText() + ",'" + checkInDate.getValue() + "','" + checkOutDate.getValue() 
            		+ "','" + firstName.getText() + "','" + lastName.getText() + "','" + email.getText() + "');"); */
			
			
			
            int rs = stmnt.executeUpdate("insert into residents"
            		+ "(CustomerId,Room_ID,Check_in_Date,Check_out_Date,FName,LastName,email)"
            		+ "values('" + customerId.getText() + "'," + roomID.getSelectionModel().getSelectedItem() + ",'" + checkInDate.getValue() + "','" + checkOutDate.getValue() 
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
            ResultSet rs = stmnt.executeQuery("select * from residents;");
        ){
            List<Residents> residentsList = new ArrayList<>();
            while (rs.next()) {
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("LastName");
                String email = rs.getString("email");
                String customerId = rs.getString("CustomerId");
                int roomID = rs.getInt("Room_ID");
                int citizenID = rs.getInt("CitizenID");
             //   String roomID = roomIDtemp.toString();
                
                Residents resident = new Residents(firstName, lastName, email,customerId,roomID,citizenID);
                residentsList.add(resident);
            }
            return residentsList ;
        } 
    }
    
    
    
    public List<Residents> getFreeRooms() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from room where status='Available';");
        ){
            List<Residents> residentsList = new ArrayList<>();
            while (rs.next()) {
                Integer roomID = rs.getInt("Room_ID");
                Integer price = rs.getInt("Price");
                String status = rs.getString("status");
                String roomCategory = rs.getString("Room_Category");
                String cleanStatus = rs.getString("CleanStatus");

                
                Residents resident = new Residents(roomID, price, status,roomCategory,cleanStatus);
                residentsList.add(resident);
            }
            return residentsList ;
        } 
    }

    // other methods, eg. addPerson(...) etc
}
