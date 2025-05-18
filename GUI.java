package org.example;
import com.google.cloud.firestore.Firestore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField ID, Name, Position, Salary;
    private JTable attendanceTable;
    private DefaultTableModel attendanceTableModel;

    public GUI() {
        setTitle("Employee Management");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Employee Management Tab
        JPanel employeePanel = new JPanel(new BorderLayout());

        // Top Panel - Form
        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 10));
        ID = new JTextField();
        Name = new JTextField();
        Position = new JTextField();
        Salary = new JTextField();


        panel.add(new JLabel("Employee ID:"));
        panel.add(new JLabel("Name:"));
        panel.add(new JLabel("Position:"));
        panel.add(new JLabel("Salary:"));
        panel.add(ID);
        panel.add(Name);
        panel.add(Position);
        panel.add(Salary);
        employeePanel.add(formPanel, BorderLayout.NORTH);

        add(panel, BorderLayout.NORTH);


        // Center Panel - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        employeePanel.add(scrollPane, BorderLayout.CENTER);


        // Bottom Panel - Buttons
        JPanel buttonPanel = new JPanel();


        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");


        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        employeePanel.add(buttonPanel, BorderLayout.SOUTH);


        // Button Events
        btnAdd.addActionListener(e -> {
            String id = ID.getText();
            String name = Name.getText();
            String position = Position.getText();
            String salary = Salary.getText();
            if (!id.isEmpty() && !name.isEmpty() && !position.isEmpty() && !salary.isEmpty()) {
                tableModel.addRow(new Object[]{id, name, position, salary});
                clearFields();
                try {
                    Map<String, Object> data = new HashMap<>();
                    data.put("name", name);
                    data.put("position", position);
                    data.put("salary", salary);
                    Firestore db = FirebaseConfig.getFirestore();
                    db.collection("employees").document(id).set(data);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Firebase Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });


        btnUpdate.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.setValueAt(ID.getText(), selectedRow, 0);
                tableModel.setValueAt(Name.getText(), selectedRow, 1);
                tableModel.setValueAt(Position.getText(), selectedRow, 2);
                tableModel.setValueAt(Salary.getText(), selectedRow, 3);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to update.");
            }
        });


        btnDelete.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });


        employeeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                public void mouseClicked(MouseEvent e){
                    int selectedRow = employeeTable.getSelectedRow();
                    ID.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    Name.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    Position.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    Salary.setText(tableModel.getValueAt(selectedRow, 3).toString());
                }
            }
        });
    }

    // Attendance Tab
    JPanel attendancePanel = new JPanel(new BorderLayout());

    attendanceTableModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Date", "Status"}, 0);
    attendanceTable = new JTable(attendanceTableModel);
    JScrollPane attendanceScroll = new JScrollPane(attendanceTable);
        attendancePanel.add(attendanceScroll, BorderLayout.CENTER);

    JPanel attendanceButtons = new JPanel();
    JButton btnPresent = new JButton("Mark Present");
    JButton btnLeave = new JButton("Record Leave");
        attendanceButtons.add(btnPresent);
        attendanceButtons.add(btnLeave);
        attendancePanel.add(attendanceButtons, BorderLayout.SOUTH);

    // Events
        btnPresent.addActionListener(e -> recordAttendance("Present"));
        btnLeave.addActionListener(e -> recordAttendance("Leave"));

    // Add tabs
        tabbedPane.addTab("Employee Management", employeePanel);
        tabbedPane.addTab("Attendance / Leaves", attendancePanel);

    add(tabbedPane);

    private void recordAttendance(String status) {
        int row = employeeTable.getSelectedRow();
        if (row != -1) {
            String id = employeeTableModel.getValueAt(row, 0).toString();
            String name = employeeTableModel.getValueAt(row, 1).toString();
            String date = LocalDate.now().toString(); // current date
            attendanceTableModel.addRow(new Object[]{id, name, date, status});
            try {
                Map<String, Object> data = new HashMap<>();
                data.put("employee_id", id);
                data.put("name", name);
                data.put("date", date);
                data.put("status", status);

                Firestore db = FirebaseConfig.getFirestore();
                db.collection("attendance").add(data);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save attendance to Firebase.");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to record attendance/leave.");
        }
    }

    private void clearFields() {
        ID.setText("");
        Name.setText("");
        Position.setText("");
        Salary.setText("");
    }


    public static void main(String[] args) {
        FirebaseConfig.initialize();
        SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
    }
}

