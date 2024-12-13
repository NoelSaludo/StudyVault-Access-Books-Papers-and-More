package FinalProject.Controller;


import FinalProject.Model.Admin;
import FinalProject.Model.Client;
import FinalProject.Model.Data.*;
import FinalProject.Model.Enum.Type;
import FinalProject.Utils.DataCollector;
import FinalProject.View.ClientView;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    private Client client;
    private ClientView view;
    private Boolean isRunning;

    public ClientController(Client client, ClientView view) {
        this.client = client;
        this.view = view;
        this.isRunning = true;
    }

    public void run(Scanner in, Boolean isAdmin) {
        if (isAdmin) {
            AdminMode(in);
        } else {
            normalMode(in);
        }
    }

    public void setUser(User currUser) {
        client.setUser(currUser);
    }

    public void setClient(Admin admin) {
        this.client = admin;
    }

    // Private methods -------
    /* === Modes === */
    private void normalMode(Scanner in) {
        while (isRunning) {
            view.greet(client.getUsername());
            char choice = in.next().charAt(0);
            in.nextLine();
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
                    isRunning = false;
                default:
                    view.incorrectInput();
                    break;
            }
        }
    }

    private void AdminMode(Scanner in) {
        while (isRunning) {
            view.greetAdmin(client.getUsername());
            char choice = in.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    findMat(in);
                    break;
                case '2':
                    addMat(in);
                    break;
                case '3':
                    deleteMat(in);
                    break;
                case '4':
                    updateMat(in);
                    break;
                case 'x':
                    isRunning = false;
                    break;
                default:

                    break;
            }
        }

    }

    private void addToFav(Scanner in) {
        findMat(in);
        view.label("Enter material id: ");
        int fav = in.nextInt();
        client.addFavorite(fav);
    }

    private void showFavorites() {
        view.label("favorites:\n");
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
        List<String> data;
        data = new DataCollector().getData(client.getMaterialAttributes(), seminar ,in);
        if (data == null) return;
        seminar.setTitle(data.get(0));
        seminar.setAuthor(data.get(1));
        seminar.setLanguage(data.get(2));
        try {
            seminar.setPublishedDate(strToDate(data.get(4)));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        seminar.setUrl(data.get(3));
        seminar.setType(Type.valueOf(data.get(6)));
        seminar.setDuration(Integer.parseInt(data.get(5)));
        if (client.addSeminar(seminar)) {
            view.label("Seminar added\n");
        } else {
            view.label("Seminar not added\n");
        }
    }

    private void addVideo(Scanner in) {
        Video video = new Video();
        List<String> data;
        data = new DataCollector().getData(client.getMaterialAttributes(), video ,in);
        if (data == null) return;
        video.setTitle(data.get(0));
        video.setAuthor(data.get(1));
        video.setLanguage(data.get(2));
        try {
            video.setPublishedDate(strToDate(data.get(4)));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        video.setUrl(data.get(3));
        video.setPlatform(data.get(5));
        video.setDuration(Integer.parseInt(data.get(6)));
        if (client.addVideo(video)) {
            view.label("Video added\n");
        } else {
            view.label("Video not added\n");
        }
    }

    private void addPaper(Scanner in) {
        Paper paper = new Paper();
        List<String> data;
        data = new DataCollector().getData(client.getMaterialAttributes(), paper ,in);
        if (data == null) return;
        paper.setTitle(data.get(0));
        paper.setAuthor(data.get(1));
        paper.setLanguage(data.get(2));
        try {
            paper.setPublishedDate(strToDate(data.get(4)));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        paper.setUrl(data.get(3));
        paper.setDOI(data.get(5));
        paper.setJournalName(data.get(6));
        if (client.addPaper(paper)) {
            view.label("Paper added\n");
        } else {
            view.label("Paper not added\n");
        }
    }

    private void addBook(Scanner in) {
        Book book = new Book();
        List<String> data;
        data = new DataCollector().getData(client.getMaterialAttributes(), book ,in);
        if (data == null) return;
        book.setTitle(data.get(0));
        book.setAuthor(data.get(1));
        book.setLanguage(data.get(2));
        try {
            book.setPublishedDate(strToDate(data.get(4)));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        book.setUrl(data.get(3));
        book.setISBN(data.get(5));
        book.setPublisher(data.get(6));
        if (client.addBook(book)) {
            view.label("Book added\n");
        } else {
            view.label("Book not added\n");
        }
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


    private void deleteMat(Scanner in) {
        findMat(in);
        int id = -1;
        view.label("Enter material id: ");
        try {
            id = in.nextInt();
            in.nextLine();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        Admin admin = (Admin) client;
        if (admin.deleteMaterial(id)) {
            view.label("deletion success");
        } else {
            view.label("deletion failed");
        }
    }

    private void updateMat(Scanner in) {
        findMat(in);
        int id = -1;
        try {
            view.label("Enter material id: ");
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        Admin admin = (Admin) client;
        Material material = admin.getMaterial(id);
        try {
            material.updateData(in);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (admin.updateMaterial(material)) {
            view.label("update success\n");
        } else {
            view.label("update failed\n");
        }

    }

}
