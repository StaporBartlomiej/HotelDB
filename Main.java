package hotel;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	private ResidentsDataAccessor dataAccessor ;
	
	
	private TableView<Residents> table = new TableView<Residents>();
	
	
	public void forbidNonIntInput(TextField sth) // forces user to input numbers, not Strings
	{
		try
		{
			sth.setTextFormatter( new TextFormatter<>(c ->
			{
			    if ( c.getControlNewText().isEmpty() )
			    {
			        return c;
			    }
			    DecimalFormat format = new DecimalFormat( "#.00" ); 
			    ParsePosition parsePosition = new ParsePosition( 0 );
			    Object object = format.parse( c.getControlNewText(), parsePosition );

			    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
			    {
			        return null;
			    }
			    else
			    {
			        return c;
			    }
			}));
			 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
				
			
		}
	}
	
	/*private final ObservableList<Person> data = FXCollections.observableArrayList(
			new Person("Euro","EUR",findRate("EUR","EUR")),
			new Person("US Dollar","USD",findRate("USD","EUR")),
			new Person("British Pound","GBP",findRate("GBP","EUR")),
			new Person("Canadian Dollar","CAD",findRate("CAD","EUR")),
			new Person("Australian Dollar","AUD",findRate("AUD","EUR")),
			new Person("Japanese Yen","JPY",findRate("JPY","EUR"))
			);

*/
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Hotel Management System");
			BorderPane border = new BorderPane();
			GridPane grid = new GridPane();
			border.setTop(grid);
			table.setEditable(false);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(0,10,0,10));
			
		//	ComboBox<String> fromCurrency = new ComboBox(items);
		//	ComboBox<String> toCurrency = new ComboBox(items);
			

			Button addReservation = new Button("Add Reservation");
			grid.add(addReservation, 0, 0);
			
			Button Residents = new Button("Residents"); // wyswietla liste rezydentow czyli osob aktualnie wynajmujacych pokoj w hotelu
			grid.add(Residents, 1, 0);
			
			Button freeRooms = new Button("Check free rooms"); // bedzie wyswietlac liste wolnych pokoi( Status == Available, w DB 'ROOM')
			grid.add(freeRooms, 2, 0);
			
			Button staff = new Button("Display Staff");
			grid.add(staff, 3, 0);
			
			
			
			addReservation.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
				{
					Stage stage = new Stage();
					stage.setTitle("Add reservation");
					BorderPane reservationBorder = new BorderPane();
					GridPane reservationGrid = new GridPane();
					reservationGrid.setHgap(10);
					reservationGrid.setVgap(10);
					//reservationGrid.setPadding(0,10,0,10);
					reservationBorder.setCenter(reservationGrid);
					
					Label fName = new Label("First Name:");
					reservationGrid.add(fName, 0, 0);
					
					TextField firstName = new TextField();
					reservationGrid.add(firstName, 1, 0);
					
					Label lName = new Label("Last Name:");
					reservationGrid.add(lName, 0, 1);
					
					TextField lastName = new TextField();
					reservationGrid.add(lastName, 1, 1);
					
					Label cInDate = new Label("Check in Date:");
					reservationGrid.add(cInDate, 0, 2);
					
					DatePicker checkInDate = new DatePicker();
					checkInDate.setOnAction(event -> {
					    LocalDate InDate = checkInDate.getValue();
					    System.out.println("Selected date: " + InDate);
					});
					reservationGrid.add(checkInDate, 1, 2);
					
					Label cOutDate = new Label("Check out Date:");
					reservationGrid.add(cOutDate, 0, 3);
					
					DatePicker checkOutDate = new DatePicker();
					checkOutDate.setOnAction(event -> {
						LocalDate OutDate = checkOutDate.getValue();
						System.out.println("Selected date: " + OutDate);
					});
					reservationGrid.add(checkOutDate, 1, 3);
					
					Label LabelEmail = new Label("E-mail:");
					reservationGrid.add(LabelEmail, 0, 4);
					
					TextField email = new TextField();
					reservationGrid.add(email, 1, 4);
					
					Label idLabel = new Label("ID:");
					reservationGrid.add(idLabel, 0, 5);
					
					TextField customerId = new TextField();
					reservationGrid.add(customerId, 1, 5);
					
					Label roomIdLabel = new Label("Room ID:");
					reservationGrid.add(roomIdLabel, 0, 6);
					
					ResidentsDataAccessor comboBoxfillering = new ResidentsDataAccessor("root","wiater94");
					ComboBox<Integer> roomID = new ComboBox<Integer>();
					comboBoxfillering.comboBoxFiller(roomID);
					reservationGrid.add(roomID, 1, 6);
					//forbidNonIntInput(roomID);
					
				//	Label reservationIdLabel = new Label("Reservation ID:");
				//	reservationGrid.add(reservationIdLabel, 0, 7);
					
				//	TextField reservationID = new TextField();
				//	reservationGrid.add(reservationID, 1, 7);
				//	forbidNonIntInput(reservationID);
					//reservationID.getText();
					
					Button submit = new Button("Submit");
					reservationGrid.add(submit, 0, 8);
					
					submit.setOnAction(new EventHandler<ActionEvent>() { //push data into DB, will be filled
						@Override public void handle(ActionEvent e)
						{
							ResidentsDataAccessor dataAccessorPush = new ResidentsDataAccessor("root","wiater94");
							dataAccessorPush.pushDataIntoTable( customerId,roomID,  checkInDate,  checkOutDate,  firstName,  lastName,email);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText(null);
							alert.setContentText("Record added successfully.");
							alert.showAndWait();
							stage.close();
							
						}
					});
					Button cancel = new Button("Cancel");
					reservationGrid.add(cancel, 1, 8);
					
					cancel.setOnAction(new EventHandler<ActionEvent>() { //
						@Override public void handle(ActionEvent e)
						{
							stage.close();
						}
					});
					
					Scene scene = new Scene(reservationBorder,600,375);
					stage.setScene(scene);
					stage.show();
				}
			});
			
			Residents.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
				{
					Stage stage = new Stage();
					stage.setTitle("Residents");
					BorderPane residentsBorder = new BorderPane();
					GridPane residentsGrid = new GridPane();
					residentsGrid.setHgap(10);
					residentsGrid.setVgap(10);
					//reservationGrid.setPadding(0,10,0,10);
					//residentsBorder.setCenter(residentsGrid);
					
					
					
					
					dataAccessor = new ResidentsDataAccessor("root","wiater94"); // provide driverName, dbURL, user, password...

			        TableView<Residents> residentTable = new TableView<>();
			        TableColumn<Residents, String> firstNameCol = new TableColumn<>("First Name");
			        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FName"));
			        TableColumn<Residents, String> lastNameCol = new TableColumn<>("Last Name");
			        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
			        TableColumn<Residents, String> emailCol = new TableColumn<>("email");
			        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
			        TableColumn<Residents, String> customerIdCol = new TableColumn<>("Customer ID");
			        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
			        TableColumn<Residents, Integer> RoomIDCol = new TableColumn<Residents,Integer>("Room ID");
			        RoomIDCol.setCellValueFactory(cellData -> cellData.getValue().roomIDNameProperty().asObject());
			        TableColumn<Residents, Integer> citizenIDCol = new TableColumn<Residents,Integer>("Reseident ID");
			        citizenIDCol.setCellValueFactory(cellData -> cellData.getValue().citizenIDNameProperty().asObject());
			        

			        residentTable.getColumns().addAll(firstNameCol, lastNameCol,emailCol,customerIdCol,RoomIDCol,citizenIDCol);

			        try
			        {
			        	  residentTable.getItems().addAll(dataAccessor.getResidents());
			        }
			        catch(SQLException ex)
			        {
			        	ex.printStackTrace();
			        }
			        residentTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
					//residentsGrid.add(residentTable, 0, 0);
					residentsBorder.setCenter(residentTable);
					
					
					
					Scene scene = new Scene(residentsBorder,600,375);
					stage.setScene(scene);
					stage.show();
				}
			});
			
			freeRooms.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
				{
					Stage stage = new Stage();
					stage.setTitle("Free Rooms");
					BorderPane freeRoomsBorder = new BorderPane();
					GridPane freeRoomsGrid = new GridPane();
					freeRoomsGrid.setHgap(10);
					freeRoomsGrid.setVgap(10);
					//reservationGrid.setPadding(0,10,0,10);
					freeRoomsBorder.setCenter(freeRoomsGrid);
					
					dataAccessor = new ResidentsDataAccessor("root","wiater94"); // provide driverName, dbURL, user, password...

			        TableView<Residents> freeRoomsTable = new TableView<>();
			        TableColumn<Residents, Integer> RoomIDCol = new TableColumn<Residents,Integer>("Room ID");
			        RoomIDCol.setCellValueFactory(cellData -> cellData.getValue().roomIDNameProperty().asObject());
			        TableColumn<Residents, Integer> Price = new TableColumn<>("Price");
			        Price.setCellValueFactory(cellData -> cellData.getValue().priceNameProperty().asObject());
			        TableColumn<Residents, String> status = new TableColumn<>("status");
			        status.setCellValueFactory(new PropertyValueFactory<>("status"));
			        TableColumn<Residents, String> roomCategory = new TableColumn<>("Room Category");
			        roomCategory.setCellValueFactory(new PropertyValueFactory<>("Room_category"));
			        TableColumn<Residents, String> cleanStatus = new TableColumn<>("Clean Status");
			        cleanStatus.setCellValueFactory(new PropertyValueFactory<>("CleanStatus"));
			        
			        
			        
			        

			        freeRoomsTable.getColumns().addAll(RoomIDCol, Price,status,roomCategory,cleanStatus);

			        try
			        {
			        	freeRoomsTable.getItems().addAll(dataAccessor.getFreeRooms());
			        }
			        catch(SQLException ex)
			        {
			        	ex.printStackTrace();
			        }
			        freeRoomsTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
					//residentsGrid.add(residentTable, 0, 0);
			        freeRoomsBorder.setCenter(freeRoomsTable);
					
					
					
					
					Scene scene = new Scene(freeRoomsBorder,600,375);
					stage.setScene(scene);
					stage.show();
				}
			});
			
			staff.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
				{
					Stage stage = new Stage();
					stage.setTitle("Staff");
					BorderPane reservationBorder = new BorderPane();
					GridPane reservationGrid = new GridPane();
					reservationGrid.setHgap(10);
					reservationGrid.setVgap(10);
					//reservationGrid.setPadding(0,10,0,10);
					reservationBorder.setCenter(reservationGrid);
					Scene scene = new Scene(reservationBorder,600,375);
					stage.setScene(scene);
					stage.show();
				}
			});
			
			
			
			
			

			
			
			

			
		/*	Label courseLabel = new Label("Course");
			courseLabel.setFont(new Font("Times New Roman",20));
			courseLabel.setTextFill(Color.CHARTREUSE);
			
			TableColumn currencyName = new TableColumn("Currency Name");
			currencyName.setMinWidth(100);
			currencyName.setCellValueFactory(new PropertyValueFactory<Person,String>("cName"));
			
			TableColumn symbol = new TableColumn("Symbol");
			symbol.setMinWidth(100);
			symbol.setCellValueFactory(new PropertyValueFactory<Person,String>("sm"));
			
			TableColumn course = new TableColumn("Units per EUR");
			course.setMinWidth(100);
			course.setCellValueFactory(new PropertyValueFactory<Person,String>("cour"));

			//table.setItems(data);
			
			table.getColumns().addAll(currencyName,symbol,course);
			
			grid2.add(table, 0, 0);
			border.setCenter(table);
			*/
			Scene scene = new Scene(border,800,475);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

	
}
