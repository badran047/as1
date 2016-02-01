package badran.fueltrack;

import android.test.ActivityInstrumentationTestCase2;
import java.util.Date;

/**
 * Created by hasan on 2016-01-31.
 */
public class EntryTest extends ActivityInstrumentationTestCase2 {
    public EntryTest() {
        super(MainActivity.class);
    }

    public void testEntry(){
        Entry entry = new Entry(new Date(),"Costco", 200123.5, 79.4, "regular", 40.234 );

        assertNotNull(entry);
    }
}
