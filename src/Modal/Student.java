package Modal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Student extends User {
    private List<Course> courses;

    public Student(String name, String email, Role role, List<Course> courses) {
        super(name, email, role);
        this.courses = courses;
    }

    public void appointWithCourse(Student me, Admin admin) {
        System.out.println("Выберите курс: ");
        AtomicInteger i = new AtomicInteger(1);
        admin.getCoursesList().forEach(course -> System.out.println(i.getAndIncrement() + ". " + course.getTitle()));
        int choice = scanner.nextInt();
        courses.add(admin.getCoursesList().get(choice - 1));
        admin.getCoursesList().get(choice - 1)
                .getTeacher().getCourses().stream()
                .filter(course -> course.getTitle().equals(admin.getCoursesList().get(choice - 1).getTitle()))
                .findFirst().orElse(null).addStudent(me);
        System.out.println("Вы записались на курс ' " + admin.getCoursesList().get(choice - 1).getTitle() + " ' ");
    }

    public void executingTask(Student me, Admin admin) {
        System.out.println("Выберите курс:");
        AtomicInteger i = new AtomicInteger(1);
        courses.forEach(course -> System.out.println(i + ". " + course.getTitle()));
        int choiceCourse = scanner.nextInt();
        Course currentCourse = courses.get(choiceCourse - 1);
        System.out.println("Выберите задание:");
        AtomicInteger i2 = new AtomicInteger(1);
        currentCourse.getAssignments().forEach(assignment -> System.out.println(i2 + ". " + assignment.getTitle()));
        int choiceAssignment = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ответ на задание :");
        String answer = scanner.nextLine();
        currentCourse.getAssignments().get(choiceAssignment - 1).addStudentsAnswer(me.getName(), answer);
        Teacher currentTeacher = currentCourse.getTeacher();
        currentTeacher.getCourses().stream()
                .filter(course -> course.getTitle().equals(currentCourse.getTitle()))
                .findFirst().orElseThrow()
                .getAssignments().stream().filter(a -> currentCourse.getAssignments().get(choiceAssignment - 1).getTitle().equals(a.getTitle()))
                .findFirst().orElseThrow().addStudentsAnswer(me.getName(), answer);
        System.out.println("Ответ успешно загружен ^_^");
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
}
