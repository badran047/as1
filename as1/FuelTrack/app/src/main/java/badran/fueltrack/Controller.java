package badran.fueltrack;

import android.content.Context;
import android.widget.EditText;
import java.util.Date;

/**
 * Created by hasan on 2016-01-30.
 */
public interface Controller {
    int save(Context context);
    int load(Context context);
    void initEntryList();
    boolean add(Date date, String station, Double odometer, Double fuelAmount, Double fuelUnitCost, String fuelGrade);
    Entry newEntry(Date date, String station, Double odometer, Double fuelAmount, Double fuelUnitCost, String fuelGrade);
    boolean add(Entry entry, int index);
    boolean add(Entry entry);
    Entry remove(int index);
    Entry getAtIndex(int index);
    boolean hasEntry(Entry entry);
    boolean validate(EditText text1, EditText text2, EditText text3, EditText text4, EditText text5, EditText text6);
}
