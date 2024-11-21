package FinalProject.Model.Data;

import FinalProject.Model.Enum.Type;

import java.util.Date;

public class Seminar extends Material {
    private Type type;
    private int duration;

    public Seminar(int id, String title, String author, String language, String url, Date publishedDate, Type type, int duration) {
        super(id, title, author, language, url, publishedDate);
        this.type = type;
        this.duration = duration;
    }

    public Seminar() {
        super();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Seminar\n".concat(super.toString()).concat(" | Type: ").
                concat(type.toString()).concat(" | Duration: ").concat(String.valueOf(duration));
    }
}
