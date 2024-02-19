package AddressBook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddressBookGUI extends Application {
    VBox root = new VBox();
    HBox nameAndNameLabel = new HBox();
    HBox streetAndStreetLabel = new HBox();
    HBox cityStateZipWithLabel = new HBox();
    HBox Buttons = new HBox();

    Label nameLabel = new Label("Name");
    Label streetLabel = new Label("Street");
    Label City = new Label("City");
    Label State = new Label("State");
    Label Zip = new Label("Zip");

    TextField nameTextField = new TextField();
    TextField streetTextField = new TextField();
    TextField cityTextField = new TextField();
    TextField stateTextField = new TextField();
    TextField zipTextField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        nameAndNameLabel.getChildren().addAll(nameLabel, nameTextField);
        streetAndStreetLabel.getChildren().addAll(streetLabel, streetTextField);
        cityStateZipWithLabel.getChildren().addAll(City, cityTextField, State, stateTextField, Zip, zipTextField);
        root.getChildren().addAll(nameAndNameLabel, streetAndStreetLabel, cityStateZipWithLabel, Buttons);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}