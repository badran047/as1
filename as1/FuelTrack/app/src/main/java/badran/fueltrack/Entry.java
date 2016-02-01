package badran.fueltrack;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by hasan on 2016-01-30.
 */
public class Entry {
    private Date date;
    private String station;
    private Double odometer;
    private Double fuelUnitCost;
    private String fuelGrade;
    private Double fuelAmount;

    public Entry(Date date, String station, Double odometer, Double fuelUnitCost, String fuelGrade, Double fuelAmount) {
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.fuelUnitCost = fuelUnitCost;
        this.fuelGrade = fuelGrade;
        this.fuelAmount = fuelAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station){
        this.station = station;
    }

    public Double getOdometer() {
        return odometer;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public Double getFuelUnitCost() {
        return fuelUnitCost;
    }

    public void setFuelUnitCost(Double fuelUCost){
        this.fuelUnitCost = fuelUCost;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public Double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Double fuelAmount){
        this.fuelAmount = fuelAmount;
    }

    public double fuelCost() {
        /* taken from:
         * http://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java */
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(( this.getFuelUnitCost() / 100.0) * (this.getFuelAmount())));
    }

    @Override
    public String toString(){
        return "Station: " + this.station + "\nFuel Cost: $" + this.fuelCost();
    }
}
