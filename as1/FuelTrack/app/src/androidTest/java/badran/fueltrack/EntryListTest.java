package badran.fueltrack;

import android.test.ActivityInstrumentationTestCase2;
import java.util.Date;

/**
 * Created by hasan on 2016-01-31.
 */
public class EntryListTest extends ActivityInstrumentationTestCase2 {
    public EntryListTest() {
        super(MainActivity.class);
    }

    public void testAddEntry() {
        EntryList entrylist = new EntryList();
        Entry entry = new Entry(new Date(), "Costco", 200123.5, 79.4, "regular", 40.234 );
        entrylist.add(entry);

        assertTrue(entrylist.hasEntry(entry));
    }

    public void testEditEntry(){
        EntryList entrylist = new EntryList();
        Entry entry0 = new Entry(new Date(),"Costco", 200123.5, 79.4, "regular", 40.234 );
        entrylist.add(entry0);

        Entry entry1 = entry0;
        Double newfuelUnitCost = 80.4;
        entry1.setFuelUnitCost(newfuelUnitCost);

        assertEquals(newfuelUnitCost, entrylist.getEntry(0).getFuelUnitCost());
    }

    public void testfuelCost(){
        double fuelCost;
        EntryList entrylist = new EntryList();
        Entry entry = new Entry(new Date(), "Costco", 200123.5, 79.4, "regular", 40.234 );
        fuelCost = 31.95;
        entrylist.add(entry);

        assertEquals(fuelCost, entrylist.fuelCost());
    }

    public void testGetEntry(){
        EntryList entrylist = new EntryList();
        Entry entry = new Entry(new Date(),"Costco", 200123.5, 79.4, "regular", 40.234 );
        entrylist.add(entry);

        assertEquals(entry, entrylist.getEntry(0));
    }

    public void testHasEntry(){
        EntryList entrylist = new EntryList();
        Entry entry0 = new Entry(new Date(),"Costco", 200123.5, 79.4, "regular", 40.234 );
        entrylist.add(entry0);
        assertTrue(entrylist.hasEntry(entry0));
        Entry entry1 = new Entry(new Date(),"Costco", 200123.5, 79.4, "regular", 40.234 );

        assertFalse(entrylist.hasEntry(entry1));
    }

}
