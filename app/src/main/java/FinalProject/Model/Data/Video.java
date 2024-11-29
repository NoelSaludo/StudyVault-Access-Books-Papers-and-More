package FinalProject.Model.Data;

import FinalProject.Model.Enum.Type;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

    @Override
    public void updateData(Scanner in) {
        super.updateData(in);
        String[] attr = {
                "Duration: ",
                "Resolution: "
        };
        String[] data = new String[2];
        for (int i = 0; i < 2; i++) {
            System.out.print(attr[i]);
            String d = in.nextLine();
            if (d.equals("exit")) break;
            data[i] = d;
        }
        setDuration(Integer.parseInt(data[0]));
        setResolution(data[1]);
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
    public String printData() {
        return "Video\n".concat(super.printData()).concat(" | duration: ")
                .concat(String.valueOf(duration)).concat(" | resolution: ").concat(resolution);
    }

    @Override
    public String toString() {
        return "video";
    }

    @Override
    public List<String> getList() {
        List<String> list =  super.getList();
        list.add(String.valueOf(duration));
        list.add(resolution);
        return list;
    }
}
