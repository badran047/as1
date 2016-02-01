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
public class DisplayEntryActivity extends ActionBarActivity implements Shared {
    boolean valid;
    private Context context;
    private EditText dateText;
    private EditText stationText;
    private EditText odometerText;
    private EditText fuelUnitCostText;
    private EditText fuelGradeText;
    private EditText fuelAmountText;
    private AppController fueltrack = FuelTrackApp.getController();

    @Override
    public void onStartData() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();
    }

    @Override
    public void onCreateSetup() {
        setContentView(R.layout.activity_display_entry);
        context = FuelTrackApp.getContext();
        stationText = (EditText) findViewById(R.id.station);
        fuelGradeText = (EditText) findViewById(R.id.fuel_grade);
        odometerText = (EditText) findViewById(R.id.odometer);
        fuelUnitCostText = (EditText) findViewById(R.id.fuel_unit_cost);
        dateText = (EditText) findViewById(R.id.date);
        fuelAmountText = (EditText) findViewById(R.id.fuel_amount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home");
    }

    @Override
    public void onCreateListeners() {
        Button saveButton = (Button)findViewById(R.id.add_edit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String dateString = dateText.getText().toString();
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date date = new Date();

                /* taken from:
                 * http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
                 */
                try {
                    date = format.parse(dateString);

                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                valid = fueltrack.validate(stationText, dateText, fuelAmountText, fuelGradeText, odometerText, fuelUnitCostText);
                if (valid) {
                    String fuelGrade = fuelGradeText.getText().toString();
                    Double fuelAmount = Double.parseDouble(fuelAmountText.getText().toString());
                    Double odometer = Double.parseDouble(odometerText.getText().toString());
                    Double fuelUnitCost = Double.parseDouble(fuelUnitCostText.getText().toString());
                    String station = stationText.getText().toString();
                    fueltrack.add(date, station, odometer, fuelAmount, fuelUnitCost, fuelGrade);
                    fueltrack.save(context);
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });
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
