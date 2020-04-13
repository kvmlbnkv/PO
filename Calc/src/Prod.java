import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        //wywołaj konstruktor jednoargumentowy przekazując new Constant(c)
        args.add(new Constant(c));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }


    @Override
    double evaluate() {
        double result =1;
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        for (Node arg:args) {
            result=result*arg.evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            r.add(m);
        }
        return r;
    }

    @Override
    boolean isZero() {
        return false;
    }

    public String toString(){
        StringBuilder b =  new StringBuilder();
        for (Node arg:args) {
            b.append(arg.toString());
            if(arg.equals(args.get(args.size()-1))){
            }
            else{
                b.append("*");
            }
        }
        return b.toString();
    }


}