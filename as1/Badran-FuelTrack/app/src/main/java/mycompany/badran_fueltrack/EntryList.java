package mycompany.badran_fueltrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hasan on 2016-01-29.
 */
public class EntryList {
    private ArrayList<Entry> entries = new ArrayList<Entry>();

    public void add(Entry entry){

        if (this.hasEntry(entry)) {
            throw new IllegalArgumentException();
        }
        entries.add(entry);
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public Entry getEntry(int index){
        return entries.get(index);
    }


    public boolean hasEntry(Entry entry){
        return entries.contains(entry);
    }

    public ArrayList<Entry> getEntries(int index){
        //return entries.get(index);
        ArrayList<Entry> entries1 = new ArrayList<Entry>();

        Collections.sort(entries1, new Comparator<Entry>() {
            //@Override
            public int compare(Entry r1, Entry r2) {
                return r1.getDate().compareTo(r2.getDate());
            }
        });
        return entries1;
    }

    public int getCount(){
        return entries.size();
    }

    public void removeEntry(Entry entry){
        entries.remove(entry);
    }

}

