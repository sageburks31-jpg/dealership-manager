package com.pluralsight.model;

import com.pluralsight.data.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeaseContract extends Contract {

    private BigDecimal expectingEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract() {}
    public LeaseContract(BigDecimal expectingEndingValue, BigDecimal leaseFee) {
        this.expectingEndingValue = expectingEndingValue;
        this.leaseFee = leaseFee;
    }

    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold, BigDecimal expectingEndingValue, BigDecimal leaseFee) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.expectingEndingValue = expectingEndingValue;
        this.leaseFee = leaseFee;
    }
    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
    }

    public BigDecimal getExpectingEndingValue() {return expectingEndingValue;}
    public void setExpectingEndingValue(BigDecimal expectingEndingValue) {this.expectingEndingValue = expectingEndingValue;}

    public BigDecimal getLeaseFee() {return leaseFee;}
    public void setLeaseFee(BigDecimal leaseFee) {this.leaseFee = leaseFee;}

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }
    @Override
    public BigDecimal getMonthlyPayments() {
        return null;
    }
}
