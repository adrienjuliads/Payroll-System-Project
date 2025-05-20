package org.example;

public class TaxCalculator {
    public static double calculateWithholdingTax(double taxableIncome) {
        double annualTaxableIncome = taxableIncome * 12; // Monthly tax

        if (annualTaxableIncome <= 250000) {
            return 0;
        } else if (annualTaxableIncome <= 400000) {
            return (annualTaxableIncome - 250000) * 0.15 / 12;
        } else if (annualTaxableIncome <= 800000) {
            return (22500 + (annualTaxableIncome - 400000) * 0.20) / 12;
        } else if (annualTaxableIncome <= 2000000) {
            return (102500 + (annualTaxableIncome - 800000) * 0.25) / 12;
        } else if (annualTaxableIncome <= 8000000) {
            return (402500 + (annualTaxableIncome - 2000000) * 0.30) / 12;
        } else {
            return (2202500 + (annualTaxableIncome - 8000000) * 0.35) / 12;
        }
    }
}
