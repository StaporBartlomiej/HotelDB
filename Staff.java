package hotel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Staff {
	private final StringProperty FName = new SimpleStringProperty(this, "FName");
    public StringProperty FNameProperty() {
        return FName ;
    }
    public final String getFirstName() {
        return FNameProperty().get();
    }
    public final void setFirstName(String firstName) {
    	FNameProperty().set(firstName);
    }

    private final StringProperty LastName = new SimpleStringProperty(this, "LastName");
    public StringProperty LastNameProperty() {
        return LastName ;
    }
    public final String getLastName() {
        return LastNameProperty().get();
    }
    public final void setLastName(String lastName) {
    	LastNameProperty().set(lastName);
    }
    
    private final StringProperty position = new SimpleStringProperty(this, "Position");
    public StringProperty positionProperty() {
        return position ;
    }
    public final String getPosition() {
        return positionProperty().get();
    }
    public final void setPosition(String position) {
    	positionProperty().set(position);
    }
    
    private final StringProperty idNumber = new SimpleStringProperty(this, "IDNumber");
    public StringProperty idNumberNameProperty() {
        return idNumber ;
    }
    public final String getIdNumber() {
        return idNumberNameProperty().get();
    }
    public final void setIdNumber(String idNumber) {
    	idNumberNameProperty().set(idNumber);
    }
    
    private final IntegerProperty staffID = new SimpleIntegerProperty(this, "StaffID");
    public IntegerProperty staffIDProperty() {
        return staffID ;
    }
    public final int getStaffID() {
        return staffIDProperty().getValue();
    }
    public final void setStaffId(Integer staffID) {
    	staffIDProperty().set(staffID);
    }
    
    private final IntegerProperty salary = new SimpleIntegerProperty(this, "Salary");
    public IntegerProperty salaryProperty() {
        return salary ;
    }
    public final int getSalary() {
        return salaryProperty().getValue();
    }
    public final void setSalary(Integer salary) {
    	salaryProperty().set(salary);
    }
    
    
    public Staff() {}
    
    public Staff(String FName, String LastName,String Position,String IDNumber,Integer StaffID,Integer salary)
    {
    	setFirstName(FName);
    	setLastName(LastName);
    	setPosition(Position);
    	setIdNumber(IDNumber);
    	setStaffId(StaffID);
    	setSalary(salary);
    }

}
