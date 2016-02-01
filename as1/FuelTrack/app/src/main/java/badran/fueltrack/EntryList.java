package badran.fueltrack;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hasan on 2016-01-30.
 */
public class EntryList {
    private ArrayList<Entry> entrylist;

    public EntryList() {
        this.entrylist = new ArrayList<Entry>();
    }

    public ArrayList<Entry> getEntryList() {
        return entrylist;
    }

    public Entry getEntry(int index){
        return entrylist.get(index);
    }

    public void setEntryList(ArrayList<Entry> entrylist) {
        this.entrylist = entrylist;
    }

    public void initEntryList () {
        this.entrylist = new ArrayList<Entry>();
    }

    public boolean hasEntry(Entry entry){
        return entrylist.contains(entry);
    }

    public Double fuelCost() {
        double cost = 0;
        for(Entry log: entrylist) {
            cost += log.fuelCost();
        }
        return cost;
    }

    public boolean editEntry(int index, Entry entry) {
        if (entrylist.size() == 0) {
            entrylist.add(0, entry);
            return hasEntry(entry);
        }
        else{
            entrylist.remove(index);
            entrylist.add(index, entry);
            return hasEntry(entry);
        }
    }

    public boolean addNewEntry(Date date, String station, Double odometer, String fuelGrade, Double fuelAmount, Double fuelUnitCost) {
        Entry entry = new Entry(date, station, odometer, fuelUnitCost, fuelGrade, fuelAmount);
        return this.entrylist.add(entry);
    }

    public boolean add(Entry entry) {
        return this.entrylist.add(entry);
    }

    public Entry remove(int position) {
        return this.entrylist.remove(position);
    }
}

