package org.example;

import java.time.LocalDate;

public class Attendance {
        private LocalDate date;
        private String status; // P-Present, A-Absent, L-Leave, H-Holiday
        private double hoursWorked;

        public Attendance(LocalDate date, String status, double hoursWorked) {
            this.date = date;
            this.status = status;
            this.hoursWorked = hoursWorked;
        }

        public LocalDate getDate() { return date; }
        public String getStatus() { return status; }
        public double getHoursWorked() { return hoursWorked; }
}
