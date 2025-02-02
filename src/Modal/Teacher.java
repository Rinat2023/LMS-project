package Modal;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Teacher extends User {
    private List<Course> courses;

    public Teacher(String name, String email, Role role, List<Course> courses) {
        super(name, email, role);
        this.courses = courses;
    }

    public void createCourse(Teacher me, Admin admin) {
        System.out.println("введите название курса:");
        String title = scanner.next();
        System.out.println("введите описание для курса:");
        String desctription = scanner.next();
        Course newcourse = new Course(title, desctription, me, new ArrayList<>(), new ArrayList<>());
        courses.add(newcourse);
        admin.getCoursesList().add(newcourse);
        System.out.println("курс успешно создан ^_^");
    }

    public void addTask() {
        System.out.println("Выберите курс: ");
        AtomicInteger index = new AtomicInteger(1);
        courses.forEach(course -> System.out.println(index.getAndIncrement() + ". " + course.getTitle()));
        int choice = scanner.nextInt();
        Course course = courses.get(choice - 1);

        System.out.println("Введите название задания: ");
        String title = scanner.next();
        System.out.println("Введите описание задания:");
        String description = scanner.next();
        Assignment newAssignment = new Assignment(title, description, new TreeMap<>(), new TreeMap<>());
        course.getAssignments().add(newAssignment);
        System.out.println("Задание успешно добавлено!");
    }

    public void checkTask() {
        courses.forEach(course -> {
            System.out.println("Курс " + course.getTitle());
            course.getAssignments().forEach(assignment -> {
                System.out.println("Задание " + assignment.getTitle());
                AtomicInteger i = new AtomicInteger(1);
                System.out.println("Ответы студентов: ");
                assignment.getStudentsAnswers().forEach((student, answer) -> {
                    System.out.println(i.getAndIncrement() + ". " + student + " [ " + answer + " ]");
                });
            });
        });
    }

    public void rate() {
        System.out.println("Выберите курс:");
        AtomicInteger a = new AtomicInteger(1);
        courses.forEach(course -> System.out.println(a.getAndIncrement() + ". " + course.getTitle()));
        int courseChoice = scanner.nextInt();

        Course selectedCourse = courses.get(courseChoice - 1);

        System.out.println("Выберите студента:");
        AtomicInteger i = new AtomicInteger(1);
        selectedCourse.getStudents().forEach(student -> System.out.println(i.getAndIncrement() + ". " + student.getName()));
        int studentChoice = scanner.nextInt();

        Student selectedStudent = selectedCourse.getStudents().get(studentChoice - 1);

        System.out.println("Выберите задание: ");
        AtomicInteger j = new AtomicInteger(1);
        selectedCourse.getAssignments().forEach(assignment -> System.out.println(j.getAndIncrement() + ". " + assignment.getTitle()));
        int assignmentChoice = scanner.nextInt();

        Assignment selectedAssignment = selectedCourse.getAssignments().get(assignmentChoice - 1);

        System.out.println("Введите оценку: ");
        int grade = scanner.nextInt();

        // Ставим оценку студенту в курсе
        selectedAssignment.givingGrades(selectedStudent.getName(), grade);

        // Ставим оценку в списке заданий студента
        Assignment studentAssignment = selectedStudent.getCourses().stream()
                .filter(course -> course.getTitle().equals(selectedCourse.getTitle()))
                .findFirst().orElseThrow()
                .getAssignments().get(assignmentChoice - 1);

        studentAssignment.givingGrades(selectedStudent.getName(), grade);

        System.out.println("Оценка успешно поставлена!");
    }

    public void viewReports() {
        courses.forEach(course -> {
            System.out.println("Курс: " + course.getTitle());
            course.getAssignments().forEach(assignment -> {
                System.out.println("  Задание: " + assignment.getTitle());
                assignment.getGrades().forEach((studentName, grade) ->
                        System.out.println("    Студент: " + studentName + " - Оценка: " + grade)
                );
            });
        });
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
