package FinalProject.Model.Data;

import FinalProject.Model.Enum.Type;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

    @Override
    public void updateData(Scanner in) throws NumberFormatException {
        super.updateData(in);
        String[] attr = {
                "Duration: ",
                "TYPE (ACADEMIC, PROFESSIONAL, WEBINAR): "
        };
        String[] data = new String[2];
        for (int i = 0; i < 2; i++) {
            System.out.print(attr[i]);
            String d = in.nextLine();
            if (d.equals("exit")) break;
            data[i] = d;
        }
        setDuration(Integer.parseInt(data[0]));
        setType(Type.valueOf(data[1]));
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
    public String printData() {
        return "Seminar\n".concat(super.printData()).concat(" | Type: ").
                concat(type.toString()).concat(" | Duration: ").concat(String.valueOf(duration));
    }

    @Override
    public String toString() {
        return "seminar";
    }

    @Override
    public List<String> getList() {
        List<String> list = super.getList();
        list.add(String.valueOf(duration));
        list.add(type.toString());
        return list;
    }
}
