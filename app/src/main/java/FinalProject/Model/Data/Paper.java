package FinalProject.Model.Data;

import java.util.Date;

public class Paper extends Material {
    private String abs, DOI, journalName;

    public Paper(int id, String title, String author, String language, String url, Date publishedDate,
            String journalName, String abs, String dOI) {
        super(id, title, author, language, url, publishedDate);
        this.journalName = journalName;
        this.abs = abs;
        this.DOI = dOI;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
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
    
}
