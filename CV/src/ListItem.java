import java.io.PrintStream;

public class ListItem {
    String item;

    ListItem(String item){
        this.item = item;
    }

    public void setContent(String item) {
        this.item = item;
    }

    void writeHTML(PrintStream out){
        out.printf("<li>%s</li>\n",item);
    }
}
