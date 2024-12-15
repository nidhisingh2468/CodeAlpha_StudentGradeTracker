import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();

        System.out.println("=== Welcome to the Student Grade Tracker ===");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a student's grade");
            System.out.println("2. Calculate average grade");
            System.out.println("3. Find highest grade");
            System.out.println("4. Find lowest grade");
            System.out.println("5. Display all grades");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add a grade
                    System.out.print("Enter the student's grade (0-100): ");
                    double grade = scanner.nextDouble();
                    if (grade >= 0 && grade <= 100) {
                        grades.add(grade);
                        System.out.println("Grade added successfully!");
                    } else {
                        System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                    }
                    break;

                case 2: // Calculate average grade
                    if (grades.isEmpty()) {
                        System.out.println("No grades available to calculate average.");
                    } else {
                        double total = 0;
                        for (double g : grades) {
                            total += g;
                        }
                        double average = total / grades.size();
                        System.out.printf("The average grade is: %.2f\n", average);
                    }
                    break;

                case 3: // Find highest grade
                    if (grades.isEmpty()) {
                        System.out.println("No grades available to find the highest.");
                    } else {
                        double highest = grades.get(0);
                        for (double g : grades) {
                            if (g > highest) {
                                highest = g;
                            }
                        }
                        System.out.printf("The highest grade is: %.2f\n", highest);
                    }
                    break;

                case 4: // Find lowest grade
                    if (grades.isEmpty()) {
                        System.out.println("No grades available to find the lowest.");
                    } else {
                        double lowest = grades.get(0);
                        for (double g : grades) {
                            if (g < lowest) {
                                lowest = g;
                            }
                        }
                        System.out.printf("The lowest grade is: %.2f\n", lowest);
                    }
                    break;

                case 5: // Display all grades
                    if (grades.isEmpty()) {
                        System.out.println("No grades have been entered yet.");
                    } else {
                        System.out.println("All grades:");
                        for (int i = 0; i < grades.size(); i++) {
                            System.out.printf("Student %d: %.2f\n", i + 1, grades.get(i));
                        }
                    }
                    break;

                case 6: // Exit
                    System.out.println("Thank you for using the Student Grade Tracker. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
