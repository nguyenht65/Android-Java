package fu.prm391.sample.practicalexam;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private double number;
    @ColumnInfo
    private String fromUnit;
    @ColumnInfo
    private String toUnit;
    @ColumnInfo
    private double result;

    public History() {
    }

    public History(String date, double number, String fromUnit, String toUnit, double result) {
        this.date = date;
        this.number = number;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
