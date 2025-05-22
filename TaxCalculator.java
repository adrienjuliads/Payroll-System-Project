package org.example;

public class TaxCalculator {
    public static double calculateWithholdingTax(double taxableIncome) {
        double annualTaxableIncome = taxableIncome * 24; // Semi-monthly x 24 pay periods

        if (annualTaxableIncome <= 250000) {
            return 0;
        } else if (annualTaxableIncome <= 400000) {
            return (annualTaxableIncome - 250000) * 0.15 / 24;
        } else if (annualTaxableIncome <= 800000) {
            return (22500 + (annualTaxableIncome - 400000) * 0.20) / 24;
        } else if (annualTaxableIncome <= 2000000) {
            return (102500 + (annualTaxableIncome - 800000) * 0.25) / 24;
        } else if (annualTaxableIncome <= 8000000) {
            return (402500 + (annualTaxableIncome - 2000000) * 0.30) / 24;
        } else {
            return (2202500 + (annualTaxableIncome - 8000000) * 0.35) / 24;
        }
    }
}
