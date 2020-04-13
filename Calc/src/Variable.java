public class Variable extends Node {
    String name;
    double value;

    Variable(String name){
        this.name = name;
    }
    Variable(String name, double value){
        this.name = name;
        this.value = value;
    }

    @Override
    Node diff(Variable var) {
        if(var.name.equals(name))return new Constant(sign);
        else return new Constant(0);
    }

    @Override
    boolean isZero() {
        return false;
    }

    void setValue(double d){
        value = d;
    }

    @Override
    double evaluate() {
        return sign*value;
    }


    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }

}