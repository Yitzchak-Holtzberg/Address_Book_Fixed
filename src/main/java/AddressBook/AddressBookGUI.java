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
import javafx.scene.control.Alert;

import java.io.IOException;

import static AddressBook.ContactManager.*;

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

    Button newButton = new Button("New");

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
        zipTextField.setPrefWidth(50);
        nameAndNameLabel.setPadding(new Insets(10));
        streetAndStreetLabel.setPadding(new Insets(10));
        cityStateZipWithLabel.setPadding(new Insets(10));
        Buttons.setPadding(new Insets(10));

        setNewButton();
        setAddButton();
        setNextButton();
        setPreviousButton();
        setFirstButton();
        setLastButton();
        setUpdateButton();

        nameAndNameLabel.getChildren().addAll(nameLabel, nameTextField);
        streetAndStreetLabel.getChildren().addAll(streetLabel, streetTextField);
        cityStateZipWithLabel.getChildren().addAll(City, cityTextField, State, stateTextField, Zip, zipTextField);
        Buttons.getChildren().addAll(newButton, addButton, firstButton, nextButton, previousButton, lastButton, updateButton);
        root.getChildren().addAll(nameAndNameLabel, streetAndStreetLabel, cityStateZipWithLabel, Buttons);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    /**
     * This method sets the action for the "New" button.
     * When the button is clicked, the text fields are cleared and a blank contact page is shown.
     */
    private void setNewButton() {
        newButton.setOnAction(event -> {
           clearFields();
            try {
                newContactLocation();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


    /**
     * This method sets the action for the "Update" button.
     * When the button is clicked, the current contact is updated with the text from the text fields.
     */
    private void setUpdateButton() {
        updateButton.setOnAction(e -> {
            try {
                updateContact(new Contact(nameTextField.getText(), streetTextField.getText(), cityTextField.getText(), stateTextField.getText(), zipTextField.getText()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ;
        });
    }


    /**
     * This method sets the action for the "Next" button.
     * When the button is clicked, the next contact is shown in the text fields.
     */
    private void setNextButton() {
        nextButton.setOnAction(e -> {
            Contact contact;
            contact = ContactManager.getNextContact();
            assert contact != null;
            nameTextField.setText(contact.getName());
            streetTextField.setText(contact.getStreet());
            cityTextField.setText(contact.getCity());
            stateTextField.setText(contact.getState());
            zipTextField.setText(contact.getZip());
        });
    }

    /**
     * This method sets the action for the "Previous" button.
     * When the button is clicked, the previous contact is shown in the text fields.
     */
    private void setPreviousButton() {
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

    /**
     * This method sets the action for the "First" button.
     * When the button is clicked, the first contact in the file is shown in the text fields.
     */
    private void setFirstButton() {
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

    /**
     * This method sets the action for the "Last" button.
     * When the button is clicked, the last contact in the file is shown in the text fields.
     */
    private void setLastButton() {
        lastButton.setOnAction(e -> {
            Contact contact;
            contact = ContactManager.getLastContact();
            nameTextField.setText(contact.getName());
            streetTextField.setText(contact.getStreet());
            cityTextField.setText(contact.getCity());
            stateTextField.setText(contact.getState());
            zipTextField.setText(contact.getZip());
        });
    }

    private void setAddButton() {
        addButton.setOnAction(event -> {
            if (validateInput()) {
                addContact(new Contact(nameTextField.getText(), streetTextField.getText(), cityTextField.getText(), stateTextField.getText(), zipTextField.getText()));
                clearFields();
                showAlert("Success", "Contact added successfully", Alert.AlertType.INFORMATION);
            } else {
                // Error handling is done within validateInput method
            }
        });
    }

    private boolean validateInput() {
        // Define the maximum lengths
        int maxNameLength = 32;
        int maxStreetLength = 32;
        int maxCityLength = 20;
        int maxStateLength = 2;
        int maxZipLength = 5;

        // Get the text from the text fields
        String name = nameTextField.getText();
        String street = streetTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String zip = zipTextField.getText();

        // Check if the text fields are not empty
        if (name.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
            showAlert("Error", "All fields must be filled", Alert.AlertType.ERROR);
            return false;
        }

        // Check if the text lengths are within the maximum lengths
        if (name.length() > maxNameLength || street.length() > maxStreetLength || city.length() > maxCityLength || state.length() > maxStateLength || zip.length() > maxZipLength) {
            showAlert("Error", "One or more fields exceed their maximum length", Alert.AlertType.ERROR);
            return false;
        }

        // If all checks pass, return true
        return true;
    }
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void clearFields() {
        nameTextField.setText("");
        streetTextField.setText("");
        cityTextField.setText("");
        stateTextField.setText("");
        zipTextField.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}