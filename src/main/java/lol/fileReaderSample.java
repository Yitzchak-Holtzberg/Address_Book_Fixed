package lol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class demonstrates basic file I/O operations in Java.
 */
public class fileReaderSample {
    /**
     * The main method of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an ArrayList and fill it with integers from 1 to 5
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }

        // Create a DateFormat object for formatting the current date and time
        DateFormat theCurrentDateAndTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        // Create a double variable and set it to 5.5
        double fivePointFive = 5.5;

        // Write data to a file
        try (FileOutputStream file = new FileOutputStream("Exercise17_05.dat")) {
            // Write the string representation of the list to the file
            file.write(list.toString().getBytes());
            // Write a line separator to the file
            file.write(System.lineSeparator().getBytes());
            // Write the current date and time, formatted as a string, to the file
            file.write(theCurrentDateAndTime.format(System.currentTimeMillis()).getBytes());
            // Write another line separator to the file
            file.write(System.lineSeparator().getBytes());
            // Write the string representation of the double value to the file
            file.write(Double.toString(fivePointFive).getBytes());

        } catch (IOException e1) {
            // If an IOException is thrown, wrap it in a RuntimeException and rethrow it
            throw new RuntimeException(e1);
        }

        // Read data from the file
        try (FileInputStream file = new FileInputStream("Exercise17_05.dat")) {
            int content;
            // Read one byte at a time from the file, convert it to a character, and print it to the console
            while ((content = file.read()) != -1) System.out.print((char) content);
        } catch (IOException e2) {
            // If an IOException is thrown, wrap it in a RuntimeException and rethrow it
            throw new RuntimeException(e2);
        }
    }
}
