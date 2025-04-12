
import java.io.File;
import java.util.*;

public class GradeAnalyzer {
    static class Student {
        String name;
        Map<String, Integer> grades;

        Student(String name) {
            this.name = name;
            grades = new HashMap<>();
        }

        void addGrade(String subject, int grade) {
            grades.put(subject, grade);
        }

        int getGrade(String subject) {
            return grades.getOrDefault(subject, 0);
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        try {

            Scanner scan = new Scanner(System.in);
            String in = scan.nextLine();
            scan.close();
            
            File file = new File("cases/" + in);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] words = line.split("\\s+");
                if (words.length > 0){
                    String name = words[0];
                    Student s = new Student(name);
                    for (int i = 1; i < words.length-1; i +=2){
                        String subject = words[i];
                        int grade = Integer.parseInt(words[i+1]);
                        s.addGrade(subject, grade);
                    }
                    students.add(s);
                }
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        printSubjectStats(students, "Math");
        printSubjectStats(students, "English");
    }

    public static void printSubjectStats(List<Student> students, String subject) {
        int total = 0;
        String topStudent = "";
        int max = Integer.MIN_VALUE;

        for (Student s : students) {
            int grade = s.getGrade(subject);
            total += grade;
            if (grade > max) {
                max = grade;
                topStudent = s.name;
            }
        }

        double average = total / students.size();

        System.out.println("Subject: " + subject);
        System.out.println("Average Grade: " + average);
        System.out.println("Top Student: " + topStudent + " (" + max + ")");
        System.out.println();
    }
}
