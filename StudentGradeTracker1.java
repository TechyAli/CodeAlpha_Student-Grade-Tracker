import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker1{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Double> studentMarks = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add Student and Mark");
            System.out.println("2. Show Summary Report");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String optionLine = scanner.nextLine().trim();
            if (optionLine.isEmpty()) {
                System.out.println("Please enter a valid option.");
                continue;
            }

            int option;
            try {
                option = Integer.parseInt(optionLine);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-3).");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty. Aborting add.");
                        break;
                    }

                    System.out.print("Enter student mark (0 - 100): ");
                    String markLine = scanner.nextLine().trim();
                    double mark;
                    try {
                        mark = Double.parseDouble(markLine);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark. Please enter a numeric value.");
                        break;
                    }

                    if (mark < 0 || mark > 100) {
                        System.out.println("Mark must be between 0 and 100.");
                        break;
                    }

                    studentNames.add(name);
                    studentMarks.add(mark);
                    System.out.println("Added: " + name + " -> " + mark);
                    break;

                case 2:
                    if (studentNames.isEmpty()) {
                        System.out.println("No students added yet.");
                        break;
                    }

                    int count = studentNames.size();
                    double sum = 0.0;
                    double highest = Double.NEGATIVE_INFINITY;
                    double lowest = Double.POSITIVE_INFINITY;
                    int highestIndex = -1;
                    int lowestIndex = -1;

                    for (int i = 0; i < count; i++) {
                        double m = studentMarks.get(i);
                        sum += m;
                        if (m > highest) {
                            highest = m;
                            highestIndex = i;
                        }
                        if (m < lowest) {
                            lowest = m;
                            lowestIndex = i;
                        }
                    }

                    double average = sum / count;

                    System.out.println("\n--- Summary Report ---");
                    System.out.println("Total students: " + count);
                    System.out.printf("Average mark: %.2f%n", average);
                    System.out.printf("Highest mark: %.2f ( %s )%n", highest, studentNames.get(highestIndex));
                    System.out.printf("Lowest mark:  %.2f ( %s )%n", lowest, studentNames.get(lowestIndex));

                    System.out.println("\n--- Student Marks & Grades ---");
                    for (int i = 0; i < count; i++) {
                        double m = studentMarks.get(i);
                        String grade = convertToGrade(m);
                        System.out.printf("%d. %s : %.2f -> Grade %s%n", i + 1, studentNames.get(i), m, grade);
                    }
                    break;

                case 3:
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Please choose a valid option (1-3).");
            }
        }
    }

    private static String convertToGrade(double mark) {
        if (mark >= 90) return "A";
        if (mark >= 80) return "B";
        if (mark >= 70) return "C";
        if (mark >= 60) return "D";
        if (mark >= 50) return "E";
        return "F";
    }
}
