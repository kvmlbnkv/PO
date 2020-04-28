public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;

    void addPoint(double x, double y){
        if(x>xmax) xmax=x;
        if(x<xmin) xmin=x;
        if(y>ymax) ymax=y;
        if(y<ymin) ymin=y;
    }

    boolean contains(double x, double y){
        return x>xmin && x<xmax && y>ymin && y<ymax;
    }

    boolean contains(BoundingBox bb){
        return bb.xmin>this.xmin && bb.xmax<this.xmax && bb.ymin>this.ymin && bb.ymax<this.ymax;
    }

    boolean intersects(BoundingBox bb){
        return (bb.xmin<this.xmax || bb.xmax>this.xmin) && (bb.ymin<this.ymax || bb.ymax>this.ymin);
    }

    BoundingBox add(BoundingBox bb){
        if(bb.xmin<this.xmin) this.xmin=bb.xmin;
        if(bb.xmax>this.xmax) this.xmax=bb.xmax;
        if(bb.ymin<this.ymin) this.ymin=bb.ymin;
        if(bb.ymax>this.ymax) this.ymax=bb.ymax;
        return this;
    }

    boolean isEmpty(){
        return Double.isNaN(xmin) && Double.isNaN(xmax) && Double.isNaN(ymin) && Double.isNaN(ymax);
    }

    double getCenterX(){
        if(isEmpty()) throw new RuntimeException("Not implemented");
        else return (xmin+xmax)/2;
    }

    double getCenterY(){
        if(isEmpty()) throw new RuntimeException("Not implemented");
        else return (ymin+ymax)/2;
    }

    double distanceTo(BoundingBox bbx){
        if(this.isEmpty() || bbx.isEmpty()) throw new RuntimeException("Not implemented");
        else return Math.sqrt((bbx.getCenterX()-this.getCenterX())*(bbx.getCenterX()-this.getCenterX())+(bbx.getCenterY()-this.getCenterY())*(bbx.getCenterY()-this.getCenterY()));
    }

    @Override
    public String toString() {
        return "BoundingBox{"+"xmin = "+xmin+", xmax = "+xmax+", ymin = "+ymin+", ymax = "+ymax+'}';
    }
}
