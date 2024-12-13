package FinalProject.Utils;

import FinalProject.Model.Data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataCollector {

    /**
     * Returns an array of string with  the same size as the given string array
     * @param labels String array that serves as the labels to print out
     * @param in Scanner object for collecting data
     * @return null if the in finds "0" and data if the input is complete
     */
    public String[] getData(String[] labels, Scanner in) {
        System.out.println("Enter 0 to cancel");
        String[] data = new String[labels.length];
        for (int i = 0; i < data.length; i++) {
            System.out.print(labels[i]);
            data[i] = in.nextLine();
            if (data[i].equals("0")) return null;
        }
        return data;
    }

    /**
     * Returns a List of String containing the data
     * @param labels String of an array that will be printed out
     * @param material used for sorting the material
     * @param in Scanner object for collecting data
     * @return a list if successful and null if cancelled
     */
    public List<String> getData(String[] labels, Material material, Scanner in) {
        List<String> data = new ArrayList<String>();
        System.out.println("Enter 0 to cancel");
        for (int i = 0; i < labels.length-1; i++) {

            // this mess sorts out the materials
            if (material.getClass().equals(Book.class))  {
                if (i > 6) continue;
            } else if (material.getClass().equals(Paper.class)) {
                if ((i > 4 && i < 7) || (i > 8)) continue;
            } else if (material.getClass().equals(Video.class)) {
                if ((i > 4 && i < 9) || (i > 10)) continue;
            } else if (material.getClass().equals(Seminar.class)) {
                if (i > 4 && i < 10) continue;
            }

            System.out.println(labels[i]);
            String input = in.nextLine();
            if (input.equals("0")) return null;
            data.add(input);
        }
        return data;
    }
}
