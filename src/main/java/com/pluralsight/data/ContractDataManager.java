package com.pluralsight.data;

import com.pluralsight.App;
import com.pluralsight.model.Contract;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.SalesContract;
import com.pluralsight.model.Vehicle;
import com.pluralsight.ui.UserInterface;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContractDataManager {

    static ArrayList<Contract> contractList= new ArrayList<>();

    public static void writeToFile(boolean append) {
        System.out.println("write To File");
        try {
            FileWriter fileWriter = new FileWriter("contracts.csv", append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (append) {
                switch (contractList.getClass().getSimpleName()) {
                    case "SalesContract" -> bufferedWriter.append("SALE|");
                    case "LeaseContract" -> bufferedWriter.append("LEASE|");
                }
                bufferedWriter.append(getVehicleRow(contractList.size() - 1).toString());
                bufferedWriter.newLine();
            } else {
                if (!contractList.isEmpty()) {
                    for (int i = 0; i < contractList.size(); i++) {
                        bufferedWriter.append(getVehicleRow(i).toString());
                        bufferedWriter.newLine();
                    }
                }
            }
            bufferedWriter.close();
            Dealership.targetIndex=-1;
        } catch(IOException e) {System.out.println("fail");}
    }

    private static StringBuilder getVehicleRow(int index) {
        Contract contractRow = contractList.get(index);
        String[] contractFields = contractRow.contractToRow().split("\\|");
        StringBuilder vehicleRow = new StringBuilder();

        for (String v: contractFields){
            String[] fieldData = v.split(":");
            System.out.println(fieldData[0]);

            if (!fieldData[0].equals(" price")) {
                vehicleRow.append(fieldData[1].trim()).append("|");
            } else vehicleRow.append(fieldData[1].trim());
        }
        return vehicleRow;
    }

    public static void readFromFile() {
        FileReader fileReader;
        try {
            fileReader = new FileReader("contracts.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;

            while((input = bufReader.readLine()) != null) {
                String[] lineSegment = input.split("\\|");

                if (lineSegment.length > 2) {
                    LocalDate localDate = LocalDate.parse(getDate(lineSegment[1]));
                    String[] vehicle = {lineSegment[4], lineSegment[5], lineSegment[6],
                            lineSegment[7], lineSegment[8], lineSegment[9],
                            lineSegment[10], lineSegment[11]};
                    boolean finance = lineSegment[13].equalsIgnoreCase("no");

                    if (lineSegment[0].equalsIgnoreCase("sale")){
                        SalesContract salesContract = new SalesContract(localDate, lineSegment[2], lineSegment[3],
                                getVehicle(vehicle), new BigDecimal(lineSegment[12]), new BigDecimal(lineSegment[14]),
                                finance);
                        contractList.add(salesContract);
                        System.out.println(salesContract.toRow());
                    }
                }

            }
            bufReader.close();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private static String getDate(String lineSegment) {
        String[] strings = lineSegment.split("");
        StringBuilder fullYear = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0 -> fullYear.append(strings[0]).append(strings[1]).append(strings[2]).append(strings[3]).append("-");
                case 1 -> fullYear.append(strings[4]).append(strings[5]).append("-");
                case 2 -> fullYear.append(strings[6]).append(strings[7]);
            }
        }
        return String.valueOf(fullYear);
    }
    private static String getYear(String lineSegment) {
        String[] strings = lineSegment.split("");
        return String.valueOf(strings[0] + strings[1] + strings[2] + strings[3]);
    }

    private static Vehicle getVehicle(String[] lineSegment) {
        int vin = Integer.parseInt(lineSegment[0]);
        int year = Integer.parseInt(getYear(lineSegment[1])) ;
        String make = lineSegment[2];
        String model = lineSegment[3];
        String type = lineSegment[4];
        String color = lineSegment[5];
        int odometer = Integer.parseInt(lineSegment[6]);
        BigDecimal price = new BigDecimal(lineSegment[7]);
        return new Vehicle(vin, year, make, model, type, color, odometer, price);
    }

}
