package com.pluralsight.model;

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
        salesTaxAmount = new BigDecimal( 0.05 / Double.parseDouble(String.valueOf(vehicleSold.getPrice())));
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

    public String toRow() {
        return String.format("%s | salesTaxAmount: %s | recordingFee: %s | isFinanced: %b",
                contractToRow(),
                getSalesTaxAmount(),
                getRecordingFee(),
                isFinanced());
    }

    @Override
    public BigDecimal getTotalPrice() {
        return new BigDecimal(Double.parseDouble(String.valueOf(getVehicleSold().getPrice())) + Double.parseDouble(String.valueOf(salesTaxAmount)) + Double.parseDouble(String.valueOf(recordingFee)) + Double.parseDouble(String.valueOf(getProcessioningFee())));
    }
    @Override
    public BigDecimal getMonthlyPayments() {
        BigDecimal bigDecimal = new BigDecimal("0");
        if (isFinanced){
            if (Double.parseDouble(String.valueOf(getVehicleSold().getPrice())) >= 10000) {
                bigDecimal = new BigDecimal(String.valueOf(.0425/48));
            } else {
                bigDecimal = new BigDecimal(String.valueOf(.0525/24));
            }
        }
        return bigDecimal;
    }
}
