package badran.fueltrack;

import android.content.Context;
import android.widget.EditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hasan on 2016-01-30.
 */
public class AppController implements Controller {
    EntryList app;
    Gson gson;
    private static final String FILENAME = "file.sav";

    public boolean init() {
        app = FuelTrackApp.getApp();
        return app.getEntryList().size() >= 0;
    }

    public AppController() {
        this.init();
    }

    @Override
    public void initEntryList() {
        app.initEntryList();
    }

    @Override
    public boolean add(Date date, String station, Double odometer, Double fuelAmount, Double fuelUnitCost, String fuelGrade) {
        return app.addNewEntry(date, station, odometer, fuelGrade, fuelAmount, fuelUnitCost);
    }

    @Override
    public boolean add(Entry entry) {
        return app.add(entry);
    }

    @Override
    public boolean add(Entry entry, int index) {
        return app.editEntry(index, entry);
    }

    @Override
    public boolean hasEntry(Entry entry) {
        return app.hasEntry(entry);
    }

    @Override
    public int save(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(app.getEntryList(), out);
            out.flush();
            fos.close();
            return 1;
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Entry newEntry(Date date, String station, Double odometer, Double fuelAmount, Double fuelUnitCost, String fuelGrade) {
        return new Entry(date, station, odometer, fuelUnitCost, fuelGrade, fuelAmount);
    }

    @Override
    public Entry getAtIndex(int index) {
        return app.getEntry(index);
    }

    @Override
    public int load(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            gson = new Gson();

			/* taken from:
			 * https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
			 */
            Type listType = new TypeToken<ArrayList<Entry>>(){}.getType();
            ArrayList<Entry> tmp;
            tmp = gson.fromJson(in, listType);
            app.setEntryList(tmp);
            return 1;

        } catch (FileNotFoundException e) {
            this.initEntryList();
        }
        return 0;
    }

    @Override
    public boolean validate(EditText text1, EditText text2, EditText text3, EditText text4, EditText text5, EditText text6) {
        ArrayList<EditText> textBoxes = new ArrayList<EditText>();
        textBoxes.add(text1);
        textBoxes.add(text2);
        textBoxes.add(text3);
        textBoxes.add(text4);
        textBoxes.add(text5);
        textBoxes.add(text6);

        boolean valid = true;

        for (EditText text: textBoxes) {
            int len = text.getText().length();
            if (len == 0) {
                text.setError("Invalid");
                valid = false;
            }
        }
        textBoxes.clear();
        return valid;
    }

    public Entry remove(int position) {
        return app.remove(position);
    }
}
