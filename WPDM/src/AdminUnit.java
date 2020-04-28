import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<>();;

    AdminUnit(String name,int adminLevel,double population,double area,double density,double xmin,double xmax,double ymin,double ymax){
        this.name=name;
        this.adminLevel=adminLevel;
        this.population=population;
        this.area=area;
        this.density=density;
        this.bbox.xmin=xmin;
        this.bbox.xmax=xmax;
        this.bbox.ymin=ymin;
        this.bbox.ymax=ymax;
    }

    public String toString(){
        if(parent!=null){
            return name+", "+adminLevel+", "+population+", "+area+", "+density+", "+parent.name+", "+bbox.toString();
        }
        else return name+", "+adminLevel+", "+population+", "+area+", "+density+", "+bbox.toString();
    }
}
