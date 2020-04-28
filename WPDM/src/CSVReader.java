import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();
    String[]current;

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    public CSVReader(String filename, String delimiter) throws FileNotFoundException{
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
    }

    public CSVReader(String filename) throws FileNotFoundException{
        reader = new BufferedReader(new FileReader(filename));
    }

    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }
    }

    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength(){
        return current.length;
    }

    boolean isMissing(int columnIndex){
        return columnIndex < 0 || columnIndex >= current.length || current[columnIndex].isEmpty();
    }

    boolean isMissing(String columnLabel){
        return isMissing(columnLabelsToInt.getOrDefault(columnLabel, -1));
    }

    boolean next() {
        try {
            current = reader.readLine().split(delimiter);
            return true;
        }
        catch (IOException | NullPointerException e) {return false;}
    }

    public String get(String columnLabel) throws ColumnNotFoundException{
        if (!columnLabelsToInt.containsKey(columnLabel)) {
            throw new ColumnNotFoundException(columnLabel);
        }
        if(current[columnLabelsToInt.get(columnLabel)].length()==0){
            return "-1";
        }
        return current[columnLabelsToInt.get(columnLabel)];
    }

    String get(int columnIndex) throws ColumnIndexOutOfBoundsException {
        try {
            if(current[columnIndex].length()==0){
                return "-1";
            }
            return current[columnIndex];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new ColumnIndexOutOfBoundsException(columnIndex, current.length);
        }
    }

    int getInt(int columnIndex) throws ColumnIndexOutOfBoundsException {
        return Integer.parseInt(get(columnIndex));
    }

    public int getInt(String columnLabel) throws ColumnNotFoundException {
        return Integer.parseInt(get(columnLabel));
    }

    public double getDouble(String columnLabel) throws ColumnNotFoundException {
        return Double.parseDouble(get(columnLabel));
    }

    public Long getLong(String columnLabel) throws ColumnNotFoundException {
        return Long.parseLong(get(columnLabel));
    }

}