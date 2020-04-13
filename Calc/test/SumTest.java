import static org.junit.Assert.*;

public class SumTest {

    @org.junit.Test
    public void evaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x, 3))
                .add( new Power(x, 2))
                .add(-2, x)
                .add(7);
        x.setValue(5);
        assertEquals(exp.evaluate(), 272, .1);
    }

    @org.junit.Test
    public void testToString() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x, 3))
                .add( new Power(x, 2))
                .add(-2, x)
                .add(7);
        assertEquals(exp.toString(),"2*x^3 + x^2 + (-2)*x + 7");
    }
}