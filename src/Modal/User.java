package Modal;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

abstract public class User {
    private static int staticid;
    private int id;
    private String name;
    private String email;
    private Role role;

    static Scanner scanner = new Scanner(System.in);

    public User(String name, String email, Role role) {
        staticid++;
        this.id = staticid;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static User signIn(Admin admin) {
        System.out.println("Введите email: ");
        String email = scanner.next();
        Optional optionalUser = admin.getUserList().stream().filter(user -> user.email.equals(email)).findFirst();
        if (optionalUser.isPresent()) {
            User foundedUser = (User) optionalUser.get();
            if (((User) optionalUser.get()).getRole() == Role.ADMINISTRATOR) {
                int adminPassword = 456;
                Admin adminn = (Admin) foundedUser;
                System.out.println("Введите password: ");
                int password = scanner.nextInt();
                scanner.nextLine();
                if (adminPassword == password) {
                    return adminn;
                } else {
                    System.out.println("не правильный пароль -_-");
                }
                return null;
            } else if (((User) optionalUser.get()).getRole() == Role.TEACHER) {
                Teacher teacher = (Teacher) foundedUser;
                return teacher;
            } else {
                return foundedUser;
            }
        } else {
            System.out.println("Пользователь с таким email не найден -_-");
            return null;
        }

    }

    public static User signUp(Admin admin) {
        System.out.println("Введите имя: ");
        String name = scanner.next();
        System.out.println("Введите email: ");
        String email = scanner.next();
        User newUser = new Student(name, email, Role.STUDENT, new ArrayList<>());
        admin.getUserList().add(newUser);
        System.out.println("Вы успешно зарегистрировались ^_^");
        return newUser;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
