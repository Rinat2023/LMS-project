package Modal;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<User> userList;
    private List<Course> coursesList;

    public Admin(String name, String email, Role role, List<User> userList, List<Course> coursesList) {
        super(name, email, role);
        this.userList = userList;
        this.coursesList = coursesList;
    }

    public void addUser() {
        System.out.println("Введите данные нового пользователя:");
        System.out.println("Имя: ");
        String name = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("Роль: ");
        String role = scanner.next();
        if (Role.getRoleString(role) == Role.TEACHER) {
            Teacher newTeacher = new Teacher(name, email, Role.TEACHER, new ArrayList<>());
            userList.add(newTeacher);
        } else if (Role.getRoleString(role) == Role.STUDENT) {
            Student newStudent = new Student(name, email, Role.STUDENT, new ArrayList<>());
            userList.add(newStudent);
        } else if (Role.getRoleString(role) == Role.ADMINISTRATOR) {
            Admin newAdmin = new Admin(name, email, Role.ADMINISTRATOR, new ArrayList<>(), new ArrayList<>());
            userList.add(newAdmin);
        }
        System.out.println("Пользователь успешно добавлен ^_^");
    }

    public void removeUser() {
        System.out.println("введите id пользователя:");
        int id = scanner.nextInt();
        userList.remove(id - 1);
        System.out.println("пользователь успешно удален ^_^ ");
    }

    public void createCourse() {
        System.out.println("Введите данные курса:");
        System.out.println("Название: ");
        String name = scanner.next();
        System.out.println("Описание:  ");
        String description = scanner.next();
        System.out.println("Преподаватель: ");
        String teacherName = scanner.next();
        User foundedUser = userList.stream()
                .filter(teacher -> teacher.getName().equals(teacherName)).map(teacher -> {
                    teacher.setRole(Role.TEACHER);
                    return teacher;
                })
                .findFirst().orElse(null);

        if (foundedUser == null) {
            System.out.println("Пользователь с именем " + teacherName + " не найден ");
        } else {
            Teacher newTeacher = new Teacher(foundedUser.getName() + "Teacher" , foundedUser.getEmail() + "Teacher", foundedUser.getRole(), new ArrayList<>());
            Course newCourse = new Course(name, description, newTeacher, new ArrayList<>(), new ArrayList<>());
            newTeacher.getCourses().add(newCourse);
            coursesList.add(newCourse);
            userList.add(newTeacher);
            System.out.println("Курс успешно создан  ^_^ ");
        }

    }

    public void appointTeacher() {
        System.out.println("Введите название курса: ");
        String title = scanner.next();
        System.out.println("Введите название преподавателя");
        String teacherName = scanner.next();

        Course foundedCourse = coursesList.stream().filter(course -> course.getTitle().equals(title)).findFirst().orElseThrow(null);
        User foundedUser = userList.stream().filter(teacher -> teacher.getName().equals(teacherName)).map(e -> {
            e.setRole(Role.TEACHER);
            return e;
        }).findFirst().orElse(null);

        if (foundedCourse == null || foundedUser == null) {
            System.out.println("курс или пользователь не найден!");
        } else {
            Teacher newTeacher = (Teacher) foundedUser;
            foundedCourse.setTeacher(newTeacher);
            newTeacher.getCourses().add(foundedCourse);

            System.out.println("преподаватель успешно назначен ^_^");
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }
}
