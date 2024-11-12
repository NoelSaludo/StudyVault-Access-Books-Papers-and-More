package FinalProject.Controller;


import FinalProject.Model.Client;
import FinalProject.Model.Data.Book;
import FinalProject.Model.Data.Material;
import FinalProject.Model.Data.Paper;
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
        dance:
        while (true) {
            view.addMatStart();
            view.matType();
            char choice = in.next().charAt(0);
            in.nextLine();
            switch (choice) {
                case '1':
                    addBook(in);
                    break;
                case '2':
                    addPaper(in);
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

    private void addPaper(Scanner in) {
        Paper paper = new Paper();
        String[] labels = {"Journal Name: ", "DOI: ", "URL: ", "Published Date (dd/MM/yyyy): ", "Language: ", "Author: ", "Title: "};
        String[] data = getData(labels, in);
        if (data == null) return;
        paper.setTitle(data[6]);
        paper.setAuthor(data[5]);
        paper.setLanguage(data[4]);
        try {
            paper.setPublishedDate(strToSqlDate(data[3]));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        paper.setUrl(data[2]);
        paper.setDOI(data[1]);
        paper.setJournalName(data[0]);
        client.addPaper(paper);
    }

    private void addBook(Scanner in) {
        Book book = new Book();
        String[] labels = {"Publisher: ", "ISBN: ", "URL: ", "Published Date (dd/MM/yyyy): ", "Language: ", "Author: ", "Title: "};
        String[] data;
        data = getData(labels, in);
        if (data == null) return;
        book.setTitle(data[6]);
        book.setAuthor(data[5]);
        book.setLanguage(data[4]);
        try {
            book.setPublishedDate(strToSqlDate(data[3]));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        book.setUrl(data[2]);
        book.setISBN(data[1]);
        book.setPublisher(data[0]);
        client.addBook(book);
    }

    private String[] getData(String[] labels, Scanner in) {
        String[] data = new String[labels.length];
        for (int i = labels.length - 1; i >= 0; i--) {
            view.Label(labels[i]);
            String input = in.nextLine();
            if (input.equals("x")) return null;
            data[i] = input;
        }
        return data;
    }

    private Date strToSqlDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return (Date) sdf.parse(date);
    }

    private void findMat(Scanner in) {
        view.askForTitle();
        String title = in.nextLine();
        List<Material> materials = client.findMaterials(title);
        view.showFoundMats(materials);
    }

}
