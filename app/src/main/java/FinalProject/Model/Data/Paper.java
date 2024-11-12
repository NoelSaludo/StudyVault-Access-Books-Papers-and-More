package FinalProject.Model.Data;

import java.util.Date;

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
    public String toString() {
        return super.toString().concat(" | DOI: ").concat(DOI).concat(" | Journal Name").concat(journalName);
    }
}
