package com.pluralsight.model;

import com.pluralsight.data.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesContract extends Contract {

    private BigDecimal salesTaxAmount;
    private BigDecimal recordingFee = new BigDecimal(100);
    private boolean isFinanced;

    public SalesContract() {
        salesTaxAmount  = new BigDecimal(0.05 / Double.parseDouble(String.valueOf(getVehicleSold().getPrice())));
    }
    public SalesContract(BigDecimal salesTaxAmount, BigDecimal recordingFee, boolean isFinanced) {
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.isFinanced = isFinanced;
    }

    public SalesContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold, BigDecimal salesTaxAmount, BigDecimal recordingFee, boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.isFinanced = isFinanced;
    }
    public SalesContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        salesTaxAmount  = new BigDecimal( 0.05 / Double.parseDouble(String.valueOf(vehicleSold.getPrice())));
    }

    public BigDecimal getProcessioningFee() {
        if (Double.parseDouble(String.valueOf(getVehicleSold().getPrice()))<10000){
            return new BigDecimal(295);
        }
        return new BigDecimal(495);
    }

    public BigDecimal getSalesTaxAmount() {return salesTaxAmount;}
    public void setSalesTaxAmount(BigDecimal salesTaxAmount) {this.salesTaxAmount = salesTaxAmount;}

    public BigDecimal getRecordingFee() {return recordingFee;}
    public void setRecordingFee(BigDecimal recordingFee) {this.recordingFee = recordingFee;}

    public boolean isFinanced() {return isFinanced;}
    public void setFinanced(boolean financed) {isFinanced = financed;}

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }
    @Override
    public BigDecimal getMonthlyPayments() {
        return null;
    }
}
