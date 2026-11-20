package com.pluralsight.data;

import com.pluralsight.model.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Contract {
    private LocalDate contractDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    public Contract() {}
    public Contract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public LocalDate getContractDate() {return contractDate;}
    public void setContractDate(LocalDate contractDate) {this.contractDate = contractDate;}

    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}

    public String getCustomerEmail() {return customerEmail;}
    public void setCustomerEmail(String customerEmail) {this.customerEmail = customerEmail;}

    public Vehicle getVehicleSold() {return vehicleSold;}
    public void setVehicleSold(Vehicle vehicleSold) {this.vehicleSold = vehicleSold;}

    public abstract BigDecimal getTotalPrice();
    public abstract BigDecimal getMonthlyPayments();

    public String contractToRow() {
        return String.format("contractDate: %s | customerName: %s | customerEmail: %s | %s ",
                getContractDate(),
                getCustomerName(),
                getCustomerEmail(),
                vehicleSold.toRow());
    }
}
