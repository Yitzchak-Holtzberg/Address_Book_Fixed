module AddressBook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens AddressBook to javafx.graphics;
}