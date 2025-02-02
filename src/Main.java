import Modal.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin", Role.ADMINISTRATOR, new ArrayList<>(), new ArrayList<>());
        admin.getUserList().add(admin);

        int choice = 0;
        while (choice != 3) {
            mainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    User user = User.signIn(admin);
                    if (user.getRole() == Role.ADMINISTRATOR) {
                        adminMethod(admin);
                    } else if (user.getRole() == Role.TEACHER) {
                        teacherMethod((Teacher) user, admin);
                    } else {
                        studentMethod((Student) user, admin);
                    }
                    break;
                }
                case 2: {
                    User user = User.signUp(admin);
                    studentMethod((Student) user, admin);
                    break;
                }
                case 3: {
                    break;
                }
            }

        }

    }

    public static void mainMenu() {
        System.out.println("""
                Добро пожаловать в систему управления обучением (LMS)
                1.	Вход
                2.	Регистрация
                3.	Выход
                """);
    }

    public static void adminMenu(int whichMenu) {
        if (whichMenu == 1) {
            System.out.println("""
                    1. Управление пользователями:
                    2. Управление курсами:
                    3. Назад
                    """);
        } else if (whichMenu == 2) {
            System.out.println("""
                    1.	Добавить пользователя.
                    2.	Удалить пользователя.
                    3.	Просмотреть список пользователей.
                    4.  Назад
                    """);
        } else if (whichMenu == 3) {
            System.out.println("""
                    1.	Создать курс.
                    2.	Назначить преподавателя.
                    3.	Просмотреть список курсов.
                    4.  Назад
                    """);
        }
    }

    public static void adminMethod(Admin me) {
        int choice = 0;
        while (choice != 3) {
            adminMenu(1);
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Управление пользователями:");
                    adminMenu(2);
                    int choice2 = scanner.nextInt();
                    switch (choice2) {
                        case 1: {
                            me.addUser();
                            break;
                        }
                        case 2: {
                            me.removeUser();
                            break;
                        }
                        case 3: {
                            me.getUserList().stream().forEach(user -> System.out.println(user.getId() + ". " + user.getName()));
                            break;
                        }
                        case 4: {
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    adminMenu(3);
                    int choice3 = scanner.nextInt();
                    switch (choice3) {
                        case 1: {
                            me.createCourse();
                            break;
                        }
                        case 2: {
                            me.appointTeacher();
                            break;
                        }
                        case 3: {
                            me.getCoursesList().stream().forEach(System.out::println);
                            break;
                        }
                        case 4: {
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    break;
                }
            }
        }
    }

    public static void teacherMenu(int whichMenu) {
        switch (whichMenu) {
            case 1: {
                System.out.println("""
                        1.	Управление курсами:
                        2.	Проверка заданий:
                        3.	Отчеты:
                        4.  Выйти
                        """);
                break;
            }
            case 2: {
                System.out.println("""
                        1.	Создать курс.
                        2.	Добавить задание.
                        3.	Просмотреть список студентов на курсе.
                        4.  Назад
                        """);
                break;
            }
            case 3: {
                System.out.println("""
                        1.	Посмотреть работы студентов.
                        2.	Выставить оценку.
                        3.  Назад
                        """);
                break;
            }
        }
    }

    public static void teacherMethod(Teacher me, Admin admin) {
        int choice = 0;
        while (choice != 4) {
            teacherMenu(1);
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    teacherMenu(2);
                    int newChoice = scanner.nextInt();
                    if (newChoice == 1) {
                        me.createCourse(me, admin);
                    } else if (newChoice == 2) {
                        me.addTask();
                    } else if (newChoice == 3) {
                        me.getCourses().forEach(course -> course.getStudents().forEach(student -> System.out.println(student.getName())));
                    } else {
                        break;
                    }
                    break;
                }
                case 2: {
                    teacherMenu(3);
                    int newChoice = scanner.nextInt();
                    if (newChoice == 1) {
                        me.checkTask();
                    } else if (newChoice == 2) {
                        me.rate();
                    } else {
                        break;
                    }
                    break;
                }
                case 3: {
                    me.viewReports();
                    break;
                }
                case 4: {
                    break;
                }
            }
        }
    }

    public static void studentMenu() {
        System.out.println("""
                1.	Просмотр доступных курсов
                2.	Запись на курс
                3.	Выполнение задания
                4.	Просмотреть отчеты
                5.  Назад
                """);
    }

    public static void studentMethod(Student me, Admin admin) {
        int choice = 0;
        while (choice != 5) {
            studentMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    AtomicInteger i = new AtomicInteger(1);
                    admin.getCoursesList().forEach(course -> System.out.println(i.getAndIncrement() + ". " + course.getTitle()));
                    break;
                }
                case 2: {
                    me.appointWithCourse(me, admin);
                    break;
                }
                case 3: {
                    me.executingTask(me, admin);
                    break;
                }
                case 4: {
                    me.viewReports();
                    break;
                }
                case 5: {

                    break;
                }
            }
        }
    }


}