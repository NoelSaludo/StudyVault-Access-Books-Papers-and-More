package FinalProject.Model.Data;

import FinalProject.Model.Enum.Type;
import FinalProject.Utils.DataCollector;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Video extends Material {
    private int duration;
    private String platform;

    public Video(int id, String title, String author, String language, String url, Date publishedDate, int duration, String platform) {
        super(id, title, author, language, url, publishedDate);
        this.duration = duration;
        this.platform = platform;
    }

    public Video() {
       super();
    }

    @Override
    public void updateData(Scanner in) {
        super.updateData(in);
        String[] attr = {
                "Duration: ",
                "Platform: "
        };
        String[] data = new DataCollector().getData(attr, in);
        setDuration(Integer.parseInt(data[0]));
        setPlatform(data[1]);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String printData() {
        return "Video\n".concat(super.printData()).concat(" | duration: ")
                .concat(String.valueOf(duration)).concat(" | Platform: ").concat(platform);
    }

    @Override
    public String toString() {
        return "video";
    }

    @Override
    public List<String> getList() {
        List<String> list =  super.getList();
        list.add(String.valueOf(duration));
        list.add(platform);
        return list;
    }
}
