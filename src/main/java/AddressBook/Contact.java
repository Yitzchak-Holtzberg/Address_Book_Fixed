package AddressBook;

public class Contact {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;

    // Fixed sizes for each attribute
    private static final int NAME_SIZE = 32;
    private static final int STREET_SIZE = 32;
    private static final int CITY_SIZE = 20;
    private static final int STATE_SIZE = 2;
    private static final int ZIP_SIZE = 5;

    public Contact(String name, String street, String city, String state, String zip) {
        this.name = fixLength(name, NAME_SIZE);
        this.street = fixLength(street, STREET_SIZE);
        this.city = fixLength(city, CITY_SIZE);
        this.state = fixLength(state, STATE_SIZE);
        this.zip = fixLength(zip, ZIP_SIZE);
    }

    private String fixLength(String input, int length) {
        if (input.length() > length) {
            return input.substring(0, length);
        } else {
            return String.format("%-" + length + "s", input);
        }
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

}
