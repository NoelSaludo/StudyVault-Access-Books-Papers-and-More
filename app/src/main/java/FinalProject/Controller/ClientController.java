package FinalProject.Controller;


import FinalProject.Model.Client;
import FinalProject.Model.Data.Book;
import FinalProject.Model.Data.Material;
import FinalProject.View.ClientView;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    private Client client;
    private ClientView view;

    public ClientController(Client client, ClientView view) {
        this.client = client;
        this.view = view;
    }

    public void run(Scanner in) {
        Boolean isRunning = true;
        while (isRunning) {
            view.greet(client.getUsername());
            char choice = in.next().charAt(0);
            in.nextLine();
            isRunning = validateChoice(choice, in);
        }
    }

    // Private methods -------
    private Boolean validateChoice(char choice, Scanner in) {
        switch (choice) {
            case '1':
                findMat(in);
                break;
            case '2':
                addMat(in);
                break;
            case 'x':
                return false;
            default:
                view.incorrectInput();
                break;
        }
        return true;
    }

    private void addMat(Scanner in) {
        /*
         * Material:
         *   -Type
         *   -Title
         *   -Author
         *
         * */
        dance: while (true) {
            view.addMatStart();
            view.matType();
            char choice = in.next().charAt(0);
            in.nextLine();
            switch (choice) {
                case '1':
                    if (!addBook(in)) System.out.println("Book added");
                    else System.out.println("Book not added");
                    break;
                case '2':
//                    addPaper(in);
                    break;
                case '3':
                    break;
                case 'x':
                    break dance;
                default:
                    view.incorrectInput();
                    break;
            }
        }
    }

    private Boolean addBook(Scanner in) {
        Book book = new Book();
        String[] labels = {"Publisher: ","ISBN: ","URL: ","Published Date (dd/MM/yyyy): ","Language: ","Author: ","Title: "};
        String[] data = new String[7];
        for (int i = labels.length-1; i >= 0; i--) {
            view.Label(labels[i]);
            data[i] = in.nextLine();
        }
        book.setTitle(data[6]);
        book.setAuthor(data[5]);
        book.setLanguage(data[4]);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            book.setPublishedDate(sdf.parse(data[3]));
        } catch (ParseException e) {
            System.out.println("Enter correct Date format");
        }
        book.setUrl(data[2]);
        book.setISBN(data[1]);
        book.setPublisher(data[0]);
        return client.addBook(book);
    }

    private void findMat(Scanner in) {
        view.askForTitle();
        String title = in.nextLine();
        List<Material> materials = client.findMaterials(title);
        view.showFoundMats(materials);
    }

}
