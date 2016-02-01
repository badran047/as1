package badran.fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-01-31.
 */
public class EntryListTest extends ActivityInstrumentationTestCase2 {
    public EntryListTest() {
        super(MainActivity.class);
    }

    public void testAddEntry() {
        EntryList entrylist = new EntryList();
        entry.addNewEntry(entry);
        assertTrue(entry, entry.hasEntry());
    }
}
