package org.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;


    private JTextField ID, Name, Position, Salary;


    public GUI() {
        setTitle("Employee Management");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


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


        add(panel, BorderLayout.NORTH);


        // Center Panel - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);


        // Bottom Panel - Buttons
        JPanel buttonPanel = new JPanel();


        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");


        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);


        // Button Events
        btnAdd.addActionListener(e -> {
            String id = ID.getText();
            String name = Name.getText();
            String position = Position.getText();
            String salary = Salary.getText();
            if (!id.isEmpty() && !name.isEmpty() && !position.isEmpty() && !salary.isEmpty()) {
                tableModel.addRow(new Object[]{id, name, position, salary});
                clearFields();
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
                int selectedRow = employeeTable.getSelectedRow();
                ID.setText(tableModel.getValueAt(selectedRow, 0).toString());
                Name.setText(tableModel.getValueAt(selectedRow, 1).toString());
                Position.setText(tableModel.getValueAt(selectedRow, 2).toString());
                Salary.setText(tableModel.getValueAt(selectedRow, 3).toString());
            }
        });
    }


    private void clearFields() {
        ID.setText("");
        Name.setText("");
        Position.setText("");
        Salary.setText("");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}

