import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> items = new ArrayList<>() ;

    void addItem(ListItem i){
        items.add(i);
    }

    void addItem(String content) {
        items.add(new ListItem(content));
    }

    void writeHTML(PrintStream out){
        out.print("<ul>\n");
        for (ListItem item: items) {
            item.writeHTML(out);
        }
        out.print("</ul>\n");
    }
}
