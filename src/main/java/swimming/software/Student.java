package swimming.software;

public class Student {
    // Attributes for Swimming Student
    private int studentKey;
    private String studentName;
    private int studentAge;
    private String studentGender;
    private String studentPhoneNumber;
    private int studentGrade;
    
    // Constructor for Swimming Student
    public Student(int studentKey, String studentName, int studentAge, String studentGender, String studentPhoneNumber, int studentGrade) {
        this.studentKey = studentKey;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentGender = studentGender;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentGrade = studentGrade;
    }

    // Getters and Setters for Swimming Student
    public int getStudentKey() {
        return studentKey;
    }

    public void setStudentKey(int studentKey) {
        this.studentKey = studentKey;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }
}
