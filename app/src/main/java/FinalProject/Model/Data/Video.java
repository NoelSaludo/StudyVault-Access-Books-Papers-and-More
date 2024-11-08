package FinalProject.Model.Data;

import java.util.Date;

public class Video extends Material {
    //video length
    private int length;
    private String platform;
    public Video(int id, String title, String author, String language, String url, Date publishedDate) {
        super(id, title, author, language, url, publishedDate);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
