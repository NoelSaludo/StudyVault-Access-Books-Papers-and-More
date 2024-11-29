package FinalProject.Model.Data;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Book extends Material {
    private String publisher, ISBN;

    public Book() {
        super();
    }

    public Book(int id, String title, String author, String language, String url, Date publishedDate, String iSBN,
                String publisher) {
        super(id, title, author, language, url, publishedDate);
        this.ISBN = iSBN;
        this.publisher = publisher;
    }

    @Override
    public void updateData(Scanner in) {
        super.updateData(in);
        String[] attr = {
                "ISBN: ",
                "publisher: "
        };
        String[] data = new String[2];
        for (int i = 0; i < 2; i++) {
            System.out.print(attr[i]);
            String d = in.nextLine();
            if (d.equals("exit")) break;
            data[i] = d;
        }
        setISBN(data[0]);
        setPublisher(data[1]);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String printData() {
        return "Book\n".concat(super.printData()).concat(" | ISBN: " + ISBN).concat(" | Publisher: " + publisher);
    }

    @Override
    public String toString() {
        return "book";
    }

    @Override
    public List<String> getList() {
        List<String> list =  super.getList();
        list.add(ISBN);
        list.add(publisher);
        return list;
    }
}
