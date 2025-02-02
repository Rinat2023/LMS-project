package Modal;

import java.util.List;

public class Course {
    private String title;
    private String description;
    private Teacher teacher;
    private List<Student> students;
    private List<Assignment> assignments;

    public Course(String title, String description, Teacher teacher, List<Student> students, List<Assignment> assignments) {
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.students = students;
        this.assignments = assignments;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(){

    }

    public void addTask(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", students=" + students +
                ", assignments=" + assignments +
                '}';
    }
}
