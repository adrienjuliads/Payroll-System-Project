package org.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class GUI extends JFrame {
    // Employee panel components
    private JTable employeeTable;
    private DefaultTableModel employeeTableModel;
    private JTextField ID, Name, Position, Salary;


    // Attendance panel components
    private JTable attendanceTable;
    private DefaultTableModel attendanceTableModel;


    // Payroll Processing components
    private JComboBox<String> employeeIdComboBox;
    private JTextField startDateField, endDateField;
    private JTextArea payslipArea;
    private Payroll currentPayroll;


    // Compliance & Reporting components
    private JTextArea reportArea;


    // Employee storage
    private Map<String, Employee> employees = new HashMap<>();


    public GUI() {
        setTitle("Payroll System - Employee, Payroll, and Compliance Management");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Main tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();


        // Employee Management Tab (existing code)
        JPanel employeePanel = createEmployeePanel();
        tabbedPane.addTab("Employee Management", employeePanel);


        // Attendance Tab (existing code)
        JPanel attendancePanel = createAttendancePanel();
        tabbedPane.addTab("Attendance / Leaves", attendancePanel);


        // Payroll Processing Tab
        JPanel payrollPanel = createPayrollPanel();
        tabbedPane.addTab("Payroll Processing", payrollPanel);


        // Compliance & Reporting Tab
        JPanel compliancePanel = createCompliancePanel();
        tabbedPane.addTab("Compliance & Reporting", compliancePanel);


        add(tabbedPane);
    }


    private JPanel createEmployeePanel() {
        JPanel employeePanel = new JPanel(new BorderLayout());


        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        ID = new JTextField();
        Name = new JTextField();
        Position = new JTextField();
        Salary = new JTextField();


        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(new JLabel("Position:"));
        formPanel.add(new JLabel("Salary:"));
        formPanel.add(ID);
        formPanel.add(Name);
        formPanel.add(Position);
        formPanel.add(Salary);
        employeePanel.add(formPanel, BorderLayout.NORTH);


        // Table
        employeeTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(employeeTableModel);
        JScrollPane employeeScroll = new JScrollPane(employeeTable);
        employeePanel.add(employeeScroll, BorderLayout.CENTER);


        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        employeePanel.add(buttonPanel, BorderLayout.SOUTH);


        // Events
        btnAdd.addActionListener(e -> addEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        employeeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                populateEmployeeFieldsFromTable();
            }
        });


        return employeePanel;
    }


    private JPanel createAttendancePanel() {
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


        btnPresent.addActionListener(e -> recordAttendance("Present"));
        btnLeave.addActionListener(e -> recordAttendance("Leave"));


        return attendancePanel;
    }


    private JPanel createPayrollPanel() {
        JPanel payrollPanel = new JPanel(new BorderLayout());


        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        employeeIdComboBox = new JComboBox<>();
        startDateField = new JTextField(LocalDate.now().withDayOfMonth(1).toString());
        endDateField = new JTextField(LocalDate.now().withDayOfMonth(15).toString());


        inputPanel.add(new JLabel("Employee ID:"));
        inputPanel.add(employeeIdComboBox);
        inputPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        inputPanel.add(startDateField);
        inputPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        inputPanel.add(endDateField);
        payrollPanel.add(inputPanel, BorderLayout.NORTH);


        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton btnCalculate = new JButton("Calculate Payroll");
        JButton btnProcess = new JButton("Process Payroll");
        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnProcess);
        payrollPanel.add(buttonPanel, BorderLayout.CENTER);


        // Payslip Area
        payslipArea = new JTextArea(15, 50);
        payslipArea.setEditable(false);
        payrollPanel.add(new JScrollPane(payslipArea), BorderLayout.SOUTH);


        // Events
        btnCalculate.addActionListener(e -> calculatePayroll());
        btnProcess.addActionListener(e -> processPayroll());


        return payrollPanel;
    }


    private JPanel createCompliancePanel() {
        JPanel compliancePanel = new JPanel(new BorderLayout());


        // Report Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton btnSSS = new JButton("SSS Report");
        JButton btnPhilHealth = new JButton("PhilHealth Report");
        JButton btnPagIbig = new JButton("Pag-IBIG Report");
        JButton btnBIR = new JButton("BIR Report");
        JButton btnYearEnd = new JButton("Year-End Tax");
        buttonPanel.add(btnSSS);
        buttonPanel.add(btnPhilHealth);
        buttonPanel.add(btnPagIbig);
        buttonPanel.add(btnBIR);
        buttonPanel.add(btnYearEnd);
        compliancePanel.add(buttonPanel, BorderLayout.NORTH);


        // Report Area
        reportArea = new JTextArea(15, 50);
        reportArea.setEditable(false);
        compliancePanel.add(new JScrollPane(reportArea), BorderLayout.CENTER);


        // Events
        btnSSS.addActionListener(e -> generateSSSReport());
        btnPhilHealth.addActionListener(e -> generatePhilHealthReport());
        btnPagIbig.addActionListener(e -> generatePagIbigReport());
        btnBIR.addActionListener(e -> generateBIRReport());
        btnYearEnd.addActionListener(e -> generateYearEndReport());


        return compliancePanel;
    }


    private void addEmployee() {
        String id = ID.getText().trim();
        String name = Name.getText().trim();
        String position = Position.getText().trim();
        String salaryText = Salary.getText().trim();


        if (id.isEmpty() || name.isEmpty() || position.isEmpty() || salaryText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }


        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid salary format. Enter a number.");
            return;
        }


        String[] names = name.split(" ", 2);
        String firstName = names[0];
        String lastName = names.length > 1 ? names[1] : "";


        Employee emp = new Employee(
                id, firstName, lastName,
                LocalDate.of(1990, 1, 1), // Placeholder birth date
                "", "", position, "", LocalDate.now(),
                salary, "", "", "", ""
        );


        employees.put(id, emp);
        employeeTableModel.addRow(new Object[]{id, name, position, salaryText});
        employeeIdComboBox.addItem(id);
        clearFields();
    }


    private void updateEmployee() {
        int row = employeeTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to update.");
            return;
        }


        String id = ID.getText().trim();
        String name = Name.getText().trim();
        String position = Position.getText().trim();
        String salaryText = Salary.getText().trim();


        if (id.isEmpty() || name.isEmpty() || position.isEmpty() || salaryText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }


        employeeTableModel.setValueAt(id, row, 0);
        employeeTableModel.setValueAt(name, row, 1);
        employeeTableModel.setValueAt(position, row, 2);
        employeeTableModel.setValueAt(salaryText, row, 3);


        Employee emp = employees.get(employeeTableModel.getValueAt(row, 0).toString());
        if (emp != null) {
            emp.setFirstName(name.split(" ", 2)[0]);
            emp.setLastName(name.split(" ", 2).length > 1 ? name.split(" ", 2)[1] : "");
            emp.setPosition(position);
            emp.setBasicSalary(Double.parseDouble(salaryText));
        }


        clearFields();
    }


    private void deleteEmployee() {
        int row = employeeTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }


        String id = employeeTableModel.getValueAt(row, 0).toString();
        employees.remove(id);
        employeeIdComboBox.removeItem(id);
        employeeTableModel.removeRow(row);
        clearFields();
    }


    private void populateEmployeeFieldsFromTable() {
        int row = employeeTable.getSelectedRow();
        if (row != -1) {
            ID.setText(employeeTableModel.getValueAt(row, 0).toString());
            Name.setText(employeeTableModel.getValueAt(row, 1).toString());
            Position.setText(employeeTableModel.getValueAt(row, 2).toString());
            Salary.setText(employeeTableModel.getValueAt(row, 3).toString());
        }
    }


    private void recordAttendance(String status) {
        int row = employeeTable.getSelectedRow();
        if (row != -1) {
            String id = employeeTableModel.getValueAt(row, 0).toString();
            String name = employeeTableModel.getValueAt(row, 1).toString();
            attendanceTableModel.addRow(new Object[]{id, name, LocalDate.now().toString(), status});
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to record attendance.");
        }
    }


    private void calculatePayroll() {
        String selectedId = (String) employeeIdComboBox.getSelectedItem();
        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Select an employee.");
            return;
        }


        Employee emp = employees.get(selectedId);
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Employee not found.");
            return;
        }


        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(startDateField.getText());
            endDate = LocalDate.parse(endDateField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.");
            return;
        }


        currentPayroll = new Payroll(emp, startDate, endDate);
        currentPayroll.calculatePay();
        payslipArea.setText(currentPayroll.generatePayslip());
    }


    private void processPayroll() {
        if (currentPayroll == null) {
            JOptionPane.showMessageDialog(this, "Calculate payroll first.");
            return;
        }


        currentPayroll.getEmployee().addPayrollRecord(currentPayroll);
        JOptionPane.showMessageDialog(this, "Payroll processed successfully.");
        currentPayroll = null;
        payslipArea.setText("");
    }


    private void generateSSSReport() {
        StringBuilder report = new StringBuilder("SSS Contributions Report\n\n");
        employees.values().forEach(emp -> {
            report.append(String.format("Employee: %s %s (ID: %s)\n",
                    emp.getFirstName(), emp.getLastName(), emp.getId()));
            emp.getPayrollHistory().forEach(p -> {
                report.append(String.format(" Period: %s to %s\n", p.getStartDate(), p.getEndDate()));
                report.append(String.format(" Employee Share: ₱%.2f\n", p.getSssDeduction()));
                report.append(String.format(" Employer Share: ₱%.2f\n\n", p.getSssEcContribution()));
            });
            report.append("----------------------------\n");
        });
        reportArea.setText(report.toString());
    }


    private void generatePhilHealthReport() {
        StringBuilder report = new StringBuilder("PhilHealth Contributions Report\n\n");
        employees.values().forEach(emp -> {
            report.append(String.format("Employee: %s %s (ID: %s)\n",
                    emp.getFirstName(), emp.getLastName(), emp.getId()));
            emp.getPayrollHistory().forEach(p -> {
                report.append(String.format(" Period: %s to %s\n", p.getStartDate(), p.getEndDate()));
                report.append(String.format(" Contribution: ₱%.2f\n\n", p.getPhilHealthDeduction()));
            });
            report.append("----------------------------\n");
        });
        reportArea.setText(report.toString());
    }


    private void generatePagIbigReport() {
        StringBuilder report = new StringBuilder("Pag-IBIG Contributions Report\n\n");
        employees.values().forEach(emp -> {
            report.append(String.format("Employee: %s %s (ID: %s)\n",
                    emp.getFirstName(), emp.getLastName(), emp.getId()));
            emp.getPayrollHistory().forEach(p -> {
                report.append(String.format(" Period: %s to %s\n", p.getStartDate(), p.getEndDate()));
                report.append(String.format(" Contribution: ₱%.2f\n\n", p.getPagIbigDeduction()));
            });
            report.append("----------------------------\n");
        });
        reportArea.setText(report.toString());
    }


    private void generateBIRReport() {
        StringBuilder report = new StringBuilder("BIR Tax Report\n\n");
        employees.values().forEach(emp -> {
            report.append(String.format("Employee: %s %s (ID: %s)\n",
                    emp.getFirstName(), emp.getLastName(), emp.getId()));
            emp.getPayrollHistory().forEach(p -> {
                report.append(String.format(" Period: %s to %s\n", p.getStartDate(), p.getEndDate()));
                report.append(String.format(" Withholding Tax: ₱%.2f\n\n", p.getWithholdingTax()));
            });
            report.append("----------------------------\n");
        });
        reportArea.setText(report.toString());
    }


    private void generateYearEndReport() {
        StringBuilder report = new StringBuilder("Year-End Tax Report\n\n");
        employees.values().forEach(emp -> {
            // Calculate Annual Gross Income (sum of all gross pays)
            double annualGross = emp.getPayrollHistory().stream()
                    .mapToDouble(Payroll::getGrossPay)
                    .sum();

            // Calculate Annual Taxable Income (Gross - Non-taxable deductions)
            double annualTaxable = emp.getPayrollHistory().stream()
                    .mapToDouble(p -> p.getGrossPay()
                            - p.getSssDeduction()
                            - p.getPhilHealthDeduction()
                            - p.getPagIbigDeduction())
                    .sum();

            double annualTax = emp.getPayrollHistory().stream()
                    .mapToDouble(Payroll::getWithholdingTax)
                    .sum(); // Monthly x 12 periods
            report.append(String.format("Employee: %s %s (ID: %s)\n",
                    emp.getFirstName(), emp.getLastName(), emp.getId()));
            report.append(String.format(" Gross Income: ₱%,.2f\n", annualGross));
            report.append(String.format(" Taxable Income: ₱%,.2f\n", annualTaxable));
            report.append(String.format(" Total Annual Tax: ₱%.2f\n\n", annualTax));
            report.append("----------------------------\n");
        });
        reportArea.setText(report.toString());
    }


    private void clearFields() {
        ID.setText("");
        Name.setText("");
        Position.setText("");
        Salary.setText("");
    }
}
