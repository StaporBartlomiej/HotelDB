package hotel;

import java.text.DecimalFormat;
import java.text.ParsePosition;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	
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
	public void start(Stage stagee) {
		GraphicInterface guy = new GraphicInterface();
		ResidentsDataAccessor dataAccesor = new ResidentsDataAccessor("root","admin1234");
		stagee.setTitle("Login");
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		
		border.setCenter(grid);
		border.setId("welcome");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		border.setCenter(grid);
		
		Label chooseUserL = new Label("Choose User");
		chooseUserL.setTextFill(Color.web("#ffffff"));
		grid.add(chooseUserL, 0, 0);
		
		ComboBox chooseUser = new ComboBox();
		dataAccesor.usersCombo(chooseUser);
		grid.add(chooseUser, 1, 0);
		
		Label enterPassL = new Label("Enter Password:");
		enterPassL.setTextFill(Color.web("#ffffff"));
		grid.add(enterPassL, 0, 1);
		
		PasswordField enterPass = new PasswordField();
		enterPass.setPromptText("Your Password");
		grid.add(enterPass, 1, 1);
		
		
		Button login = new Button("Login");
		grid.add(login, 0, 2);
		
		Button exit = new Button("Exit");
		grid.add(exit, 1, 2);
		GraphicInterface guio = new GraphicInterface();
		
		Scene scene = new Scene(border,520,342);
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		stagee.setScene(scene);
		stagee.show();
		
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
			{
				stagee.close();
				
				
				
			}
		});
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
			{
				String userName = chooseUser.getSelectionModel().getSelectedItem().toString();
				String password = enterPass.getText();
				System.out.println(dataAccesor.validation(userName, password));
				if(dataAccesor.validation(userName, password) == true)
				{
					
					Stage guiStage = new Stage();
					guy.gUI(guiStage);
					stagee.close();
					
				}
				else
				{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText(null);
					alert.setContentText("Wrong Password!");
					alert.showAndWait();
				}
				
				
				
				
			}
		});
		
		
	}
	

	
}
