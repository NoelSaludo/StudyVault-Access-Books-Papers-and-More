package FinalProject.Model.Data;

import FinalProject.Utils.DataCollector;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Used for creating new study material such as books, papers, and more
 * this is an abstract class so it must be inherited to be used
 */
public abstract class Material {
    private int id;
    private String title, author, language, url;
    private Date publishedDate;


    public Material(int id, String title, String author, String language, String url, Date publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
        this.url = url;
        this.publishedDate = publishedDate;
    }

    public Material() {
    }

    /**
     * Updates the data of the material using a Scanner object
     * @param in Scanner Object used for input
     */
    public void updateData(Scanner in) {
        String[] attr = {
                "Title: ",
                "Author: ",
                "Language: ",
                "Url: ",
                "Published Date: ",
        };
        String[] data = new DataCollector().getData(attr, in);
        setTitle(data[0]);
        setAuthor(data[1]);
        setLanguage(data[2]);
        setUrl(data[3]);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatter.parse(data[4]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String printData() {
        return "id: ".concat(String.valueOf(id)).concat(" | title: ").concat(title).concat(" | author: ").concat(author)
                .concat(" | language: ").concat(language).concat(" | url: ").concat(url).concat(" | publishDate: ")
                .concat(String.valueOf(publishedDate));
    }

    public List<String> getList() {
            return new ArrayList<String>(Arrays.asList(
                    String.valueOf(id),
                    title,
                    author,
                    language,
                    url,
                    publishedDate.toString()
            )) ;
    }
}
