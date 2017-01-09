package hotel;

import java.sql.Connection;
import java.sql.Date;
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
		     
		    
		   // System.out.println("Connected to database");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	    
	}
	
	void comboBoxFiller(ComboBox<String> roomID)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select Room_ID from room where status='Available';");
			final ObservableList<String> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				Integer id = rs.getInt("Room_ID");
				String ids = id.toString();
				System.out.println(ids);
				
				data.add(ids);
			}
			roomID.setItems(data);
			roomID = new ComboBox(data);
			
			st.close();
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
            int rs2 = stmnt.executeUpdate("update room set status='Unavailable' where Room_ID=" + roomID.getSelectionModel().getSelectedItem() + ";");		
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
                Date checkInDated = rs.getDate(1);
                Date checkOutDated = rs.getDate(2);
                
                String checkInDate = checkInDated.toString();
                String checkOutDate = checkOutDated.toString();
               // System.out.println("check in = "+checkInDate);
                // System.out.println("");
              //  System.out.println("check out = "+checkOutDate);
                int roomID = rs.getInt("Room_ID");
                int citizenID = rs.getInt("CitizenID");
                
                
                
             //   String roomID = roomIDtemp.toString();
                
                Residents resident = new Residents(firstName, lastName, email,customerId,roomID,citizenID,checkInDate,checkOutDate);
                residentsList.add(resident);
            }
            stmnt.close();
            return residentsList ;
        } 
    }
    
    
    public List<Staff> getStaff() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from staff;");
        	
        		
        ){
            List<Staff> staffList = new ArrayList<>();
            while (rs.next()) {
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("LastName");
                String position = rs.getString("Position");
                String idNumber = rs.getString("IDNumber");
                int staffID = rs.getInt("StaffID");
                int salary = rs.getInt("Salary");
                
                
                
             //   String roomID = roomIDtemp.toString();
                
                Staff staff = new Staff(firstName, lastName, position,idNumber,staffID,salary);
                staffList.add(staff);
            }
            stmnt.close();
            return staffList ;
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
                String roomCategory = rs.getString("Room_category");
                String cleanStatus = rs.getString("CleanStatus");

                
                Residents resident = new Residents(roomID, price, status,roomCategory,cleanStatus);
                residentsList.add(resident);
            }
            stmnt.close();
            return residentsList ;
        } 
    }
    
    
    void residentsIDCombo(ComboBox<String> residentsID)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select CitizenID from residents");
			final ObservableList<String> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				Integer id = rs.getInt("CitizenID");
				String ids = id.toString();
				//System.out.println(ids);
				
				data.add(ids);
			}
			residentsID.setItems(data);
			residentsID = new ComboBox(data);
			st.close();
			
			//roomID = new ComboBox(data);
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		
		}
		
		
		
	}
    
    void residentsIDComboInteger(ComboBox<Integer> residentsID)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select CitizenID from residents");
			final ObservableList<Integer> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				Integer id = rs.getInt("CitizenID");
				String ids = id.toString();
				//System.out.println(ids);
				
				data.add(id);
			}
			residentsID.setItems(data);
			residentsID = new ComboBox(data);
			st.close();
			
			//roomID = new ComboBox(data);
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		
		}
		
		
		
	}
    
    
    
    void usersCombo(ComboBox<String> users)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select UserName from users");
			final ObservableList<String> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				
				String userNames = rs.getString("UserName");
				data.add(userNames);
			}
			users.setItems(data);
			users = new ComboBox(data);
			
			st.close();
			//roomID = new ComboBox(data);
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		
		}
		
		
		
	}
    
     Boolean validation(String userName, String password)
    {
    	Boolean temp = false;
    	try
    	{
    		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select PasswordField from users where UserName='"+ userName + "';");
			rs.next();
			String dbPassword = rs.getString("PasswordField");
		//	System.out.println("password = " + password + "\ndbpassword = "+dbPassword);
			st.close();
			
			if(dbPassword.equals(password))
			{
				temp = true;
			}
			else
			{
				temp = false;
			}
			
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    	}
    	//System.out.println(temp);
    	
		return temp;
    	
    }
    
    
    void roomIdCombo(ComboBox<String> roomId)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select Room_ID from room");
			final ObservableList<String> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				Integer id = rs.getInt("Room_ID");
				String ids = id.toString();
				//System.out.println(ids);
				
				data.add(ids);
			}
			roomId.setItems(data);
			roomId = new ComboBox(data);
			
			st.close();
			//roomID = new ComboBox(data);
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		
		}
	}
    
    void roomIdComboInteger(ComboBox<Integer> roomId)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select Room_ID from room");
			final ObservableList<Integer> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				Integer id = rs.getInt("Room_ID");
				String ids = id.toString();
				//System.out.println(ids);
				
				data.add(id);
			}
			roomId.setItems(data);
			roomId = new ComboBox(data);
			
			st.close();
			//roomID = new ComboBox(data);
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		
		}
	}
    
    
    
    void markAsCleaned(ComboBox<String> roomId)
    {
    	try
    	{
    		Statement st = connection.createStatement();
    		int rs = st.executeUpdate("update room set CleanStatus='Cleaned' where Room_id=" + roomId.getSelectionModel().getSelectedItem() + ";");
    		st.close();
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    	}
    }
    
    void markAsNotCleaned(ComboBox<String> roomId)
    {
    	try
    	{
    		Statement st = connection.createStatement();
    		int rs = st.executeUpdate("update room set CleanStatus='Not Cleaned' where Room_id=" + roomId.getSelectionModel().getSelectedItem() + ";");
    		st.close();
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    	}
    }
    
    
    
    void deleteEntry(ComboBox<String> enterId)
    {
    	try
    	{
    		Statement st = connection.createStatement();
    		Statement st2 = connection.createStatement();
    		Statement st3 = connection.createStatement();
    		Statement st4 = connection.createStatement();
    		ResultSet rs3 = st.executeQuery("select Room_ID from residents where CitizenID=" + enterId.getSelectionModel().getSelectedItem() + ";");
			int rs = st2.executeUpdate("delete from residents where CitizenID=" + enterId.getSelectionModel().getSelectedItem() + ";");
			int rs2 = st3.executeUpdate("ALTER TABLE residents AUTO_INCREMENT=1;");
			
			rs3.next();
			
			
			Integer ID = rs3.getInt("Room_ID");
			String IDs = ID.toString();
		//	System.out.println(ID);
			int rs4 = st4.executeUpdate("update room set status='Available' where Room_ID=" + IDs + ";");
			//System.out.println(rs4);
			
			st.close();
			st2.close();
			st3.close();
			st4.close();
			
			
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    	}
    }
    
    
    void calculate(Integer residentsID,Integer roomId,TextField result)
    {
    	try
    	{
    		Statement st = connection.createStatement();
    		ResultSet rs = st.executeQuery("select datediff(Check_in_Date,Check_out_Date) from residents where CitizenID="+ residentsID + ";");
			System.out.println(rs.next());
			//rs.next();
			Integer days = rs.getInt(1);
			
			days = days * -1;
			System.out.println("days= "+days);
			
			Statement st2 = connection.createStatement();
			ResultSet rs2 = st2.executeQuery("select Price from room where Room_ID=" + roomId + ";");
			rs2.next();
			Integer priceI = rs2.getInt(1);
			System.out.println("Price = "+ priceI);
			Integer tempResult = days * priceI;
			String finalResult = tempResult.toString();
			result.setText(finalResult);
			st.close();
			st2.close();
			
			
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    	}
    }
    
    
    
    public List<Residents> search(String field,String fieldContent) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
        		ResultSet rs = stmnt.executeQuery("select * from residents where " + field + "='" + fieldContent + "';");
        	
        		
        ){
            List<Residents> residentsList = new ArrayList<>();
            while (rs.next()) {
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("LastName");
                String email = rs.getString("email");
                String customerId = rs.getString("CustomerId");
                Date checkInDated = rs.getDate(1);
                Date checkOutDated = rs.getDate(2);
                
                String checkInDate = checkInDated.toString();
                String checkOutDate = checkOutDated.toString();
               // System.out.println("check in = "+checkInDate);
                // System.out.println("");
              //  System.out.println("check out = "+checkOutDate);
                int roomID = rs.getInt("Room_ID");
                int citizenID = rs.getInt("CitizenID");
                
                
                
             //   String roomID = roomIDtemp.toString();
                
                Residents resident = new Residents(firstName, lastName, email,customerId,roomID,citizenID,checkInDate,checkOutDate);
                residentsList.add(resident);
            }
            stmnt.close();
            return residentsList ;
        } 
    }

    // other methods, eg. addPerson(...) etc
}
