package hotel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Residents {
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
    
    /*private final StringProperty email = new SimpleStringProperty(this, "email");
    public StringProperty emailNameProperty() {
        return email ;
    }
    public final String getEmail() {
        return emailNameProperty().get();
    }
    public final void setEmail(String email) {
    	emailNameProperty().set(email);
    }*/


    

    public Residents() {}

    public Residents(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
       // setEmail(email);
       
    }
}
