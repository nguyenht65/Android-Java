package fu.prm391.sample.recyclerviewexercise;

public class Student {
    private String rollNumber;
    private String name;
    private String address;
    private double gpa;

    public Student() {
    }

    public Student(String rollNumber, String name, String address, double gpa) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
