package org.example;

public class SSSContributions {
    public static Contribution calculate(double monthlySalary) {
        double contribution;
        double ecContribution = 20.00; // Fixed EC contribution per pay period

        if (monthlySalary < 3250) {
            contribution = 135.00; // Monthly
        } else if (monthlySalary >= 3250 && monthlySalary < 3750) {
            contribution = 157.50;
        } else if (monthlySalary >= 3750 && monthlySalary < 4250) {
            contribution = 180.00;
        } else if (monthlySalary >= 4250 && monthlySalary < 4750) {
            contribution = 202.50;
        } else if (monthlySalary >= 4750 && monthlySalary < 5250) {
            contribution = 225.00;
        } else if (monthlySalary >= 5250 && monthlySalary < 5750) {
            contribution = 247.50;
        } else if (monthlySalary >= 5750 && monthlySalary < 6250) {
            contribution = 270.00;
        } else if (monthlySalary >= 6250 && monthlySalary < 6750) {
            contribution = 292.50;
        } else if (monthlySalary >= 6750 && monthlySalary < 7250) {
            contribution = 315.00;
        } else if (monthlySalary >= 7250 && monthlySalary < 7750) {
            contribution = 337.50;
        } else if (monthlySalary >= 7750 && monthlySalary < 8250) {
            contribution = 360.00;
        } else if (monthlySalary >= 8250 && monthlySalary < 8750) {
            contribution = 382.50;
        } else if (monthlySalary >= 8750 && monthlySalary < 9250) {
            contribution = 405.00;
        } else if (monthlySalary >= 9250 && monthlySalary < 9750) {
            contribution = 427.50;
        } else if (monthlySalary >= 9750 && monthlySalary < 10250) {
            contribution = 450.00;
        } else if (monthlySalary >= 10250 && monthlySalary < 10750) {
            contribution = 472.50;
        } else if (monthlySalary >= 10750 && monthlySalary < 11250) {
            contribution = 495.00;
        } else if (monthlySalary >= 11250 && monthlySalary < 11750) {
            contribution = 517.50;
        } else if (monthlySalary >= 11750 && monthlySalary < 12250) {
            contribution = 540.00;
        } else if (monthlySalary >= 12250 && monthlySalary < 12750) {
            contribution = 562.50;
        } else if (monthlySalary >= 12750 && monthlySalary < 13250) {
            contribution = 585.00;
        } else if (monthlySalary >= 13250 && monthlySalary < 13750) {
            contribution = 607.50;
        } else if (monthlySalary >= 13750 && monthlySalary < 14250) {
            contribution = 630.00;
        } else if (monthlySalary >= 14250 && monthlySalary < 14750) {
            contribution = 652.50;
        } else if (monthlySalary >= 14750 && monthlySalary < 15250) {
            contribution = 675.00;
        } else if (monthlySalary >= 15250 && monthlySalary < 15750) {
            contribution = 697.50;
        } else if (monthlySalary >= 15750 && monthlySalary < 16250) {
            contribution = 720.00;
        } else if (monthlySalary >= 16250 && monthlySalary < 16750) {
            contribution = 742.50;
        } else if (monthlySalary >= 16750 && monthlySalary < 17250) {
            contribution = 765.00;
        } else if (monthlySalary >= 17250 && monthlySalary < 17750) {
            contribution = 787.50;
        } else if (monthlySalary >= 17750 && monthlySalary < 18250) {
            contribution = 810.00;
        } else if (monthlySalary >= 18250 && monthlySalary < 18750) {
            contribution = 832.50;
        } else if (monthlySalary >= 18750 && monthlySalary < 19250) {
            contribution = 855.00;
        } else if (monthlySalary >= 19250 && monthlySalary < 19750) {
            contribution = 877.50;
        } else if (monthlySalary >= 19750 && monthlySalary < 20250) {
            contribution = 900.00;
        } else if (monthlySalary >= 20250 && monthlySalary < 20750) {
            contribution = 922.50;
        } else if (monthlySalary >= 20750 && monthlySalary < 21250) {
            contribution = 945.00;
        } else if (monthlySalary >= 21250 && monthlySalary < 21750) {
            contribution = 967.50;
        } else if (monthlySalary >= 21750 && monthlySalary < 22250) {
            contribution = 990.00;
        } else if (monthlySalary >= 22250 && monthlySalary < 22750) {
            contribution = 1012.50;
        } else if (monthlySalary >= 22750 && monthlySalary < 23250) {
            contribution = 1035.00;
        } else if (monthlySalary >= 23250 && monthlySalary < 23750) {
            contribution = 1057.50;
        } else if (monthlySalary >= 23750 && monthlySalary < 24250) {
            contribution = 1080.00;
        } else if (monthlySalary >= 24250 && monthlySalary < 24750) {
            contribution = 1102.50;
        } else {
            contribution = 1125.00; // Maximum contribution
        }


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
