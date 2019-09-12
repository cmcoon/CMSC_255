import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Random;
import javafx.geometry.Orientation;

public class RandomPasswordGUI extends Application implements EventHandler<ActionEvent> {
	//String to be used for storing randomly generated password
	private String randomPassword = " ";
	
	//Button will be used for user to press when creating password
	Button createPassword;
	
	//Check boxes based on user selected parameters for password
	CheckBox box1;
	CheckBox box2;
	CheckBox box3;
	CheckBox box4;
	
	//Label to be used for password display
	Label createdPassword;
	
	//Slider used to select password length by user
	Slider slider1;
	
	@Override
	public void start(Stage primaryStage){		
		//Check boxes labeled with corresponding parameters
		box1 = new CheckBox("Uppercase Letters");
		box2 = new CheckBox("Lowercase Letters");
		box3 = new CheckBox("Numbers");
		box4 = new CheckBox("Misc. Characters");
		
		//Password label will be blank in final version and update with password after pushing create password button
		createdPassword = new Label("Random Password: " + randomPassword);
		
		//Button user presses this button to create password
		createPassword = new Button("Create Password");
		createPassword.setOnAction(this);
		
		//Label for space and formatting
		Label spaceLabel = new Label("  ");
		
		//Create label and slider for password length specification
		Label label1 = new Label("Password Length:");
		slider1 = new Slider(0, 10, 5);
		slider1.setShowTickMarks(true);
		slider1.setShowTickLabels(true);
		slider1.setMajorTickUnit(2);
		slider1.setBlockIncrement(2);
		slider1.setSnapToTicks(true);
		slider1.setMinorTickCount(1);
		
		//Object property HORIZTONAL of Orientation class makes slider horizontal
		slider1.setOrientation(Orientation.HORIZONTAL);
		
		//Create HBox for welcome message and instructions
		VBox vbox1 = new VBox();
		Label welcomeLabel = new Label("Welcome To The Random Password Generator");
		Label instructionsLabel = new Label("----------------------------------------------------");
		vbox1.getChildren().addAll(welcomeLabel, instructionsLabel);
		vbox1.setSpacing(5);
		
		//Create HBox or horizontal box for text input then add to VBOX
		HBox hbox2 = new HBox();
		hbox2.getChildren().addAll(label1, slider1);
		hbox2.setSpacing(5);
		
		//Layout VBOX = vertical box
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(vbox1, hbox2, box1, box2, box3, box4, createPassword, spaceLabel, createdPassword);
		
		//Add layout box to scene
		Scene scene = new Scene(layout, 347, 375);
		
		//primaryStage setup and execution (main window containing layout and scene)
		primaryStage.setTitle("Random Password Generator");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		//Only need action event for create password button
		if(event.getSource() == createPassword) {
			//Set text with new password and call createPassword() for heavy lifting
			createdPassword.setText("Random Password: " + createPassword());
		}	
	}
	
	public String createPassword() {
		//Must reset password to blank before creating a new one
		randomPassword = "";
		
		//Following strings will have characters randomly selected if user specifies they want specific categories of characters contained in strings
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numberToNine = "0123456789";
		String characterOptions = "!@#$%&*-_?";
		
		//New random object to be used for password creation
		Random random = new Random();
		
		//If user did not check any check boxes exit loop and output error message
		if(!box1.isSelected() && !box2.isSelected() && !box3.isSelected() && !box4.isSelected()) {
			return "Error: No Options Selected";
		}
		
		//Counter variable for loop and randomNumber for decisions
		int i = 0;
		int randomNumber = 0;
		
		//While loop will run until password of desired length is created
		while (i < slider1.getValue()){
			//Generate random number 0 - 3 inclusive for four possible password parameters
			randomNumber = random.nextInt(4);
			
			//Will add upper case letter to password if check box is selected
			if(randomNumber == 0 && box1.isSelected()){
				randomPassword += upperCase.charAt(random.nextInt(upperCase.length()-1));
				i++;
			}
			
			//Will add lower case letter to password if check box is selected
			if(randomNumber == 1 && box2.isSelected()){
				randomPassword += lowerCase.charAt(random.nextInt(lowerCase.length()-1));
				i++;
			}
			
			//Will add number to password if check box is selected
			if(randomNumber == 2 && box3.isSelected()){
				randomPassword += numberToNine.charAt(random.nextInt(numberToNine.length()-1));
				i++;
			}	
			
			//Will add miscellaneous character to password if check box is selected
			if(randomNumber == 3 && box4.isSelected()){
				randomPassword += characterOptions.charAt(random.nextInt(characterOptions.length()-1));
				i++;
			}	
		}
		
		//randomPassword = hasUpperCase + " " + hasLowerCase + " " + hasNumbers + " " + hasMiscCharacters + " " + passwordLength ;
		
		return randomPassword;
	}
}