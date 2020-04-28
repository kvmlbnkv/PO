import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        Document cv = new Document("Janusz Kowalski - CV");
        cv.setPhoto("https://cdn-images.interviewme.pl/authors/wojtek_martynski_1.jpg");
        cv.addSection("Wyksztalcenie")
                .addParagraph("1410 Bitwa pod Grunwaldem")
                .addParagraph("1683 Odsiecz Wiedenska")
                .addParagraph("2019 Ogladanie jak Robert Kubica zdobywa 1 pkt w F1");
        cv.addSection("Umiejetnosci")
                .addParagraph(
                        new ParagraphWithList("Umiejetnosci")
                                .addListItem("Bog")
                                .addListItem("Honor")
                                .addListItem("Ojczyzna")
                );
        cv.writeHTML(System.out);
        try {
            cv.writeHTML(new PrintStream("cv.html", "ISO-8859-2"));
        }
        catch (Exception e){}
    }
}
