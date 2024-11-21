package FinalProject.Controller;


import FinalProject.Model.Client;
import FinalProject.Model.Data.*;
import FinalProject.Model.Enum.Type;
import FinalProject.View.ClientView;

import java.util.Date;
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
            case '3':
                addToFav(in);
                break;
            case '4':
                showFavorites();
                break;
            case 'x':
                return false;
            default:
                view.incorrectInput();
                break;
        }
        return true;
    }

    private void addToFav(Scanner in) {
        findMat(in);
        view.label("Enter material id");
        int fav = in.nextInt();
        client.addFavorite(fav);
    }

    private void showFavorites() {
        view.label("favorites:");
        view.showFoundMats(client.getFavorites(client.getID()));
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
                    addVideo(in);
                    break;
                case '4':
                    addSeminar(in);
                    break;
                case 'x':
                    break dance;
                default:
                    view.incorrectInput();
                    break;
            }
        }
    }

    private void addSeminar(Scanner in) {
        Seminar seminar = new Seminar();
        String[] labels = {"Type (ACADEMIC, PROFFESSIONAL, WEBINAR): ", "Duration: ", "URL: ", "Published Date (dd/MM/yyyy): ", "Language: ", "Author: ", "Title: "};
        String[] data = getData(labels, in);
        if (data == null) return;
        seminar.setTitle(data[6]);
        seminar.setAuthor(data[5]);
        seminar.setLanguage(data[4]);
        try {
            seminar.setPublishedDate(strToDate(data[3]));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        seminar.setUrl(data[2]);
        seminar.setDuration(Integer.parseInt(data[1]));
        try {
            seminar.setType(Type.valueOf(data[0]));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        client.addSeminar(seminar);
    }

    private void addVideo(Scanner in) {
        Video video = new Video();
        String[] labels = {"Resolution: ", "Duration: ", "URL: ", "Published Date (dd/MM/yyyy): ", "Language: ", "Author: ", "Title: "};
        String[] data = getData(labels, in);
        if (data == null) return;
        video.setTitle(data[6]);
        video.setAuthor(data[5]);
        video.setLanguage(data[4]);
        try {
            video.setPublishedDate(strToDate(data[3]));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        video.setUrl(data[2]);
        video.setDuration(Integer.parseInt(data[1]));
        video.setResolution(data[0]);
        if (client.addVideo(video)) {
            view.label("Video added");
        } else {
            view.label("Video not added");
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
            paper.setPublishedDate(strToDate(data[3]));
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
            book.setPublishedDate(strToDate(data[3]));
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
            view.label(labels[i]);
            String input = in.nextLine();
            if (input.equals("x")) return null;
            data[i] = input;
        }
        return data;
    }

    private Date strToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }

    private void findMat(Scanner in) {
        view.askForTitle();
        String title = in.nextLine();
        List<Material> materials = client.findMaterials(title);
        view.showFoundMats(materials);
    }

}
