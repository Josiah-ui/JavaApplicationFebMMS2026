import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * AppointmentForm
 * A Swing GUI form that collects a person's details and appends them
 * to a plain text file (appointments.txt) that opens in Notepad++.
 */
public class AppointmentForm extends JFrame {

    private static final String FILE_NAME = "appointments.txt";
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private final JTextField nameField = new JTextField(20);
    private final JRadioButton maleButton = new JRadioButton("Male");
    private final JRadioButton femaleButton = new JRadioButton("Female");
    private final JRadioButton otherButton = new JRadioButton("Other");
    private final ButtonGroup genderGroup = new ButtonGroup();
    private final JTextField phoneField = new JTextField(20);
    private final JTextField dobField = new JTextField(20);
    private final JTextField appointmentField = new JTextField(20);

    public AppointmentForm() {
        super("Appointment Registration Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Group radio buttons so only one can be selected
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(maleButton);
        genderPanel.add(Box.createHorizontalStrut(10));
        genderPanel.add(femaleButton);
        genderPanel.add(Box.createHorizontalStrut(10));
        genderPanel.add(otherButton);

        // Form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        addRow(formPanel, gbc, 0, "Full Name:", nameField);
        addRow(formPanel, gbc, 1, "Gender:", genderPanel);
        addRow(formPanel, gbc, 2, "Phone Number:", phoneField);
        addRow(formPanel, gbc, 3, "Date of Birth (dd/MM/yyyy):", dobField);
        addRow(formPanel, gbc, 4, "Appointment Date (dd/MM/yyyy):", appointmentField);

        // Buttons
        JButton saveButton = new JButton("Save");
        JButton clearButton = new JButton("Clear");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        saveButton.addActionListener(e -> saveData());
        clearButton.addActionListener(e -> clearForm());

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // centre on screen
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, Component field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private String getGender() {
        if (maleButton.isSelected()) return "Male";
        if (femaleButton.isSelected()) return "Female";
        if (otherButton.isSelected()) return "Other";
        return "";
    }

    private void saveData() {
        String name = nameField.getText().trim();
        String gender = getGender();
        String phone = phoneField.getText().trim();
        String dobText = dobField.getText().trim();
        String appointmentText = appointmentField.getText().trim();

        // ---- Validation ----
        if (name.isEmpty()) {
            showError("Please enter the full name.");
            return;
        }
        if (!name.matches("[\\p{L} .'-]{2,}")) {
            showError("Full Name may only contain letters, spaces, hyphens, apostrophes and full stops.");
            return;
        }
        if (gender.isEmpty()) {
            showError("Please select a gender.");
            return;
        }
        if (!phone.replace(" ", "").matches("\\+?[0-9]{7,15}")) {
            showError("Please enter a valid phone number (7-15 digits, e.g. 08012345678).");
            return;
        }

        LocalDate dob;
        LocalDate appointment;
        try {
            dob = LocalDate.parse(dobText, DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            showError("Date of Birth must be a valid date in the format dd/MM/yyyy.");
            return;
        }
        try {
            appointment = LocalDate.parse(appointmentText, DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            showError("Appointment Date must be a valid date in the format dd/MM/yyyy.");
            return;
        }

        if (!dob.isBefore(LocalDate.now())) {
            showError("Date of Birth must be in the past.");
            return;
        }
        if (appointment.isBefore(LocalDate.now())) {
            showError("Appointment Date cannot be in the past.");
            return;
        }

        // ---- Write to the text file (append mode) ----
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(FILE_NAME, true), StandardCharsets.UTF_8)))) {

            writer.println("Saved On         : " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            writer.println("Full Name        : " + name);
            writer.println("Gender           : " + gender);
            writer.println("Phone Number     : " + phone);
            writer.println("Date of Birth    : " + dob.format(DATE_FORMAT));
            writer.println("Appointment Date : " + appointment.format(DATE_FORMAT));
            writer.println("------------------------------------------");

            JOptionPane.showMessageDialog(this,
                    "Information saved successfully to:\n" + new File(FILE_NAME).getAbsolutePath(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();

        } catch (IOException ex) {
            showError("Could not save the file: " + ex.getMessage());
        }
    }

    private void clearForm() {
        nameField.setText("");
        phoneField.setText("");
        dobField.setText("");
        appointmentField.setText("");
        genderGroup.clearSelection();
        nameField.requestFocusInWindow();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppointmentForm().setVisible(true));
    }
}