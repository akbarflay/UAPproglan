package main.nutribalance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModernHealthAppSwing {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ModernHealthAppSwing() {
        initialize();
    }

    private void initialize() {
        // Main frame setup
        frame = new JFrame("Health App Mockup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 650);
        frame.setLocationRelativeTo(null);

        // CardLayout for switching between screens
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add screens
        cardPanel.add(createLoginPanel(), "Login");
        cardPanel.add(createHomePanel(), "Home");
        cardPanel.add(createSpecialistDoctorPanel(), "SpecialistDoctor");
        cardPanel.add(createNutriCalculatePanel(), "NutriCalculate");
        cardPanel.add(createLiveChatPanel(), "LiveChat");

        frame.getContentPane().add(cardPanel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.decode("#F7F7F7"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Welcome to HealthApp", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));

        JTextField usernameField = new JTextField(15);
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.decode("#39A2DB"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(e -> switchToScreen("Home"));

        // Add components to layout with proper spacing
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(header, gbc);

        gbc.gridy = 1; gbc.gridwidth = 2;
        loginPanel.add(usernameField, gbc);

        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        gbc.gridy = 3; gbc.gridwidth = 1;
        loginPanel.add(loginButton, gbc);

        return loginPanel;
    }


    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        homePanel.setBackground(Color.decode("#F7F7F7"));
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create menu buttons with consistent size and padding
        JButton specialistDoctorButton = createMenuButton("💼 Specialist Doctor", "#39A2DB");
        specialistDoctorButton.addActionListener(e -> switchToScreen("SpecialistDoctor"));

        JButton nutriCalculateButton = createMenuButton("🥗 Nutri Calculate", "#A29BFE");
        nutriCalculateButton.addActionListener(e -> switchToScreen("NutriCalculate"));

//        JButton labResultsButton = createMenuButton("📋 Lab Results", "#FDCB6E");
//        labResultsButton.addActionListener(e -> switchToScreen("LabResults"));

        JButton liveChatButton = createMenuButton("💬 Live Chat", "#74B9FF");
        liveChatButton.addActionListener(e -> switchToScreen("LiveChat"));

        // Add buttons with vertical spacing
        homePanel.add(Box.createVerticalStrut(20));
        homePanel.add(specialistDoctorButton);
        homePanel.add(Box.createVerticalStrut(10));
        homePanel.add(nutriCalculateButton);
//        homePanel.add(Box.createVerticalStrut(10));
//        homePanel.add(labResultsButton);
        homePanel.add(Box.createVerticalStrut(10));
        homePanel.add(liveChatButton);

        return homePanel;
    }


    private JPanel createSpecialistDoctorPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Specialist Doctor", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(header);
        panel.add(Box.createVerticalStrut(10));

        // List of doctors
        DefaultListModel<String> doctorListModel = new DefaultListModel<>();
        JList<String> doctorList = new JList<>(doctorListModel);
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane doctorScrollPane = new JScrollPane(doctorList);
        panel.add(doctorScrollPane);

        // Buttons for CRUD operations
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Doctor");
        addButton.addActionListener(e -> showAddDoctorDialog(doctorListModel));

        JButton editButton = new JButton("Edit Doctor");
        editButton.addActionListener(e -> {
            int selectedIndex = doctorList.getSelectedIndex();
            if (selectedIndex != -1) {
                showEditDoctorDialog(doctorListModel, selectedIndex, doctorList);
            }
        });

        JButton deleteButton = new JButton("Delete Doctor");
        deleteButton.addActionListener(e -> {
            int selectedIndex = doctorList.getSelectedIndex();
            if (selectedIndex != -1) {
                doctorListModel.remove(selectedIndex);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel);

        // Back button
        JButton backButton = createBackButton();
        backButton.addActionListener(e -> switchToScreen("Home"));
        panel.add(backButton);

        return panel;
    }

    // Dialog to add a new doctor
    private void showAddDoctorDialog(DefaultListModel<String> doctorListModel) {
        JTextField nameField = new JTextField(15);
        JTextField specialtyField = new JTextField(15);
        JTextField ratingField = new JTextField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Doctor Name"));
        panel.add(nameField);
        panel.add(new JLabel("Specialty"));
        panel.add(specialtyField);
        panel.add(new JLabel("Rating"));
        panel.add(ratingField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Add New Doctor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String specialty = specialtyField.getText();
            String rating = ratingField.getText();
            if (!name.isEmpty() && !specialty.isEmpty() && !rating.isEmpty()) {
                doctorListModel.addElement(name + " - " + specialty + " - Rating: " + rating);
            } else {
                JOptionPane.showMessageDialog(null, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Dialog to edit an existing doctor
    private void showEditDoctorDialog(DefaultListModel<String> doctorListModel, int selectedIndex, JList<String> doctorList) {
        String doctorDetails = doctorListModel.get(selectedIndex);
        String[] parts = doctorDetails.split(" - ");
        String currentName = parts[0];
        String currentSpecialty = parts[1];
        String currentRating = parts[2].replace("Rating: ", "");

        JTextField nameField = new JTextField(currentName, 15);
        JTextField specialtyField = new JTextField(currentSpecialty, 15);
        JTextField ratingField = new JTextField(currentRating, 5);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Doctor Name"));
        panel.add(nameField);
        panel.add(new JLabel("Specialty"));
        panel.add(specialtyField);
        panel.add(new JLabel("Rating"));
        panel.add(ratingField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Edit Doctor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String specialty = specialtyField.getText();
            String rating = ratingField.getText();
            if (!name.isEmpty() && !specialty.isEmpty() && !rating.isEmpty()) {
                doctorListModel.set(selectedIndex, name + " - " + specialty + " - Rating: " + rating);
            } else {
                JOptionPane.showMessageDialog(null, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private JPanel createNutriCalculatePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Nutri Calculate", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(header);
        panel.add(Box.createVerticalStrut(10));

        JComboBox<String> foodSelector = new JComboBox<>(new String[]{"Nasi Putih", "Ayam Goreng", "Ikan Bakar", "Sayur Bayam"});
        JTextField weightField = new JTextField();

        JButton calculateButton = new JButton("Hitung");
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

        calculateButton.addActionListener(e -> {
            String food = (String) foodSelector.getSelectedItem();
            try {
                int weight = Integer.parseInt(weightField.getText());
                resultLabel.setText(calculateNutrition(food, weight));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid weight input");
            }
        });

        panel.add(new JLabel("Pilih Makanan"));
        panel.add(foodSelector);
        panel.add(new JLabel("Berat (gram)"));
        panel.add(weightField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        JButton backButton = createBackButton();
        backButton.addActionListener(e -> switchToScreen("Home"));
        panel.add(backButton);

        return panel;
    }


    private JPanel createLiveChatPanel() {
        JPanel liveChatPanel = new JPanel();
        liveChatPanel.setBackground(Color.WHITE);
        liveChatPanel.setLayout(new BoxLayout(liveChatPanel, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Live Chat", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        liveChatPanel.add(header);

        // Create the chat area to display messages
        JTextArea chatArea = new JTextArea(10, 30);
        chatArea.setEditable(false);  // Make the chat area non-editable
        chatArea.setLineWrap(true);  // Allow line wrapping
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        liveChatPanel.add(scrollPane);

        // Create a message input field
        JTextField messageField = new JTextField(30);

        // Create the send button
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                // Append the message to the chat area
                chatArea.append("You: " + message + "\n");
                messageField.setText("");  // Clear the message input field after sending
                // Optionally, simulate a reply after a delay
                simulateBotReply(chatArea);
            }
        });

        // Add components for user input and send button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(messageField);
        inputPanel.add(sendButton);
        liveChatPanel.add(inputPanel);

        return liveChatPanel;
    }

    private void simulateBotReply(JTextArea chatArea) {
        // Simulate a bot response with a slight delay
        Timer timer = new Timer(1000, e -> chatArea.append("Bot: I'm here to help!\n"));
        timer.setRepeats(false);
        timer.start();
    }


    private JPanel createLabResultsPanel() {
        JPanel labResultsPanel = new JPanel();
        labResultsPanel.setBackground(Color.WHITE);
        labResultsPanel.setLayout(new BoxLayout(labResultsPanel, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Lab Results", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        labResultsPanel.add(header);

        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        labResultsPanel.add(scrollPane);

        return labResultsPanel;
    }

    private JButton createMenuButton(String text, String color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode(color));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 50));
        return button;
    }

    private JButton createBackButton() {
        JButton button = new JButton("Back");
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(100, 30));
        return button;
    }

    private JPanel createDoctorCard(String name, String specialty, String rating) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel specialtyLabel = new JLabel(specialty);
        JLabel ratingLabel = new JLabel("⭐ " + rating);

        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.add(nameLabel);
        details.add(specialtyLabel);
        details.add(ratingLabel);

        card.add(details, BorderLayout.CENTER);

        return card;
    }

    private String calculateNutrition(String food, int weight) {
        double factor = weight / 100.0;
        int calories = 0, protein = 0, fat = 0, carbs = 0;

        switch (food) {
            case "Nasi Putih": calories = 130; protein = 2; fat = 0; carbs = 28; break;
            case "Ayam Goreng": calories = 250; protein = 25; fat = 15; carbs = 10; break;
            case "Ikan Bakar": calories = 200; protein = 20; fat = 10; carbs = 0; break;
            case "Sayur Bayam": calories = 23; protein = 3; fat = 0; carbs = 4; break;
        }

        return String.format("Kalori: %.1f kcal | Protein: %.1f g | Lemak: %.1f g | Karbohidrat: %.1f g",
                calories * factor, protein * factor, fat * factor, carbs * factor);
    }

    private void switchToScreen(String screenName) {
        cardLayout.show(cardPanel, screenName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ModernHealthAppSwing::new);
    }
}
