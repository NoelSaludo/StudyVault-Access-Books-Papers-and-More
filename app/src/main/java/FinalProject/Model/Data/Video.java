package FinalProject.Model.Data;

import java.util.Date;

public class Video extends Material {
    private int duration;
    private String resolution;

    public Video(int id, String title, String author, String language, String url, Date publishedDate, int duration, String resolution) {
        super(id, title, author, language, url, publishedDate);
        this.duration = duration;
        this.resolution = resolution;
    }

    public Video() {
       super();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "Video\n".concat(super.toString()).concat(" | duration: ")
                .concat(String.valueOf(duration)).concat(" | resolution: ").concat(resolution);
    }
}
