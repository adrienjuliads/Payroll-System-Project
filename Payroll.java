package org.example;

import java.time.LocalDate;

public class Payroll {
    // Employee and period information
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;

    // Payroll components
    private int daysWorked;
    private double grossPay;
    private double netPay;

    // Deductions
    private double sssDeduction;
    private double sssEcContribution;  // Employer's share
    private double philHealthDeduction;
    private double pagIbigDeduction;
    private double withholdingTax;

    // Other earnings/deductions (expandable)
    private double overtimePay;
    private double lateDeduction;
    private double otherBenefits;

    public Payroll(Employee employee, LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void calculatePay() {
        // 1. Calculate basic components
        calculateWorkDays();
        calculateBasicPay();

        // 2. Calculate additional earnings
        calculateOvertime();
        calculateOtherBenefits();

        // 3. Calculate deductions
        calculateMandatoryContributions();
        calculateTax();
        calculateLateDeductions();

        // 4. Calculate final net pay
        calculateNetPay();
    }

    private void calculateWorkDays() {
        this.daysWorked = employee.getDaysWorked(startDate, endDate);
    }

    private void calculateBasicPay() {
        // Semi-monthly pay (1/2 of monthly salary)
        this.grossPay = employee.getBasicSalary();
    }

    private void calculateOvertime() {
        // Example implementation - would use attendance records
        this.overtimePay = 0; // Implement actual overtime calculation
    }

    private void calculateOtherBenefits() {
        // Example - bonuses, allowances, etc.
        this.otherBenefits = 0;
    }

    private void calculateMandatoryContributions() {
        double monthlySalary = employee.getBasicSalary();

        // Calculate SSS contributions (employee and employer share)
        SSSContributions.Contribution sss = SSSContributions.calculate(monthlySalary);
        this.sssDeduction = sss.employeeShare;
        this.sssEcContribution = sss.employerShare;

        // Calculate PhilHealth
        this.philHealthDeduction = PhilHealthContributions.calculate(monthlySalary);

        // Calculate Pag-IBIG
        this.pagIbigDeduction = PagIbigContributions.calculate(monthlySalary);
    }

    private void calculateTax() {
        double taxableIncome = this.grossPay + this.overtimePay + this.otherBenefits
                - (this.sssDeduction + this.philHealthDeduction + this.pagIbigDeduction);

        this.withholdingTax = TaxCalculator.calculateWithholdingTax(taxableIncome);
    }

    private void calculateLateDeductions() {
        // Example - would use attendance records
        this.lateDeduction = 0; // Implement actual late calculation
    }

    private void calculateNetPay() {
        this.netPay = (this.grossPay + this.overtimePay + this.otherBenefits)
                - (this.sssDeduction + this.philHealthDeduction
                + this.pagIbigDeduction + this.withholdingTax
                + this.lateDeduction);
    }

    // Getters for all components
    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getNetPay() {
        return netPay;
    }

    public double getSssDeduction() {
        return sssDeduction;
    }

    public double getSssEcContribution() {
        return sssEcContribution;
    }

    public double getPhilHealthDeduction() {
        return philHealthDeduction;
    }

    public double getPagIbigDeduction() {
        return pagIbigDeduction;
    }

    public double getWithholdingTax() {
        return withholdingTax;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public double getLateDeduction() {
        return lateDeduction;
    }

    public double getOtherBenefits() {
        return otherBenefits;
    }

    public double getTotalDeductions() {
        return sssDeduction + philHealthDeduction + pagIbigDeduction + withholdingTax + lateDeduction;
    }

    public double getTotalEarnings() {
        return grossPay + overtimePay + otherBenefits;
    }

    // Utility method to generate payslip text
    public String generatePayslip() {
        return String.format(
                "PAYSLIP\n\n" +
                        "Employee: %s %s\n" +
                        "Position: %s\n" +
                        "Period: %s to %s\n\n" +
                        "EARNINGS:\n" +
                        "Basic Salary: ₱%,.2f\n" +
                        "Overtime Pay: ₱%,.2f\n" +
                        "Other Benefits: ₱%,.2f\n" +
                        "Total Earnings: ₱%,.2f\n\n" +
                        "DEDUCTIONS:\n" +
                        "SSS: ₱%,.2f\n" +
                        "PhilHealth: ₱%,.2f\n" +
                        "Pag-IBIG: ₱%,.2f\n" +
                        "Withholding Tax: ₱%,.2f\n" +
                        "Late Deductions: ₱%,.2f\n" +
                        "Total Deductions: ₱%,.2f\n\n" +
                        "NET PAY: ₱%,.2f",
                employee.getFirstName(), employee.getLastName(),
                employee.getPosition(),
                startDate, endDate,
                grossPay, overtimePay, otherBenefits, getTotalEarnings(),
                sssDeduction, philHealthDeduction, pagIbigDeduction,
                withholdingTax, lateDeduction, getTotalDeductions(),
                netPay
        );
    }
}
