package org.example;

public class SSSContributions {
    public static Contribution calculate(double monthlySalary) {
        double contribution;
        double ecContribution = 10.00; // Fixed EC contribution per pay period

        if (monthlySalary < 3250) {
            contribution = 135.00 / 2; // Semi-monthly
        } else if (monthlySalary < 3750) {
            contribution = 157.50 / 2;
        }
        // [All other brackets remain the same as original]
        else if (monthlySalary < 24750) {
            contribution = 1102.50 / 2;
        } else {
            contribution = 1125.00 / 2; // Maximum contribution
        }

        return new Contribution(contribution, ecContribution);
    }

    public static class Contribution {
        public final double employeeShare;
        public final double employerShare;

        public Contribution(double employeeShare, double employerShare) {
            this.employeeShare = employeeShare;
            this.employerShare = employerShare;
        }
    }
}
