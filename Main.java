import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class Main {
    private JTextField patientNameField;
    private JTextField ownerNameField;
    private JTextField emailField;
    private JLabel messageLabel;
    private JRadioButton vet1Button, vet2Button, vet3Button;
    private ButtonGroup vetGroup;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Veterinary Clinic Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(7, 2, 10, 10));

        // Labels and text fields
        frame.add(new JLabel("Patient Name:"));
        patientNameField = new JTextField();
        frame.add(patientNameField);

        frame.add(new JLabel("Owner Name:"));
        ownerNameField = new JTextField();
        frame.add(ownerNameField);

        frame.add(new JLabel("Email Address:"));
        emailField = new JTextField();
        frame.add(emailField);

        // Radio Buttons
        vet1Button = new JRadioButton("Dr. Smith", true);
        vet2Button = new JRadioButton("Dr. Johnson");
        vet3Button = new JRadioButton("Dr. Brown");
        vetGroup = new ButtonGroup();
        vetGroup.add(vet1Button);
        vetGroup.add(vet2Button);
        vetGroup.add(vet3Button);
        frame.add(vet1Button);
        frame.add(vet2Button);
        frame.add(vet3Button);

        // Message Label
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        frame.add(messageLabel);

        // Buttons
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::registerPatient);
        frame.add(registerButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearForm());
        frame.add(clearButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        frame.add(exitButton);

        frame.setVisible(true);
    }

    private void registerPatient(ActionEvent e) {
        String patientName = patientNameField.getText().trim();
        String ownerName = ownerNameField.getText().trim();
        String email = emailField.getText().trim();

        // Validation
        if (patientName.isEmpty()) {
            showMessage("Patient name cannot be empty!");
            return;
        }
        if (ownerName.isEmpty()) {
            showMessage("Owner name cannot be empty!");
            return;
        }
        if (!Validator.isValidEmail(email)) {
            showMessage("Invalid email address!");
            return;
        }

        // Get selected veterinarian
        String vet = vet1Button.isSelected() ? "Dr. Smith" :
                vet2Button.isSelected() ? "Dr. Johnson" : "Dr. Brown";

        // Get current date
        String currentDate = new Date().toString();

        // Save data to file
        String data = "**Patient Registration Document**\n" +
                "Patient Name: " + patientName + "\n" +
                "Owner Name: " + ownerName + "\n" +
                "Email Address: " + email + "\n" +
                "Assigned Vet: " + vet + "\n" +
                "Date: " + currentDate + "\n";

        try {
            FileManager.saveToFile(data);
            showMessage("File saved successfully!");
        } catch (IOException ex) {
            showMessage("Error saving file: " + ex.getMessage());
        }
    }

    private void clearForm() {
        patientNameField.setText("");
        ownerNameField.setText("");
        emailField.setText("");
        vet1Button.setSelected(true);
        showMessage(" ");
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
    }
}
