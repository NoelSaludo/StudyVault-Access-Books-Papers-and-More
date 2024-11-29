package FinalProject.Model.Data;

import FinalProject.Utils.DataCollector;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Paper extends Material {
    private String DOI, journalName;

    public Paper(int id, String title, String author, String language, String url, Date publishedDate,
                 String journalName, String dOI) {
        super(id, title, author, language, url, publishedDate);
        this.journalName = journalName;
        this.DOI = dOI;
    }

    public Paper() {
        super();
    }

    @Override
    public void updateData(Scanner in) {
        super.updateData(in);
        String[] attr = {
                "DOI: ",
                "Journal Name: "
        };
        String[] data = new DataCollector().getData(attr, in);
        setDOI(data[0]);
        setJournalName(data[1]);
    }
    public String getDOI() {
        return DOI;
    }

    public void setDOI(String dOI) {
        DOI = dOI;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    @Override
    public String printData() {
        return "Paper\n".concat(super.printData()).concat(" | DOI: ").concat(DOI).concat(" | Journal Name").concat(journalName);
    }

    @Override
    public String toString() {
        return "paper";
    }

    @Override
    public List<String> getList() {
        List<String> list =  super.getList();
        list.add(DOI);
        list.add(journalName);
        return list;
    }
}
