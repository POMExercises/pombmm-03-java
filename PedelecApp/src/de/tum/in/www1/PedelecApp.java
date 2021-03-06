package de.tum.in.www1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import de.tum.in.www1.model.Reservation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PedelecApp extends Application {
	
	private String title = "PedelecApp";

	@Override
    public void start(Stage primaryStage) {
		
		final VBox vboxLayout = new VBox(20);
		vboxLayout.setAlignment(Pos.CENTER);
		final Scene scene = new Scene(vboxLayout, 600, 500);
		
		final ImageView imageView = new ImageView();
		imageView.setImage(new Image(getClass().getResourceAsStream("Pedelec.png")));
		final Text pedelecNametext = new Text("Pedelec Magma 12");
		final Text chooseText = new Text("Choose Starting Time:");
		final DatePicker datePicker = new DatePicker();
		final TextField timeTextField = new TextField("2:00 pm");
		timeTextField.setMaxWidth(100);
		final Button reserveButton = new Button("Reserve"); 
		
		vboxLayout.getChildren().add(imageView);
		vboxLayout.getChildren().add(pedelecNametext);
		vboxLayout.getChildren().add(chooseText);
		vboxLayout.getChildren().add(datePicker);
		vboxLayout.getChildren().add(timeTextField);
		vboxLayout.getChildren().add(reserveButton);
		
		primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
		
		reserveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				System.out.println("Reserve Button clicked");
				   
				final LocalDate startDate = datePicker.getValue();
				if (startDate != null) {
					final String time = timeTextField.getText();
				    final String bikeName = pedelecNametext.getText();
				    final String startDateString = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
				    final String message = "Please confirm your reservation of " + bikeName + " at " +
				    		startDateString + " " + time;
				    final Alert alert = new Alert(AlertType.CONFIRMATION, message);
				    ButtonType result = alert.showAndWait().get();
				    
				if (result == ButtonType.OK) {
					Reservation reservation = new Reservation();
				     reservation.setBike(bikeName);
                     reservation.setStartDate(startDate);
                     reservation.setStartTime(timeTextField.getText().concat(""));
                     reservation.save();
				}
				else{
					System.out.println("Reservation confirmed");
				}	
			 } 
		}
		});
    }

	public static void main(String[] args) {
        launch(args);
    }
}
