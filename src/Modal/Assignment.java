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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getStudentsAnswers() {
        return studentsAnswers;
    }

    public void setStudentsAnswers(Map<String, String> studentsAnswers) {
        this.studentsAnswers = studentsAnswers;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
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
