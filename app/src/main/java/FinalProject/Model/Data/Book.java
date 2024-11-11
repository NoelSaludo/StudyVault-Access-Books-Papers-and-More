package FinalProject.Model.Data;

import java.util.Date;

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
    public String toString() {
        return super.toString().concat(" | ISBN: " + ISBN).concat(" | Publisher: " + publisher);
    }
}
