package Modal;

import java.util.Map;

public class Assignment {
    private String title;
    private String description;
    private Map<String, String> studentsAnswers;
    private Map<String, Integer> grades;

    public Assignment(String title, String description, Map<String, String> studentsAnswers, Map<String, Integer> grades) {
        this.title = title;
        this.description = description;
        this.studentsAnswers = studentsAnswers;
        this.grades = grades;
    }

    public void addStudentsAnswer(String studentName, String answer) {
        studentsAnswers.put(studentName, answer);
    }

    public void givingGrades(String studentName, Integer grade) {
        grades.put(studentName, grade);
    }

    public String getTitle() {
        return title;
    }

    public Map<String, String> getStudentsAnswers() {
        return studentsAnswers;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", studentsAnswers=" + studentsAnswers +
                ", grades=" + grades +
                '}';
    }
}
