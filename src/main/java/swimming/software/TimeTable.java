package swimming.software;

import java.util.ArrayList;
import java.util.List;

public class TimeTable { 

    //Initialize the data for the time slot available
    public List<Lesson> initializeLessons(){
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson(1, "Monday", "4-5pm", 1, 3, 1, 5));
        lessons.add(new Lesson(2, "Wednesday", "4-5pm", 2, 2, 2, 4));
        lessons.add(new Lesson(3, "Friday", "4-5pm", 3, 3, 3, 3));
        lessons.add(new Lesson(4, "Saturday", "2-3pm", 4, 2, 4, 2));
        lessons.add(new Lesson(5, "Saturday", "3-4pm", 1, 3, 5, 1));
        lessons.add(new Lesson(6, "Monday", "5-6pm", 2, 2, 1, 5));
        lessons.add(new Lesson(7, "Wednesday", "5-6pm", 3, 3, 2, 4));
        lessons.add(new Lesson(8, "Friday", "5-6pm", 4, 2, 3, 3));
        lessons.add(new Lesson(9, "Saturday", "3-4pm", 1, 3, 4, 2));
        lessons.add(new Lesson(10, "Saturday", "2-3pm", 2, 2, 5, 1));
        lessons.add(new Lesson(11, "Monday", "6-7pm", 3, 3, 1, 5));
        lessons.add(new Lesson(12, "Monday", "4-5pm", 1, 3, 2, 5));
        lessons.add(new Lesson(13, "Wednesday", "5-6pm", 2, 3, 3, 4));
        lessons.add(new Lesson(14, "Friday", "6-7pm", 3, 3, 4, 3));
        lessons.add(new Lesson(15, "Saturday", "2-3pm", 4, 2, 5, 2));
        lessons.add(new Lesson(16, "Saturday", "3-4pm", 1, 3, 1, 1));
        lessons.add(new Lesson(17, "Monday", "5-6pm", 2, 2, 2, 5));
        lessons.add(new Lesson(18, "Wednesday", "4-5pm", 2, 3, 3, 4));
        lessons.add(new Lesson(19, "Friday", "5-6pm", 4, 2, 4, 3));
        lessons.add(new Lesson(20, "Saturday", "3-4pm", 1, 3, 5, 2));
        lessons.add(new Lesson(21, "Saturday", "2-3pm", 2, 2, 1, 1));
        lessons.add(new Lesson(22, "Monday", "6-7pm", 3, 3, 2, 5));
        lessons.add(new Lesson(23, "Monday", "4-5pm", 1, 3, 3, 5));
        lessons.add(new Lesson(24, "Wednesday", "5-6pm", 2, 3, 4, 4));
        lessons.add(new Lesson(25, "Friday", "6-7pm", 3, 3, 5, 3));
        lessons.add(new Lesson(26, "Saturday", "2-3pm", 4, 2, 1, 2));
        lessons.add(new Lesson(27, "Saturday", "3-4pm", 1, 3, 2, 1));
        lessons.add(new Lesson(28, "Monday", "5-6pm", 2, 2, 3, 5));
        lessons.add(new Lesson(29, "Wednesday", "4-5pm", 3, 3, 4, 4));
        lessons.add(new Lesson(30, "Friday", "5-6pm", 4, 2, 5, 3));
        lessons.add(new Lesson(31, "Saturday", "3-4pm", 1, 3, 1, 2));
        lessons.add(new Lesson(32, "Saturday", "2-3pm", 2, 2, 2, 1));
        lessons.add(new Lesson(33, "Monday", "6-7pm", 3, 3, 3, 5));
        lessons.add(new Lesson(34, "Monday", "4-5pm", 1, 3, 4, 5));
        lessons.add(new Lesson(35, "Wednesday", "5-6pm", 2, 2, 5, 4));
        lessons.add(new Lesson(36, "Friday", "6-7pm", 3, 3, 1, 3));
        lessons.add(new Lesson(37, "Saturday", "2-3pm", 4, 2, 2, 2));
        lessons.add(new Lesson(38, "Saturday", "3-4pm", 1, 3, 3, 1));
        lessons.add(new Lesson(39, "Monday", "5-6pm", 2, 2, 4, 5));
        lessons.add(new Lesson(40, "Wednesday", "4-5pm", 3, 3, 5, 4));
        lessons.add(new Lesson(41, "Friday", "5-6pm", 4, 2, 1, 3));
        lessons.add(new Lesson(42, "Saturday", "3-4pm", 1, 3, 2, 2));
        lessons.add(new Lesson(43, "Saturday", "2-3pm", 2, 2, 3, 1));
        lessons.add(new Lesson(44, "Monday", "6-7pm", 3, 3, 4, 5));
        return lessons;
    }

    //Initialize the data for the coach available
    public List<Coach> initializeCoaches() {
        List<Coach> coaches = new ArrayList<>();
        coaches.add(new Coach(1, "Mike", 5));
        coaches.add(new Coach(2, "John", 4));
        coaches.add(new Coach(3, "Mary", 3));
        coaches.add(new Coach(4, "Lilly", 3));
        return coaches;
    }

    //Initialize the data for the student available
    public List<Student> initializeStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Diana", 4, "Female", "0123456789",1));
        students.add(new Student(2, "Oliver", 5, "Male", "1234512345",2));
        students.add(new Student(3, "Charlie", 6, "Male", "9088767767", 3));
        students.add(new Student(4, "Mark", 7, "Male", "5644567567", 4));
        students.add(new Student(5, "Evelyn", 8, "Female", "9807789878", 5));
        students.add(new Student(6, "Peter", 9, "Male", "098900878", 4));
        students.add(new Student(7, "Billy", 10, "Male", "125678340",3));
        students.add(new Student(8, "Polly", 11, "Male", "0988908900", 5));
        students.add(new Student(9, "Mercy", 4, "Female", "2345678901", 1));
        students.add(new Student(10, "Ben", 5, "Male", "1231231233", 2));
        students.add(new Student(11, "William", 6, "Male", "2341232341", 3));
        students.add(new Student(12, "George", 7, "Male", "0989008900", 4));
        students.add(new Student(13, "Mandy", 8, "Female", "23112342234", 5));
        students.add(new Student(14, "Shelly", 9, "Male", "3333111222", 3));
        students.add(new Student(15, "Missy", 10, "Female", "2312312333", 4));
        return students;
    }
}
