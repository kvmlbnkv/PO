import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Constant extends Node {
    double value;
    Constant(double value){
        this.sign = value<0?-1:1;
        this.value = value<0?-value:value;
    }

    @Override
    Node diff(Variable var){
        return new Constant(0);
    }

    @Override
    boolean isZero() {
        return true;
    }

    @Override
    double evaluate() {
        return sign*value;
    }

    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        StringBuilder b =  new StringBuilder();
        DecimalFormat format = new DecimalFormat("0.#####",new DecimalFormatSymbols(Locale.US));
        if(this.getSign()<0) b.append("(");
        b.append(sgn+format.format(value));
        if(this.getSign()<0) b.append(")");
        return b.toString();
    }
}
