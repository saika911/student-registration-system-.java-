public class Account {
    private String username;
    private String password;
    private String email;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters
}



public class Course {
    private String courseID;
    private String title;
    private int credits;

    public Course(String courseID, String title, int credits) {
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
    }

    // Getters and setters

    public String getDetails() {
        return courseID + ": " + title + " (" + credits + " credits)";
    }
}



import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentID;
    private String name;
    private Account account;
    private List<Registration> registrations = new ArrayList<>();

    public Student(String studentID, String name, Account account) {
        this.studentID = studentID;
        this.name = name;
        this.account = account;
    }

    public void viewCourses() {
        for (Registration reg : registrations) {
            System.out.println(reg.getCourse().getDetails());
        }
    }

    public void register(Course course, CourseRegistrationManager manager) {
        manager.enrollStudent(this, course);
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
    }

    // Getters and setters
}



public class Registration {
    private static int regCounter = 1;
    private String regID;
    private Student student;
    private Course course;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.regID = "REG" + (regCounter++);
    }

    public String getDetails() {
        return regID + ": " + student + " -> " + course.getDetails();
    }

    // Getters
    public Course getCourse() {
        return course;
    }
}



import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationManager {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Registration> registrations = new ArrayList<>();

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudent(Student student, Course course) {
        Registration reg = new Registration(student, course);
        registrations.add(reg);
        student.addRegistration(reg);
    }

    public void getStudentDetails() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void getCourseDetails() {
        for (Course course : courses) {
            System.out.println(course.getDetails());
        }
    }
}



public class Main {
    public static void main(String[] args) {
        CourseRegistrationManager manager = new CourseRegistrationManager();

        Account acc = new Account("john_doe", "pass123", "john@example.com");
        Student student = new Student("S1001", "John Doe", acc);

        Course course1 = new Course("CSE101", "Intro to CS", 3);
        Course course2 = new Course("MAT201", "Calculus I", 4);

        manager.registerStudent(student);
        manager.addCourse(course1);
        manager.addCourse(course2);

        student.register(course1, manager);
        student.register(course2, manager);

        student.viewCourses();
    }
}