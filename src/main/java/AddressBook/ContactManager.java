package AddressBook;

import java.io.*;

/**
 * This class manages the contacts in an address book.
 * It uses a RandomAccessFile to store and retrieve contact information.
 */
public class ContactManager {
    private static RandomAccessFile file;
    private static long currentContactIndex = 0;

    // Static initializer block to initialize the RandomAccessFile
    static {
        try {
            file = new RandomAccessFile("contacts.dat", "rw");
        } catch (IOException e) {
            System.err.println("Error initializing file: " + e.getMessage());
        }
    }

    public static void newContactLocation() throws IOException {
        file.seek(file.length());
    }

    /**
     * Retrieves a contact from the file at a specific position.
     *
     * @param position The position in the file.
     * @return The contact at the specified position.
     */
    static Contact getContact(long position) {
        try {
            file.seek(position);
            String name = readFixedString(32);
            String street = readFixedString(32);
            String city = readFixedString(20);
            String state = readFixedString(2);
            String zip = readFixedString(5);
            return new Contact(name, street, city, state, zip);
        } catch (IOException e) {
            System.err.println("Error getting contact: " + e.getMessage());
            return null;
        }
    }

    /**
     * Adds a contact to the end of the file.
     *
     * @param contact The contact to be added.
     */
    public static void addContact(Contact contact) {
        try {
            file.seek(file.length());
            writeFixedString(contact.getName(), 32);
            writeFixedString(contact.getStreet(), 32);
            writeFixedString(contact.getCity(), 20);
            writeFixedString(contact.getState(), 2);
            writeFixedString(contact.getZip(), 5);
            currentContactIndex = file.length();
        } catch (IOException e) {
            System.err.println("Error adding contact: " + e.getMessage());
        }
    }

    /**
     * Updates the current contact in the file.
     *
     * @param contact The contact with updated information.
     * @throws IOException If an I/O error occurs.
     */
    public static void updateContact(Contact contact) throws IOException {
        try {
            file.seek(currentContactIndex);
            writeFixedString(contact.getName(), 32);
            writeFixedString(contact.getStreet(), 32);
            writeFixedString(contact.getCity(), 20);
            writeFixedString(contact.getState(), 2);
            writeFixedString(contact.getZip(), 5);
            currentContactIndex = file.getFilePointer();
        } catch (Exception ioException) {
            System.out.println("Failure. no idea why, this should've worked");
        }
    }

    /**
     * Retrieves the next contact from the file.
     *
     * @return The next contact.
     */
    public static Contact getNextContact() {
        try {
            Contact contact = getContact(currentContactIndex);
            currentContactIndex += getRecordSize();
            if (currentContactIndex >= file.length()) {
                currentContactIndex = 0;
            }
            return contact;
        } catch (IOException e) {
            System.err.println("Error getting next contact: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the previous contact from the file.
     *
     * @return The previous contact.
     */
    public static Contact getPreviousContact() {
        Contact contact = getContact(currentContactIndex);
        currentContactIndex -= getRecordSize();
        if (currentContactIndex < 0) {
            currentContactIndex = 0;
        }
        return contact;
    }

    /**
     * Retrieves the last contact in the file.
     *
     * @return The last contact.
     */
    public static Contact getLastContact() {
        try {
            long lastContactPosition = file.length() - getRecordSize();
            return getContact(lastContactPosition);
        } catch (IOException e) {
            System.err.println("Error getting last contact: " + e.getMessage());
            return null;
        }
    }

    /**
     * Reads a fixed size string from the file.
     *
     * @param size The size of the string.
     * @return The string read from the file.
     * @throws IOException If an I/O error occurs.
     */
    private static String readFixedString(int size) throws IOException {
        byte[] bytes = new byte[size];
        file.read(bytes);
        return new String(bytes).trim();
    }

    /**
     * Writes a fixed size string to the file.
     *
     * @param str  The string to be written.
     * @param size The size of the string.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeFixedString(String str, int size) throws IOException {
        byte[] bytes = new byte[size];
        byte[] strBytes = str.getBytes();
        int length = Math.min(strBytes.length, size);
        System.arraycopy(strBytes, 0, bytes, 0, length);
        file.write(bytes);
    }

    /**
     * Calculates the size of a record in the file.
     *
     * @return The size of a record.
     */
    private static int getRecordSize() {
        return 32 + 32 + 20 + 2 + 5; // Total size of all attributes
    }

    /**
     * Calculates the number of contacts in the file.
     *
     * @return The number of contacts.
     * @throws IOException If an I/O error occurs.
     */
    static int numberOfContacts() throws IOException {
        return (int) (file.length() / getRecordSize());
    }
}