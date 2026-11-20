package com.pluralsight.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeaseContract extends Contract {

    private BigDecimal expectingEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract() {init();}

    private void init() {
        this.expectingEndingValue = new BigDecimal(Double.parseDouble(String.valueOf(getVehicleSold().getPrice()))/.5);
        this.leaseFee = new BigDecimal(Double.parseDouble(String.valueOf(getVehicleSold().getPrice()))/.07);
    }

    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        init();
    }

    public BigDecimal getExpectingEndingValue() {return expectingEndingValue;}
    public void setExpectingEndingValue(BigDecimal expectingEndingValue) {this.expectingEndingValue = expectingEndingValue;}

    public BigDecimal getLeaseFee() {return leaseFee;}
    public void setLeaseFee(BigDecimal leaseFee) {this.leaseFee = leaseFee;}

    public String toRow() {
        return String.format("expectingEndingValue: %s | leaseFee: %s | %s",
                getExpectingEndingValue(),
                getLeaseFee(),
                contractToRow());
    }

    @Override
    public BigDecimal getTotalPrice() {return leaseFee.add(expectingEndingValue);}
    @Override
    public BigDecimal getMonthlyPayments() {return new BigDecimal(String.valueOf(.04/36));}
}
