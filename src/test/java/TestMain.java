import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import swimming.software.Coach;
import swimming.software.Lesson;
import swimming.software.Main;
import swimming.software.Student;
import swimming.software.TimeTable;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMain {
    TimeTable timeTable = new TimeTable();
    List<Student> students = timeTable.initializeStudents();
    List<Lesson> lessons = timeTable.initializeLessons();

    @Before
    public void setUp() {
        String input = "Oliver\nMonday\n4-5pm\nDiana\nMonday\n4-5pm\n1\n3\n5\nShelly\nFriday\n6-7pm\n7\nFemale\n9807897890\n2\nGregory\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    public void testABookingLesson() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.bookingLesson(students, lessons);

        String expectedOutput = "Lesson is successfully booked.";
        String actualOutput = outputStream.toString().trim();

        assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void testBAttendLesson() {
        TimeTable timeTable = new TimeTable();
        List<Student> students = timeTable.initializeStudents();
        List<Lesson> lessons = timeTable.initializeLessons();
        List<Coach> coaches = timeTable.initializeCoaches();

        // String input = "Diana\nMonday\n4-5pm\n1\n3\n5\n";
        // in = new ByteArrayInputStream(input.getBytes());
        // System.setIn(in);

        Main.bookingLesson(students, lessons);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Main.attendedLessons(lessons, coaches);

        String expectedOutput = "Lesson is successfully attended.";
        String actualOutput = outputStream.toString().trim();

        assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void testCCancelLesson() {
        TimeTable timeTable = new TimeTable();
        List<Student> students = timeTable.initializeStudents();
        List<Lesson> lessons = timeTable.initializeLessons();
        int lessonKey = 14;

        // String input = "Shelly\nFriday\n6-7pm\n";
        // in = new ByteArrayInputStream(input.getBytes());
        // System.setIn(in);

        Main.bookingLesson(students, lessons);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.deleteBookingFromMap(lessonKey, lessons);

        String expectedOutput = "Booking is successfully cancelled.";
        String actualOutput = outputStream.toString().trim();

        assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void testDaddStudent(){
        TimeTable timeTable = new TimeTable();
        List<Student> students = timeTable.initializeStudents();
        String studentName = "Ginny";
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main main = new Main();
        main.addStudentToList(students, studentName);

        String expectedOutput = "Student is successfully added.";
        String actualOutput = outputStream.toString().trim();

        assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void testEaddCoach(){
        TimeTable timeTable = new TimeTable();
        List<Coach> coaches = timeTable.initializeCoaches();
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main main = new Main();
        main.addCoachToList(coaches);

        String expectedOutput = "Coach is successfully added.";
        String actualOutput = outputStream.toString().trim();

        assertTrue(actualOutput.contains(expectedOutput));
    }
}
