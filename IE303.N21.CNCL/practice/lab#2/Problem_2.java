import java.util.*;

public class Problem_2 {
    static class Student {
        int id;
        String name;
        double mathScore;
        double physicsScore;
        double programmingScore;
        double averageScore;

        public Student(int id, String name, double mathScore, double physicsScore, double programmingScore) {
            this.id = id;
            this.name = name;
            this.mathScore = mathScore;
            this.physicsScore = physicsScore;
            this.programmingScore = programmingScore;
            this.averageScore = (mathScore + physicsScore + programmingScore) / 3.0;
        }

        @Override
        public String toString() {
            return String.format("%-5d %-20s %7.2f %7.2f %7.2f %7.2f", id, name, mathScore, physicsScore, programmingScore, averageScore);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        System.out.println("Enter the number of student: ");
        int n = scanner.nextInt();
        for(int i = 0 ;  i < n ; ++i) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter student name: ");
            String name = scanner.next();
            System.out.print("Enter math score: ");
            double mathScore = scanner.nextDouble();
            System.out.print("Enter physics score: ");
            double physicsScore = scanner.nextDouble();
            System.out.print("Enter programming score: ");
            double programmingScore = scanner.nextDouble();
            students.add(new Student(id, name, mathScore, physicsScore, programmingScore));
        }

        System.out.println("List of students who receive scholarship:");
        for (Student student : students) {
            if (student.averageScore >= 8.0 && student.programmingScore >= 9.0) {
                System.out.println(student);
            }
        }

        System.out.println("Students with highest average score:");
        List<Student> highestAverageScoreStudents = getHighestAverageScoreStudents(students);
        for (Student student : highestAverageScoreStudents) {
            System.out.println(student);
        }

        System.out.println("Top 10 students with highest average score:");
        List<Student> top10Students = getTopNStudentsByAverageScore(students, 10);
        for (Student student : top10Students) {
            System.out.println(student);
        }
    }

    public static List<Student> getHighestAverageScoreStudents(List<Student> students) {
        double maxAverageScore = Double.MIN_VALUE;
        List<Student> highestAverageScoreStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.averageScore > maxAverageScore) {
                maxAverageScore = student.averageScore;
                highestAverageScoreStudents.clear();
                highestAverageScoreStudents.add(student);
            } else if (student.averageScore == maxAverageScore) {
                highestAverageScoreStudents.add(student);
            }
        }
        return highestAverageScoreStudents;
    }

    public static List<Student> getTopNStudentsByAverageScore(List<Student> students, int n) {
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.averageScore < s2.averageScore) return 1;
                if (s1.averageScore > s2.averageScore) return -1;
                return 0;
            }
        });
        return sortedStudents.subList(0, Math.min(n, sortedStudents.size()));
    }
}
   

