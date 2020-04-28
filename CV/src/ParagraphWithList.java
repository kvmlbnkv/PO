import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph{
    UnorderedList list;

    public ParagraphWithList() {
        super();
        this.list = new UnorderedList();
    }

    public ParagraphWithList(String content) {
        super(content);
        this.list = new UnorderedList();
    }

    public ParagraphWithList(String content, UnorderedList list) {
        super(content);
        this.list = list;
    }

    public ParagraphWithList addListItem(String content) {
        list.addItem(content);
        return this;
    }

    public void writeHTML(PrintStream out) {
        super.writeHTML(out);
        list.writeHTML(out);
    }
}
