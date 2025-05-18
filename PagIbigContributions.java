package org.example;

public class PagIbigContributions {
    public static double calculate(double monthlySalary) {
        // Pag-IBIG contribution is 2% of monthly salary, but capped at ₱100 per month (₱50 per pay period)
        double monthlyContribution = monthlySalary * 0.02;
        return Math.min(monthlyContribution, 100.00) / 2;
    }
}
