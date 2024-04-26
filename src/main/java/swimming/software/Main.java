package swimming.software;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<Integer, List<Integer>> bookedLessons = new HashMap<>();
    static Map<Integer, List<Integer>> attendedLessons = new HashMap<>();
    static Map<Integer, List<Integer>> cancelledLessons = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //TimeTable
        TimeTable timeTable = new TimeTable();
        List<Coach> coaches = timeTable.initializeCoaches();
        List<Student> students = timeTable.initializeStudents();
        List<Lesson> lessons = timeTable.initializeLessons();

        while(true){
            menu();
            System.out.print("Enter the option: ");
            String option = scanner.nextLine();
            //Check if the option is valid
            while (!option.matches("[0-9]+") || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 14){
                System.out.println("Invalid option. Enter a valid option");
                System.out.print("Enter the option: ");
                option = scanner.nextLine();
            }
            int selectedOption = Integer.parseInt(option);
            switch (selectedOption) {
                case 1:
                    viewTimeTableByDay(lessons);
                    break;
                case 2:
                    viewTimeTableByCoach(lessons);
                    break;
                case 3:
                    viewTimeTableByGrade(lessons);
                    break;
                case 4:
                    bookingLesson(students, lessons);
                    break;
                case 5:
                    System.out.print("Enter the lesson key to cancel the booking: ");
                    String lessonKeyString = scanner.nextLine();
                    //Check if the lesson key is valid
                    while (!lessonKeyString.matches("[0-9]+")) {
                        System.out.println("Invalid lesson key. Enter a valid lesson key");
                        System.out.print("Enter the lesson key: ");
                        lessonKeyString = scanner.nextLine();
                    }
                    int lessonKey = Integer.parseInt(lessonKeyString);
                    deleteBookingFromMap(lessonKey, lessons);
                    break;
                case 6:
                    attendedLessons(lessons, coaches);
                    break;
                case 7:
                    studentsReport(students, lessons);
                    break;
                case 8:
                    coachReport(coaches, lessons);
                    break;
                case 9:
                    System.out.print("Enter the student name: ");
                    String studentName = scanner.nextLine();
                    addStudentToList(students, studentName);
                    break;
                case 10:
                    addCoachToList(coaches);
                    break;
                case 11:
                    lessonTimeTable(timeTable);
                    break;
                case 12:
                    viewBookingDetails(students, lessons);
                    break;
                case 13:
                    System.exit(0);
                    break;
            }
        }
    }

    //Menu for the user to select the option
    public static void menu() {
        System.out.println("****HJSS Swimming Software****");
        System.out.println("1. View timetable by day");
        System.out.println("2. View timetable by coach");
        System.out.println("3. View timetable by grade");
        System.out.println("4. Create a new booking");
        System.out.println("5. Cancel a booking");
        System.out.println("6. Attend lesson");
        System.out.println("7. Students report");
        System.out.println("8. Coach report");
        System.out.println("9. Add new student");
        System.out.println("10. Add new coach");
        System.out.println("11. View all lessons");
        System.out.println("12. View all booked lessons");
        System.out.println("13. Exit");
    }

    public static List<Lesson> lessonTimeTable(TimeTable timeTable) {
        System.out.println("****Swimming lessons****");
        List<Lesson> lessons = timeTable.initializeLessons();
        System.out.printf("| %-8s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", "Lesson Key", "Day", "Time Slot", "Coach Key", "Lesson Rating", "Student Count", "Grade");
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (Lesson lesson : lessons) {
            System.out.printf("| %-10s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", lesson.getLessonKey(), lesson.getLessonDay(), lesson.getLessonTimeSlot(), lesson.getLessonCoachKey(), lesson.getLessonRating(), lesson.getStudentsCount(), lesson.getGrade());
        }
        return lessons;
    }

    //Create a new booking for student based on the lesson
    public static void bookingLesson(List<Student> students, List<Lesson> lessons) {
        System.out.println("****Create a new booking****");
        System.out.print("Enter the student name: ");
        String studentName = scanner.nextLine();
        while(studentName.isEmpty() || studentName.isBlank() || studentName.matches(".*\\d.*")){
            System.out.println("Student name cannot be invalid.");
            System.out.print("Enter the student name: ");
            studentName = scanner.nextLine();
        }
        
        Student selectedStudent = null;
        boolean isStudentRegistered = false;
        //Check if the student is already registered
        for (Student student : students) {
            if (student.getStudentName().equals(studentName)) {
                System.out.println("Student is already registered.");
                selectedStudent = student;
                isStudentRegistered = true;
                break;
            }
        }

        if (!isStudentRegistered) {
            addStudentToList(students, studentName);
            selectedStudent = students.get(students.size() - 1);
            System.out.println("Student is successfully registered.");
        }
        
        System.out.print("Enter the day to be booked: ");
        String day = scanner.nextLine();
        //Check if the day is valid
        while (!day.equals("Monday") && !day.equals("Wednesday") && !day.equals("Friday") && !day.equals("Saturday")) {
            System.out.println("Invalid day. Enter a valid day (Monday, Wednesday, Friday, Saturday)");
            System.out.print("Enter the day to be booked: ");
            day = scanner.nextLine();
        }
        
        System.out.print("Enter the time slot to be booked: ");
        String timeSlot = scanner.nextLine();
        //Check if the time slot is valid when the day is Monday, wednesday, Friday then the time slot should be 4-5pm, 5-6pm, 6-7pm and when the day is Saturday then the time slot should be 2-3pm, 3-4pm
        while ((day.equals("Monday") || day.equals("Wednesday") || day.equals("Friday")) && (!timeSlot.equals("4-5pm") && !timeSlot.equals("5-6pm") && !timeSlot.equals("6-7pm")) || (day.equals("Saturday") && (!timeSlot.equals("2-3pm") && !timeSlot.equals("3-4pm")))) {
            System.out.println("Invalid time slot. Enter a valid time slot");
            System.out.print("Enter the time slot to be booked: ");
            timeSlot = scanner.nextLine();
        }

        int studentCount = 0;
        //Check if the lesson is available
        for (Lesson lesson : lessons) {
            if (lesson.getLessonDay().equals(day) && lesson.getLessonTimeSlot().equals(timeSlot) && lesson.getStudentsCount() < 4){
                if( bookedLessons.get(lesson.getLessonKey()) != null && bookedLessons.get(lesson.getLessonKey()).contains(selectedStudent.getStudentKey())){
                    System.out.println("Lesson is already booked.");
                    return;
                }
                studentCount = lesson.getStudentsCount();
                studentCount++;
                lesson.setStudentsCount(studentCount);
                addBookingToMap(lesson.getLessonKey(), selectedStudent.getStudentKey());
                System.out.println("Lesson is successfully booked.");
                break;
            }
        }
       
    }

    //Add student to the Student list if the student details are not already present
    public static void addStudentToList(List<Student> students, String studentName){
        System.out.print("Enter the student age: ");
        String studentAgeString = scanner.nextLine();
        //Check if the student age is valid
        while (!studentAgeString.matches("[0-9]+") || Integer.parseInt(studentAgeString) < 3 || Integer.parseInt(studentAgeString) > 12){
            System.out.println("Invalid student age.");
            System.out.print("Enter the student age: ");
            studentAgeString = scanner.nextLine();
        }
        int studentAge = Integer.parseInt(studentAgeString);

        System.out.print("Enter the gender of the student: ");
        String studentGender = scanner.nextLine();
        //Check if the student gender is valid
        while (!studentGender.equalsIgnoreCase("male") && !studentGender.equalsIgnoreCase("female")){
            System.out.println("Invalid student gender.");
            System.out.print("Enter the gender of the student: ");
            studentGender = scanner.nextLine();
        }

        System.out.print("Enter the student phone number: ");
        String studentPhoneNumber = scanner.nextLine();
        //Check if the student phone number is valid
        while (!studentPhoneNumber.matches("[0-9]+") || studentPhoneNumber.length() != 10){
            System.out.println("Invalid student phone number.");
            System.out.print("Enter the student phone number: ");
            studentPhoneNumber = scanner.nextLine();
        }

        System.out.print("Enter the student grade: ");
        String studentGradeString = scanner.nextLine();
        //Check if the student grade is valid
        while (!studentGradeString.matches("[0-9]+") || Integer.parseInt(studentGradeString) < 1 || Integer.parseInt(studentGradeString) > 5){
            System.out.println("Invalid student grade.");
            System.out.print("Enter the student grade: ");
            studentGradeString = scanner.nextLine();
        }
        int studentGrade = Integer.parseInt(studentGradeString);

        Student student = new Student(students.size() + 1, studentName, studentAge, studentGender, studentPhoneNumber, studentGrade);
        students.add(student);
        System.out.println("Student is successfully added.");
    }


    //Add the booking to the map
    public static void addBookingToMap(Integer LessonKey, Integer studentKey){
        bookedLessons.putIfAbsent(LessonKey, new ArrayList<>());
        bookedLessons.get(LessonKey).add(studentKey);
    }

    //View all the booking details so far
    public static void viewBookingDetails(List<Student> students, List<Lesson> lessons) {
        bookedLessons.forEach((lessonKey, studentKeys) -> {
            System.out.println("****Booking details****");
            System.out.print("Lesson Key: " + lessonKey+"\n");
            System.out.println("Student Name: ");
            studentKeys.forEach(studentKey -> {
                for (Student student : students) {
                    if (student.getStudentKey() == studentKey) {
                        System.out.println(student.getStudentName());
                    }
                }
            });
            System.out.println("Day: " + lessons.get(lessonKey - 1).getLessonDay());
            System.out.println("Time Slot: " + lessons.get(lessonKey - 1).getLessonTimeSlot());
        });
    }

    //Get the booked lessons and provide rating for the lesson and coach
    public static void attendedLessons(List<Lesson> lessons, List<Coach> coaches) {
        System.out.println("Attending Lessons....");
        System.out.print("Enter the lesson key: ");
        String lessonKeyString = scanner.nextLine();
        //Check if the lesson key is valid
        while (!lessonKeyString.matches("[0-9]+") || Integer.parseInt(lessonKeyString) > lessons.size()){
            System.out.println("Invalid lesson key. Enter a valid lesson key");
            System.out.print("Enter the lesson key: ");
            lessonKeyString = scanner.nextLine();
        }
        int lessonKey = Integer.parseInt(lessonKeyString);
        //Check if the lesson is already booked
        if (bookedLessons.get(lessonKey) == null){
            System.out.println("Lesson is not booked.");
            return;
        }

        System.out.print("Enter the lesson rating: ");
        String lessonRatingString = scanner.nextLine();
        //Check if the lesson rating is valid
        while (!lessonRatingString.equals("1") && !lessonRatingString.equals("2") && !lessonRatingString.equals("3") && !lessonRatingString.equals("4") && !lessonRatingString.equals("5")) {
            System.out.println("Invalid lesson rating. Enter a valid lesson rating");
            System.out.print("Enter the lesson rating: ");
            lessonRatingString = scanner.nextLine();
        }
        int lessonRating = Integer.parseInt(lessonRatingString);
        
        System.out.print("Enter the coach rating: ");
        String coachRatingString = scanner.nextLine();
        //Check if the coach rating is valid
        while (!coachRatingString.equals("1") && !coachRatingString.equals("2") && !coachRatingString.equals("3") && !coachRatingString.equals("4") && !coachRatingString.equals("5")) {
            System.out.println("Invalid coach rating. Enter a valid coach rating");
            System.out.print("Enter the coach rating: ");
            coachRatingString = scanner.nextLine();
        }
        int coachRating = Integer.parseInt(coachRatingString);

        //Update the lesson rating
        lessons.get(lessonKey).setLessonRating(lessonRating);
        //Update the coach rating
        for (Coach coach : coaches) {
            if (coach.getCoachKey() == lessons.get(lessonKey).getLessonCoachKey()) {
                coach.setCoachRating(coachRating);
            }
        }
        //Add the lesson to the attended lessons
        attendedLessons.putIfAbsent(lessonKey, new ArrayList<>());
        attendedLessons.get(lessonKey).addAll(bookedLessons.get(lessonKey));
        System.out.println("Lesson is successfully attended.");
    }

    //Delete the booking from the map and update the student count
    public static void deleteBookingFromMap(Integer lessonKey, List<Lesson> lessons){
        bookedLessons.get(lessonKey).forEach(studentKey -> {
            for (Lesson lesson : lessons) {
                if (lesson.getLessonKey() == lessonKey) {
                    int studentCount = lesson.getStudentsCount();
                    studentCount--;
                    lesson.setStudentsCount(studentCount);
                }
            }
        });
        //Add the lesson to the cancelled lessons
        cancelledLessons.putIfAbsent(lessonKey, new ArrayList<>());
        cancelledLessons.get(lessonKey).addAll(bookedLessons.get(lessonKey));
        bookedLessons.remove(lessonKey);
        System.out.println("Booking is successfully cancelled.");
        
    }

    //Report for the student
    public static void studentsReport(List<Student> students, List<Lesson> lessons) {
        System.out.println("****Report of students****");
        System.out.print("Enter the student name: ");
        String studentName = scanner.nextLine();
        while(studentName.isEmpty() || studentName.isBlank() || studentName.matches(".*\\d.*")){
            System.out.println("Student name cannot be invalid.");
            System.out.print("Enter the student name: ");
            studentName = scanner.nextLine();
        }
        Student selectedStudent = null;
        boolean isStudentRegistered = false;
        //Check if the student is already registered
        for (Student student : students) {
            if (student.getStudentName().equals(studentName)) {
                selectedStudent = student;
                isStudentRegistered = true;
                break;
            }
        }

        if (!isStudentRegistered) {
            addStudentToList(students, studentName);
            selectedStudent = students.get(students.size() - 1);
        }
        int bookedCount = 0;
        int cancelledCount = 0;
        int attendedCount = 0;
        for (Map.Entry<Integer, List<Integer>> entry : bookedLessons.entrySet()) {
            if (entry.getValue().contains(selectedStudent.getStudentKey())) {
                bookedCount++;
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : cancelledLessons.entrySet()) {
            if (entry.getValue().contains(selectedStudent.getStudentKey())) {
                cancelledCount++;
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : attendedLessons.entrySet()) {
            if (entry.getValue().contains(selectedStudent.getStudentKey())) {
                attendedCount++;
            }
        }
        System.out.println("Booked lessons: " + bookedCount);
        System.out.println("Cancelled lessons: " + cancelledCount);
        System.out.println("Attended lessons: " + attendedCount);
    }
    
    //Report for the coach
    public static void coachReport(List<Coach> coaches, List<Lesson> lessons) {
        System.out.println("****Report of coaches****");
        System.out.print("Enter the coach Key: ");
        String coachKeyString = scanner.nextLine();
        while(coachKeyString.isEmpty() || coachKeyString.isBlank() || !coachKeyString.matches("^[1-9]\\d*$")){
            System.out.println("Coach Key cannot be invalid.");
            System.out.print("Enter the coach key: ");
            coachKeyString = scanner.nextLine();
        }
        int coachKey = Integer.parseInt(coachKeyString);
        Coach selectedCoach = null;
        boolean isCoachRegistered = false;
        //Check if the coach is already registered
        for (Coach coach : coaches) {
            if (coach.getCoachKey() == coachKey) {
                selectedCoach = coach;
                isCoachRegistered = true;
                break;
            }
        }

        if (!isCoachRegistered) {
            System.out.println("Coach is not registered.");
            addCoachToList(coaches);
            selectedCoach = coaches.get(coaches.size() - 1);
        }
        int lessonRating = 0;
        int count = 0;
        boolean found = false;
        for (Lesson lesson : lessons) {
            if (lesson.getLessonCoachKey() == selectedCoach.getCoachKey()) {
                lessonRating += lesson.getLessonRating();
                count++;
                found = true;
            }
        }
        if(!found){
            System.out.println("No lessons found for the coach.");
            return;
        }
        lessonRating = (lessonRating / count);
        System.out.println("Rating: " + lessonRating);
    }

    //Add coach to the Coach list if the coach details are not already present
    public static void addCoachToList(List<Coach> coaches){
        System.out.print("Enter the coach name: ");
        String coachName = scanner.nextLine();
        //Check if the coach name is valid
        while(coachName.isEmpty() || coachName.isBlank() || coachName.matches(".*\\d.*") || !coachName.matches("^[a-zA-Z]*$")){
            System.out.println("Coach name cannot be invalid.");
            System.out.print("Enter the coach name: ");
            coachName = scanner.nextLine();
        }
        int coachRating = 0;
        Coach coach = new Coach(coaches.size() + 1, coachName, coachRating);
        coaches.add(coach);
        System.out.println("Coach is successfully added.");
    }

    //View timetable by day
    public static void viewTimeTableByDay(List<Lesson> lessons) {
        System.out.println("****View timetable by day****");
        System.out.print("Enter the day: ");
        String day = scanner.nextLine();
        //Check if the day is valid
        while (!day.equals("Monday") && !day.equals("Wednesday") && !day.equals("Friday") && !day.equals("Saturday")) {
            System.out.println("Invalid day. Enter a valid day (Monday, Wednesday, Friday, Saturday)");
            System.out.print("Enter the day: ");
            day = scanner.nextLine();
        }
        System.out.printf("| %-8s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", "Lesson Key", "Day", "Time Slot", "Coach Key", "Lesson Rating", "Student Count", "Grade");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (Lesson lesson : lessons) {
            if (lesson.getLessonDay().equals(day)) {
                System.out.printf("| %-10s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", lesson.getLessonKey(), lesson.getLessonDay(), lesson.getLessonTimeSlot(), lesson.getLessonCoachKey(), lesson.getLessonRating(), lesson.getStudentsCount(), lesson.getGrade());
            }
        }
    }

    //View timetable by coach
    public static void viewTimeTableByCoach(List<Lesson> lessons) {
        System.out.println("****View timetable by coach****");
        System.out.print("Enter the coach key: ");
        String coachKeyString = scanner.nextLine();
        //Check if the coach name is valid
        while(coachKeyString.isEmpty() || coachKeyString.isBlank() || !coachKeyString.matches("^[1-9]\\d*$")){
            System.out.println("Coach Key cannot be invalid.");
            System.out.print("Enter the coach key: ");
            coachKeyString = scanner.nextLine();
        }
        int coachKey = Integer.parseInt(coachKeyString);

        System.out.printf("| %-8s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", "Lesson Key", "Day", "Time Slot", "Coach Key", "Lesson Rating", "Student Count", "Grade");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for (Lesson lesson : lessons) {
            if (lesson.getLessonCoachKey() == coachKey){
                System.out.printf("| %-10s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", lesson.getLessonKey(), lesson.getLessonDay(), lesson.getLessonTimeSlot(), lesson.getLessonCoachKey(), lesson.getLessonRating(), lesson.getStudentsCount(), lesson.getGrade());
            }
        }
    }

    //View timetable by grade
    public static void viewTimeTableByGrade(List<Lesson> lessons) {
        System.out.println("****View timetable by grade****");
        System.out.print("Enter the grade: ");
        String gradeString = scanner.nextLine();
        //Check if the grade is valid
        while (!gradeString.matches("[0-9]+") || Integer.parseInt(gradeString) < 1 || Integer.parseInt(gradeString) > 5){
            System.out.println("Invalid grade.");
            System.out.print("Enter the grade: ");
            gradeString = scanner.nextLine();
        }
        int grade = Integer.parseInt(gradeString);
        System.out.printf("| %-8s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", "Lesson Key", "Day", "Time Slot", "Coach Key", "Lesson Rating", "Student Count", "Grade");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (Lesson lesson : lessons) {
            if (lesson.getGrade() == grade) {
                System.out.printf("| %-10s | %-13s | %-11s | %-10s | %-15s | %-15s | %-15s\n", lesson.getLessonKey(), lesson.getLessonDay(), lesson.getLessonTimeSlot(), lesson.getLessonCoachKey(), lesson.getLessonRating(), lesson.getStudentsCount(), lesson.getGrade());
            }
        }
    }

}