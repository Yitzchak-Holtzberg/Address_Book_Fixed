package AddressBook;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    Button addButton = new Button("Add");
    Button firstButton = new Button("First");
    Button nextButton = new Button("Next");
    Button previousButton = new Button("Previous");
    Button lastButton = new Button("Last");
    Button updateButton = new Button("Update");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Address Book");
        nameLabel.setPadding(new Insets(10));
        streetLabel.setPadding(new Insets(10));
        City.setPadding(new Insets(10));
        State.setPadding(new Insets(10));
        Zip.setPadding(new Insets(10));

        nameTextField.setPrefWidth(200);
        streetTextField.setPrefWidth(200);
        cityTextField.setPrefWidth(70);
        stateTextField.setPrefWidth(50);
        zipTextField.setPrefWidth(50  );
        nameAndNameLabel.setPadding(new Insets(10));
        streetAndStreetLabel.setPadding(new Insets(10));
        cityStateZipWithLabel.setPadding(new Insets(10));
        Buttons.setPadding(new Insets(10));

        addButton();
        setNextButton();
        setPreviousButton();

        nameAndNameLabel.getChildren().addAll(nameLabel, nameTextField);
        streetAndStreetLabel.getChildren().addAll(streetLabel, streetTextField);
        cityStateZipWithLabel.getChildren().addAll(City, cityTextField, State, stateTextField, Zip, zipTextField);
        Buttons.getChildren().addAll(addButton, firstButton, nextButton, previousButton, lastButton, updateButton);
        root.getChildren().addAll(nameAndNameLabel, streetAndStreetLabel, cityStateZipWithLabel, Buttons);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    private void addButton() {
        addButton.setOnAction(e -> {
            Contact contact = new Contact(nameTextField.getText(), streetTextField.getText(), cityTextField.getText(), stateTextField.getText(), zipTextField.getText());
            ContactManager.addContact(contact);
        });
    }

    private void setNextButton(){
        nextButton.setOnAction(e -> {
            Contact contact;
            try {
                contact = ContactManager.getNextContact();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            nameTextField.setText(contact.getName());
            streetTextField.setText(contact.getStreet());
            cityTextField.setText(contact.getCity());
            stateTextField.setText(contact.getState());
            zipTextField.setText(contact.getZip());
        });
    }

    private void setPreviousButton(){
        previousButton.setOnAction(e -> {
            Contact contact;
            contact = ContactManager.getPreviousContact();
            nameTextField.setText(contact.getName());
            streetTextField.setText(contact.getStreet());
            cityTextField.setText(contact.getCity());
            stateTextField.setText(contact.getState());
            zipTextField.setText(contact.getZip());
        });
    }

    private void setFirstButton(){
        firstButton.setOnAction(e -> {
            Contact contact;
            contact = ContactManager.getContact(0);
            nameTextField.setText(contact.getName());
            streetTextField.setText(contact.getStreet());
            cityTextField.setText(contact.getCity());
            stateTextField.setText(contact.getState());
            zipTextField.setText(contact.getZip());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}