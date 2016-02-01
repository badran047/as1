package badran.fueltrack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hasan on 2016-01-30.
 */
public class EditEntryActivity extends ActionBarActivity {
    boolean valid;
    private Context context;
    int position;
    private AppController fueltrack = FuelTrackApp.getController();
    private EditText dateText;
    private EditText stationText;
    private EditText odometerText;
    private EditText fuelUnitCostText;
    private EditText fuelGradeText;
    private EditText fuelAmountText;
    private Button saveButton;
    private Button deleteButton;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_entry);
        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home");
        Intent intent = getIntent();
        position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
        stationText = (EditText)findViewById(R.id.station);
        fuelGradeText = (EditText)findViewById(R.id.fuel_grade);
        odometerText = (EditText)findViewById(R.id.odometer);
        fuelUnitCostText = (EditText)findViewById(R.id.fuel_unit_cost);
        dateText = (EditText)findViewById(R.id.date);
        fuelAmountText = (EditText)findViewById(R.id.fuel_amount);
        saveButton = (Button)findViewById(R.id.save);
        deleteButton = (Button)findViewById(R.id.delete);


        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String dateString = stationText.getText().toString();

                /* taken from:
                 * http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
                 */
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date date = new Date();
                try {
                    date = format.parse(dateString);

                } catch (java.text.ParseException e){
                    e.printStackTrace();
                }
                valid = fueltrack.validate(stationText, dateText, fuelAmountText, fuelGradeText, odometerText, fuelUnitCostText);
                if (valid) {
                    String fuelGrade = fuelGradeText.getText().toString();
                    Double fuelAmount = Double.parseDouble(fuelAmountText.getText().toString());
                    Double odometer = Double.parseDouble(odometerText.getText().toString());
                    Double fuelUnitCost = Double.parseDouble(fuelUnitCostText.getText().toString());
                    String station = stationText.getText().toString();
                    Entry temporary = fueltrack.newEntry(date, station, odometer, fuelAmount, fuelUnitCost, fuelGrade);
                    fueltrack.add(temporary, position);
                    fueltrack.save(context);
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            /* need to fix */
            public void onClick(View v) {
                setResult(RESULT_OK);
                fueltrack.remove(position);
                //adapter.notifyDataSetChanged();
                fueltrack.save(context);

                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Entry entry = fueltrack.getAtIndex(position);
        dateText.setText(entry.getDate().toString());
        fuelAmountText.setText(entry.getFuelAmount().toString());
        fuelGradeText.setText(entry.getFuelGrade());
        stationText.setText(entry.getStation());
        odometerText.setText(entry.getOdometer().toString());
        fuelUnitCostText.setText(entry.getFuelUnitCost().toString());
    }

    @Override
    /* taken from:
     * http://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
