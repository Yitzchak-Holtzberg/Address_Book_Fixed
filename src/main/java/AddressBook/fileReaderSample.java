package AddressBook;

import java.io.*;
import java.util.Date;

public class fileReaderSample {

    public static void main(String[] args) throws Exception {
        int [] array = {1, 2, 3, 4, 5};
        Date date = new Date();
        double num = 5.5;

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Exercise17_05.dat", false));
             ObjectInputStream input = new ObjectInputStream(new FileInputStream("Exercise17_05.dat"))) {

            output.writeObject(array);
            output.writeObject(date);
            output.writeObject(num);

            displayObjects(input);
        }
    }

    public static void displayObjects(ObjectInputStream input) throws Exception {
        int [] newArray = (int[])(input.readObject());
        String newDate = input.readObject().toString();
        double newNum = (double)input.readObject();

        for(int i : newArray) {
            System.out.print(i + " ");
        }

        System.out.println("\n" + newDate + "\n" + newNum);
    }
}