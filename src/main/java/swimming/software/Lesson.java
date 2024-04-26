package swimming.software;

public class Lesson {
    // Attributes for Swimming Lesson
    private int lessonKey;
    private String lessonDay;
    private String lessonTimeSlot;
    private int lessonCoachKey;
    private int studentsCount;
    private int grade;
    private int lessonRating;

    // Constructor for Swimming Lesson
    public Lesson(int lessonKey, String lessonDay, String lessonTimeSlot, int lessonCoachKey, int studentCount, int grade, int lessonRating) {
        this.lessonKey = lessonKey;
        this.lessonDay = lessonDay;
        this.lessonTimeSlot = lessonTimeSlot;
        this.lessonCoachKey = lessonCoachKey;
        this.studentsCount = studentCount;
        this.grade = grade;
        this.lessonRating = lessonRating;
    }

    // Getters and Setters for Swimming Lesson
    public int getLessonKey() {
        return lessonKey;
    }

    public void setLessonKey(int lessonKey) {
        this.lessonKey = lessonKey;
    }

    public String getLessonDay() {
        return lessonDay;
    }

    public void setLessonDay(String lessonDay) {
        this.lessonDay = lessonDay;
    }

    public String getLessonTimeSlot() {
        return lessonTimeSlot;
    }

    public void setLessonTimeSlot(String lessonTimeSlot) {
        this.lessonTimeSlot = lessonTimeSlot;
    }

    public int getLessonCoachKey() {
        return lessonCoachKey;
    }

    public void setLessonCoachKey(int lessonCoachKey) {
        this.lessonCoachKey = lessonCoachKey;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getLessonRating() {
        return lessonRating;
    }

    public void setLessonRating(int lessonRating) {
        this.lessonRating = lessonRating;
    }
}
