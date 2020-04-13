import java.util.Random;

public class Matrix {
    double[]data;
    int rows;
    int cols;

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d){
        rows=d.length;
        for(int i=0;i<rows;i++) {
                if(d[i].length>cols){
                    cols=d[i].length;
                }
            }
        data = new double[rows*cols];

        int m=0;
        for(int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if(j<d[i].length){
                    data[m] = d[i][j];
                    m++;
                }
                else{
                    data[m]=0;
                    m++;
                }
            }
        }
    }

    double[][] asArray(){
        double[][] m = new double[rows][cols];
        int l=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                m[i][j]=data[l];
                l++;
            }
        }
        return m;
    }

    double get(int r,int c){
        return data[r*cols+c];
    }

    void set(int r,int c, double value){
        data[r*cols+c] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        int l=0;
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0;j<cols;j++){
                buf.append(data[l]);
                if(j<cols-1){
                    buf.append(", ");
                }
                l++;
            }
            buf.append("]");
            if(i<rows-1){
                buf.append(", ");
            }
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        }
        else{
            rows=newRows;
            cols=newCols;
        }
    }

    int[] shape(){
        return new int[]{rows,cols};
    }

    Matrix add(Matrix m){
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new RuntimeException("Wrong dimensions");
        }
        else{
            Matrix s= new Matrix(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    s.set(i, j, this.get(i,j) + m.get(i,j));
                }
            }
        return s;
        }
    }

    Matrix sub(Matrix m){
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new RuntimeException("Wrong dimensions");
        }
        else{
            Matrix s= new Matrix(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    s.set(i, j, this.get(i,j) - m.get(i,j));
                }
            }
            return s;
        }
    }

    Matrix mul(Matrix m){
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new RuntimeException("Wrong dimensions");
        }
        else{
            Matrix s= new Matrix(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    s.set(i, j, this.get(i,j) * m.get(i,j));
                }
            }
            return s;
        }
    }

    Matrix div(Matrix m){
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new RuntimeException("Wrong dimensions");
        }
        else{
            Matrix s= new Matrix(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(m.get(i,j) == 0){
                        throw new RuntimeException("Can't divide by 0");
                    }
                    else{
                        s.set(i, j, this.get(i, j) / m.get(i, j));
                    }
                }
            }
            return s;
        }
    }

    Matrix add(double w){
        Matrix s= new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                s.set(i, j, this.get(i,j) + w);
            }
        }
        return s;
    }

    Matrix sub(double w){
        Matrix s= new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                s.set(i, j, this.get(i,j) - w);
            }
        }
        return s;
    }

    Matrix mul(double w){
        Matrix s= new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                s.set(i, j, this.get(i,j) * w);
            }
        }
        return s;
    }

    Matrix div(double w){
        if(w == 0){
            throw new RuntimeException("Can't divide by 0");
        }
        else{
            Matrix s = new Matrix(rows, cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    s.set(i, j, this.get(i, j) / w);
                }
            }
            return s;
        }
    }

    Matrix dot(Matrix m){
        if(cols != m.rows){
            throw new RuntimeException("Wrong dimensions");
        }
        else{
            Matrix s = new Matrix(rows, m.cols);
            int t;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < m.cols; j++) {
                    t=0;
                    for(int n = 0; n < cols; n++){
                        t += this.get(i,n) * m.get(n,j);
                    }
                    s.set(i, j, t);
                }
            }
            return s;
        }
    }

    double frobenius(){
        double t=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                t=t+(get(i,j)*get(i,j));
            }
        }
        return t;
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                m.set(i, j, r.nextDouble());
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for (int i = 0; i < n; i++) {
            m.set(i, i,1);
        }
        return m;
    }

    public static void main(String args[]){
        int rows=4, cols=5;
        Matrix m= new Matrix(new double[][]{{5,11,0,2},{13,9,4,6},{4,-1,4,-2},{1,-2,3}});
        Matrix m1= new Matrix(new double[][]{{1,0,9,5},{-6,-1,2,1},{-2,-5,3,3},{1,5,9}});
        Matrix n= m.dot(m1);
        String n1 = n.toString();
        System.out.print(n1);
        /*
        for(int i=0;i<4;i++) {
            for(int j=0;j<5;j++) {
                System.out.print(n[i][j]);
                System.out.print(" ");
                if(j==4){
                    System.out.print("\n");
                }
            }
        }
*/
    }
}
