package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import infobite.must.eat.R;


public class ManualLocationActivity extends AppCompatActivity {

    String[] fruits = {"Plasia", "TI", "LIG", "Vijay Nagar", "Bhawarkua", "Vishnupuri", "MR 9", "TIT Group", "Pardesipura", "Palnipura"};
    ListView area_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_location);
        //Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruits);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

        area_list = (ListView)findViewById(R.id.area_list);
        area_list.setAdapter(adapter);

        area_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ManualLocationActivity.this,NearRestaurantActivity.class);
                startActivity(intent);
            }
        });
    }
}
