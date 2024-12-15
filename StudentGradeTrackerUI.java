import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradeTrackerUI {
    private JFrame frame;
    private JTextField gradeInput;
    private JTextArea gradeDisplay;
    private ArrayList<Double> grades;

    public StudentGradeTrackerUI() {
        grades = new ArrayList<>();
        createUI();
    }

    private void createUI() {
        // Initialize frame
        frame = new JFrame("Student Grade Tracker");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Student Grade Tracker", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Grade input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        gradeInput = new JTextField(10);
        JButton addButton = new JButton("Add Grade");
        inputPanel.add(new JLabel("Enter Grade:"));
        inputPanel.add(gradeInput);
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.CENTER);

        // Grade display area
        gradeDisplay = new JTextArea(15, 40);
        gradeDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gradeDisplay);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGrade();
            }
        });

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem averageItem = new JMenuItem("Calculate Average");
        JMenuItem highestItem = new JMenuItem("Find Highest Grade");
        JMenuItem lowestItem = new JMenuItem("Find Lowest Grade");
        JMenuItem exitItem = new JMenuItem("Exit");

        menu.add(averageItem);
        menu.add(highestItem);
        menu.add(lowestItem);
        menu.addSeparator();
        menu.add(exitItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // Menu item actions
        averageItem.addActionListener(e -> calculateAverage());
        highestItem.addActionListener(e -> findHighestGrade());
        lowestItem.addActionListener(e -> findLowestGrade());
        exitItem.addActionListener(e -> System.exit(0));

        // Make frame visible
        frame.setVisible(true);
    }

    private void addGrade() {
        try {
            double grade = Double.parseDouble(gradeInput.getText());
            if (grade >= 0 && grade <= 100) {
                grades.add(grade);
                gradeDisplay.append(String.format("Grade %.2f added.\n", grade));
                gradeInput.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a valid grade (0-100).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateAverage() {
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No grades available to calculate average.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        double average = total / grades.size();
        JOptionPane.showMessageDialog(frame, String.format("The average grade is: %.2f", average), "Average Grade", JOptionPane.INFORMATION_MESSAGE);
    }

    private void findHighestGrade() {
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No grades available to find the highest.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        double highest = grades.stream().max(Double::compareTo).orElse(0.0);
        JOptionPane.showMessageDialog(frame, String.format("The highest grade is: %.2f", highest), "Highest Grade", JOptionPane.INFORMATION_MESSAGE);
    }

    private void findLowestGrade() {
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No grades available to find the lowest.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        double lowest = grades.stream().min(Double::compareTo).orElse(0.0);
        JOptionPane.showMessageDialog(frame, String.format("The lowest grade is: %.2f", lowest), "Lowest Grade", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeTrackerUI::new);
    }
}
