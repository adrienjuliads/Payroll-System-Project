package org.example;

public class PhilHealthContributions {
    public static double calculate(double monthlySalary) {
        // PhilHealth contribution is 4% of monthly salary, shared 50-50 between employee and employer
        // For semi-monthly, we calculate 1% of monthly salary
        double contribution = monthlySalary * 0.01;

        // Minimum and maximum contributions (2023 rates)
        if (contribution < 200.00 / 2) {
            contribution = 200.00 / 2; // Semi-monthly minimum
        } else if (contribution > 1800.00 / 2) {
            contribution = 1800.00 / 2; // Semi-monthly maximum
        }

        return contribution;
    }
}
