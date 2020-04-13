import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void Matrix() {
        Matrix test = new Matrix(4,5);
        if(test.rows != 4 || test.cols !=5) fail("Error");
    }

    @org.junit.Test
    public void testMatrix() {
        Matrix m = new Matrix(new double[][]{{1,2},{3,4,5}});
        double test[][]= m.asArray();
        if(test[0][0]!=1 || test[0][1]!=2 || test[1][0]!=3 || test[1][1]!=4 || test[0][2]!=0 || test[1][2]!=5) fail("Error");
    }

    @org.junit.Test
    public void asArray() {
        double test[][]={{1,2,0},{3,4,5}};
        Matrix m = new Matrix(test);
        assertArrayEquals(m.asArray()[0],test[0],.1);
        assertArrayEquals(m.asArray()[1],test[1],.1);
    }

    @org.junit.Test
    public void get() {
        Matrix m = new Matrix(new double[][]{{1,2},{3,4,5}});
        if(m.get(1,2)!=5) fail("Error");
    }

    @org.junit.Test
    public void set() {
        Matrix m = new Matrix(new double[][]{{1,2},{3,4,5}});
        m.set(1,2,9);
        assertEquals(9,m.get(1,2),.1);
    }

    @org.junit.Test
    public void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @org.junit.Test
    public void reshape() {
        Matrix test = new Matrix(4,5);
        try{
            test.reshape(6,9);
        }catch(RuntimeException e){
            System.out.print("Error");
        }
        test.reshape(2,10);
    }

    @org.junit.Test
    public void shape() {
        Matrix test = new Matrix(4,5);
        int[]s=test.shape();
        if(s[0]!=4 || s[1]!=5) fail("Error");
    }

    @org.junit.Test
    public void add() {
        Matrix m = new Matrix(new double[][]{{1,2},{3,4,5}});
        Matrix n = new Matrix(new double[][]{{-1,-2},{-3,-4,-5}});
        m=m.add(n);
        assertEquals(0,m.frobenius(),.1);
    }

    @org.junit.Test
    public void sub() {
        Matrix m = new Matrix(new double[][]{{1,2},{3,4,5}});
        m=m.sub(m);
        assertEquals(0,m.frobenius(),.1);
    }

    @org.junit.Test
    public void mul() {
        Matrix m = new Matrix(new double[][]{{1,2},{3,4,5}});
        Matrix n = new Matrix(2,3);
        m=m.mul(n);
        assertEquals(0,m.frobenius(),.1);
    }

    @org.junit.Test
    public void div() {
        Matrix m = new Matrix(new double[][]{{1,2,6},{3,4,5}});
        m=m.div(m);
        assertEquals(m.rows*m.cols,m.frobenius(),.1);
    }

    @org.junit.Test
    public void testAdd() {
        int t=9;
        Matrix m = new Matrix(4,5);
        m=m.add(t);
        assertEquals(m.rows*m.cols*t*t,m.frobenius(),.1);
    }

    @org.junit.Test
    public void testSub() {
        int t=9;
        Matrix m = new Matrix(4,5);
        m=m.sub(t);
        assertEquals(m.rows*m.cols*(-t)*(-t),m.frobenius(),.1);
    }

    @org.junit.Test
    public void testMul() {
        int t=9;
        Matrix m = new Matrix(new double[][]{{1,1,1},{1,1,1}});
        m=m.mul(t);
        assertEquals(m.rows*m.cols*(t)*(t),m.frobenius(),.1);
    }

    @org.junit.Test
    public void testDiv() {
        int t=9;
        Matrix m = new Matrix(new double[][]{{9,9,9},{9,9,9}});
        m=m.div(t);
        assertEquals(m.rows*m.cols,m.frobenius(),.1);
    }

    @org.junit.Test
    public void dot() {
    }

    @org.junit.Test
    public void frobenius() {
        int n=9;
        Matrix m = new Matrix(0,0);
        m = m.eye(n);
        assertEquals(n,m.frobenius(),.1);
    }

    @org.junit.Test
    public void random() {
        Matrix m = new Matrix(0,0);
        m=m.random(4,5);
        assertNotEquals(0,m.get(3,3));
    }

    @org.junit.Test
    public void eye() {
        int n=9;
        Matrix m = new Matrix(0,0);
        m = m.eye(n);
        assertEquals(n,m.frobenius(),.1);
    }
}