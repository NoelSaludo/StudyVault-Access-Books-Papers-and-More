package FinalProject.Model.Data;

import java.util.Date;

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

    @Override
    public String toString() {
        return "id: ".concat(String.valueOf(id)).concat(" | title: ").concat(title).concat(" | author: ").concat(author)
                .concat(" | language: ").concat(language).concat(" | url: ").concat(url).concat(" | publishDate: ")
                .concat(String.valueOf(publishedDate));
    }
}
