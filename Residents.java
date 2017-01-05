package hotel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    
    private final StringProperty email = new SimpleStringProperty(this, "email");
    public StringProperty emailNameProperty() {
        return email ;
    }
    public final String getEmail() {
        return emailNameProperty().get();
    }
    public final void setEmail(String email) {
    	emailNameProperty().set(email);
    }
    
    private final StringProperty customerId = new SimpleStringProperty(this, "email");
    public StringProperty customerIdNameProperty() {
        return customerId ;
    }
    public final String getCustomerId() {
        return customerIdNameProperty().get();
    }
    public final void setCustomerId(String customerId) {
    	customerIdNameProperty().set(customerId);
    }
    
    private final IntegerProperty roomID = new SimpleIntegerProperty(this, "Room_ID");
    public IntegerProperty roomIDNameProperty() {
        return roomID ;
    }
    public final int getRoomID() {
        return roomIDNameProperty().getValue();
    }
    public final void setRoomID(Integer roomID) {
    	roomIDNameProperty().set(roomID);
    }
    
    private final IntegerProperty citizenID = new SimpleIntegerProperty(this, "CitizenID");
    public IntegerProperty citizenIDNameProperty() {
        return citizenID ;
    }
    public final int getCitizenID() {
        return citizenIDNameProperty().getValue();
    }
    public final void setCitizenID(Integer citizenID) {
    	citizenIDNameProperty().set(citizenID);
    }
    
    
    private final IntegerProperty price = new SimpleIntegerProperty(this, "Price");
    public IntegerProperty priceNameProperty() {
        return price ;
    }
    public final int getPrice() {
        return priceNameProperty().get();
    }
    public final void setPrice(Integer Price) {
    	priceNameProperty().set(Price);
    }
    
    
    private final StringProperty status = new SimpleStringProperty(this, "status");
    public StringProperty statusNameProperty() {
        return status ;
    }
    public final String getStatus() {
        return statusNameProperty().get();
    }
    public final void setStatus(String status) {
    	statusNameProperty().set(status);
    }
    
    private final StringProperty roomCategory = new SimpleStringProperty(this, "Room_category");
    public StringProperty roomCategoryNameProperty() {
        return roomCategory ;
    }
    public final String getRoomCategory() {
        return roomCategoryNameProperty().get();
    }
    public final void setRoomCategory(String roomCategory) {
    	roomCategoryNameProperty().set(roomCategory);
    }
    
    private final StringProperty cleanStatus = new SimpleStringProperty(this, "CleanStatus");
    public StringProperty cleanStatusNameProperty() {
        return cleanStatus ;
    }
    public final String getCleanStatus() {
        return cleanStatusNameProperty().get();
    }
    public final void setCleanStatus(String cleanStatus) {
    	cleanStatusNameProperty().set(cleanStatus);
    }


    public Residents(Integer roomID, Integer price,String status,String roomCategory,String cleanStatus)
    {
    	setRoomID(roomID);
    	setPrice(price);
    	setStatus(status);
    	setRoomCategory(roomCategory);
    	setCleanStatus(cleanStatus);
    	
    }

    public Residents() {}

    public Residents(String firstName, String lastName,String email,String customerId,Integer roomID, Integer citizenID) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setCustomerId(customerId);
        setRoomID(roomID);
        setCitizenID(citizenID);
       
    }
    
}
