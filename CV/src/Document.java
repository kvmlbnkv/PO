import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    public Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo=new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section s= new Section();
        s=s.setTitle(sectionTitle);
        sections.add(s);
        return s;
    }
    Document addSection(Section s){
        sections.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        out.print("<html>\n");
        out.print("<head>\n");
        out.printf("<title>%s</title>\n", title);
        out.print("<meta charset=\"UTF-8\">\n");
        out.print("</head>\n");
        out.print("<body>\n");
        out.printf("<h1>%s</h1>\n", title);
        photo.writeHTML(out);
        for (Section section : sections){
            section.writeHTML(out);
        }
        out.print("</body>\n");
        out.print("</html>\n");
    }
}
