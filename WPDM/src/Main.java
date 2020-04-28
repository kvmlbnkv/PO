import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ColumnNotFoundException {
        AdminUnitList list= new AdminUnitList();
        list.read("files/admin-units.csv");
        AdminUnitList nlist=list.getNeighbors(list.units.get(1),15);
        nlist.list();
    }
}
