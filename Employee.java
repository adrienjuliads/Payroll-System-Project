package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee {
        private String id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String address;
        private String contactNumber;
        private String position;
        private String department;
        private LocalDate dateHired;
        private double basicSalary;
        private String sssNumber;
        private String philHealthNumber;
        private String pagIbigNumber;
        private String tin;
        private Map<LocalDate, Attendance> attendanceRecords;
        private List<Payroll> payrollHistory;

        public Employee(String id, String firstName, String lastName, LocalDate birthDate,
                        String address, String contactNumber, String position,
                        String department, LocalDate dateHired, double basicSalary,
                        String sssNumber, String philHealthNumber, String pagIbigNumber,
                        String tin) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.address = address;
            this.contactNumber = contactNumber;
            this.position = position;
            this.department = department;
            this.dateHired = dateHired;
            this.basicSalary = basicSalary;
            this.sssNumber = sssNumber;
            this.philHealthNumber = philHealthNumber;
            this.pagIbigNumber = pagIbigNumber;
            this.tin = tin;
            this.attendanceRecords = new HashMap<>();
            this.payrollHistory = new ArrayList<>();
        }

        // Getters and Setters
        public String getId() { return id; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public LocalDate getBirthDate() { return birthDate; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getContactNumber() { return contactNumber; }
        public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }
        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }
        public LocalDate getDateHired() { return dateHired; }
        public double getBasicSalary() { return basicSalary; }
        public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
        public String getSssNumber() { return sssNumber; }
        public String getPhilHealthNumber() { return philHealthNumber; }
        public String getPagIbigNumber() { return pagIbigNumber; }
        public String getTin() { return tin; }
        public List<Payroll> getPayrollHistory() { return payrollHistory; }

        public void recordAttendance(LocalDate date, String status, double hoursWorked) {
            attendanceRecords.put(date, new Attendance(date, status, hoursWorked));
        }

        public void addPayrollRecord(Payroll payroll) {
            payrollHistory.add(payroll);
        }

        public int getDaysWorked(LocalDate startDate, LocalDate endDate) {
            // Ensure we're checking a full month
//            if (!startDate.withDayOfMonth(1).equals(endDate.withDayOfMonth(endDate.lengthOfMonth()))) {
//                throw new IllegalArgumentException("Pay period must cover a full month");
//            }

            int daysWorked = 0;

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                Attendance attendance = attendanceRecords.get(date);
                if (attendance != null && attendance.getStatus().equals("P")) {
                    daysWorked++;
                }
            }

            return daysWorked;
        }
    
}
