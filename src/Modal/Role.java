package Modal;

public enum Role {
    STUDENT, TEACHER, ADMINISTRATOR;

    public static Role getRoleString(String role) {
        if (role.equals("Student")) {
            return STUDENT;
        }else if (role.equals("Teacher")) {
            return TEACHER;
        }else if (role.equals("Admin")) {
            return ADMINISTRATOR;
        }
        return null;
    }
}
