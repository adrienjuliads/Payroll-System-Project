package org.example;

public class PhilHealthContributions {
    public static double calculate(double monthlySalary) {
        // PhilHealth contribution is 4% of monthly salary, shared 50-50 between employee and employer
        // Now 2% of monthly salary (shared 50-50)
        double contribution = monthlySalary * 0.02;

        // Minimum and maximum contributions (monthly)
        if (contribution < 200.00) {
            contribution = 200.00; // Monthly minimum
        } else if (contribution > 1800.00) {
            contribution = 1800.00; // Monthly maximum
        }

        return contribution / 2; // Employee share only
    }
}
