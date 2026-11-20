package com.pluralsight.model;

import com.pluralsight.data.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeaseContract extends Contract {

    private BigDecimal expectingEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract() {
        this.expectingEndingValue = getVehicleSold().getPrice().divide(new BigDecimal(".5"));
        this.leaseFee = getVehicleSold().getPrice().divide(new BigDecimal(".07"));
    }
    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.expectingEndingValue = getVehicleSold().getPrice().divide(new BigDecimal(".5"));
        this.leaseFee = getVehicleSold().getPrice().divide(new BigDecimal(".07"));

    }

    public BigDecimal getExpectingEndingValue() {return expectingEndingValue;}
    public void setExpectingEndingValue(BigDecimal expectingEndingValue) {this.expectingEndingValue = expectingEndingValue;}

    public BigDecimal getLeaseFee() {return leaseFee;}
    public void setLeaseFee(BigDecimal leaseFee) {this.leaseFee = leaseFee;}

    public String salesToRow(){
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
