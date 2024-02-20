package AddressBook;

import java.io.*;

public class ContactManager {
    private static RandomAccessFile file;
    private static long currentContactIndex = 0;

    static {
        try {
            file = new RandomAccessFile("contacts.dat", "rw");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
            throw new RuntimeException(e);
        }
    }
    public static Contact getCurrentContact() {
        return getContact(currentContactIndex);
    }

    public static Contact getNextContact() throws IOException {
        currentContactIndex += getRecordSize();
        if (currentContactIndex >= file.length()) {
            currentContactIndex = 0;
        }
        return getContact(currentContactIndex);
    }

    public static Contact getPreviousContact() {
        currentContactIndex -= getRecordSize();
        if (currentContactIndex < 0) {
            try {
                currentContactIndex = (file.length() / getRecordSize() - 1) * getRecordSize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return getContact(currentContactIndex);
    }

    static Contact getContact(long position) {
        try {
            file.seek(position);
            String name = file.readUTF();
            String street = file.readUTF();
            String city = file.readUTF();
            String state = file.readUTF();
            String zip = file.readUTF();
            return new Contact(name, street, city, state, zip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeFixedString(String str, int size) throws IOException {
        byte[] bytes = str.getBytes();
        if (bytes.length > size) {
            file.write(bytes, 0, size);
        } else {
            file.write(bytes);
            file.write(new byte[size - bytes.length]); // Padding with zeros
        }
    }

    private static int getRecordSize() {
        return 32 + 32 + 20 + 2 + 5; // Total size of all attributes
    }
}