package badran.fueltrack;


import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
    private ListView listView;
    private ArrayAdapter<Entry> adapter;
    private EntryList entrylist = FuelTrackApp.getApp();
    private AppController fueltrack = FuelTrackApp.getController();
    private Context context;

    /* taken from: LonelyTwitterActivity */
    public final static String EXTRA_MESSAGE = "badran.fueltrack.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Badran-FuelTrack");
        listView = (ListView) findViewById(R.id.list);
        Button addButton = (Button) findViewById(R.id.add_new_entry);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, DisplayEntryActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (MainActivity.this, EditEntryActivity.class);
                intent.putExtra(EXTRA_MESSAGE, position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        fueltrack.load(context);
        adapter = new ArrayAdapter<Entry>(this, R.layout.list_item, entrylist.getEntryList());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /* taken from:
     * http://stackoverflow.com/questions/6439085/android-how-to-create-option-menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /* taken from:
     * http://stackoverflow.com/questions/16896513/avoiding-call-to-oncreate-of-background-activity-by-pressing-back-of-finish-from
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
