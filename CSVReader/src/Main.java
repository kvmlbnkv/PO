import java.io.IOException;
import java.util.Locale;

//"files/titanic-part.csv"

public class Main {
    public static void main(String[] args) throws IOException, ColumnNotFoundException {
        CSVReader reader = new CSVReader("files/with-header.csv",";",true);
        while(reader.next()){
            int id = reader.getInt("id");
            String name = reader.get("nazwisko");
            double fare = reader.getDouble("dubel");

            System.out.printf(Locale.US,"%d %s %s\n",id,name,fare);
        }
    }
}
