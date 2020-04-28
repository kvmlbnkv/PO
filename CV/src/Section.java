import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;

    public Section() {
        this.paragraphs = new ArrayList<>();
    }

    public Section(String title) {
        this.title = title;
    }

    Section setTitle(String title){
        this.title=title;
        return this;
    }
    Section addParagraph(String paragraphText){
        Paragraph p=new Paragraph();
        p.setContent(paragraphText);
        paragraphs.add(p);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<div>\n<h2>%s</h2>\n",title);
        for (Paragraph paragraph : paragraphs) {
            paragraph.writeHTML(out);
        }
        out.print("</div>\n");
    }
}
