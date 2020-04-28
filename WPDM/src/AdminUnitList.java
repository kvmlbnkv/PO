import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idtounit = new HashMap<>();
    Map<AdminUnit, Long> unittoid = new HashMap<>();
    Map<AdminUnit, Long> parentid = new HashMap<>();
    //Map<Long, List<AdminUnit>> children = new HashMap<>();


    public void read(String filename) throws IOException, ColumnNotFoundException {
        CSVReader reader=new CSVReader(filename,",",true);
        while(reader.next()){
            String name=reader.get("name");
            int adminLevel=reader.getInt("admin_level");
            double population=reader.getDouble("population");
            double area=reader.getDouble("area");
            double density=reader.getDouble("density");
            Long id=reader.getLong("id");
            Long parent=reader.getLong("parent");

            double xmin=99999999;
            double xmax=-99999999;
            double ymin=99999999;
            double ymax=-99999999;
            for (int i=1;i<5;i++){
                if(xmin>reader.getDouble("x"+i)) xmin=reader.getDouble("x"+i);
                if(xmax<reader.getDouble("x"+i)) xmax=reader.getDouble("x"+i);
                if(ymin>reader.getDouble("y"+i)) ymin=reader.getDouble("y"+i);
                if(ymax<reader.getDouble("y"+i)) ymax=reader.getDouble("y"+i);
            }

            AdminUnit unit=new AdminUnit(name,adminLevel,population,area,density,xmin,xmax,ymin,ymax);
            units.add(unit);
            parentid.put(unit,parent);
            idtounit.put(id,unit);
            unittoid.put(unit,id);
        }

        dochildren();
        fixMissingValues();
    }

    void list(){
        for (AdminUnit unit:units) {
            System.out.println(unit.toString());
        }
    }

    void list(int offset, int limit){
        for (int i=offset-1;i<offset-1+limit;i++){
            System.out.println(units.get(i));
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        if (regex) {
            for (AdminUnit unit : units) {
                if (unit.name.matches(pattern)){
                    ret.units.add(unit);
                }
            }
        }
        else{
            for (AdminUnit unit : units) {
                if (unit.name.contains(pattern)){
                    ret.units.add(unit);
                }
            }
        }
        return ret;
    }

    void dochildren(){
        for (AdminUnit unit:units) {
            if(parentid.get(unit)!=-1) {
                unit.parent = idtounit.get(parentid.get(unit));
                unit.parent.children.add(unit);
            }
            else unit.parent = null;
        }
        /*for(AdminUnit unit:units){
            children.put(unittoid.get(unit),unit.children);
        }*/
    }

    void fixMissingValues(){
        for(AdminUnit unit:units){
            AdminUnit parents=unit.parent;
           if (unit.density==-1){
               while(parents.density==-1){
                   parents=parents.parent;
               }
               unit.density=parents.density;
           }
           if(unit.population==-1){
               unit.population=unit.area*unit.density;
           }
        }
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList neighbours = new AdminUnitList();
        for(AdminUnit uni:units){
            /*if(unit.adminLevel==6){
                if(unit.bbox.intersects(uni.bbox) || unit.bbox.distanceTo(uni.bbox)<=maxdistance){
                    neighbours.units.add(uni);
                }
            }*/
            if(uni.adminLevel==unit.adminLevel && unit.bbox.intersects(uni.bbox)){
                neighbours.units.add(uni);
            }
        }
        return neighbours;
    }
/*
    void fixMissingValues2(){
        for (AdminUnit unit:units) {
            if(unit.density!=-1){
                for(AdminUnit child:unit.children){
                    child.density=unit.density;
                }
            }

        }
        for (AdminUnit unit:units) {
            if(unit.population==-1){
                unit.population=unit.area*unit.density;
            }
        }

    }*/
}
