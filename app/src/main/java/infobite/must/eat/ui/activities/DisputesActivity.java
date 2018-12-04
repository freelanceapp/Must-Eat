package infobite.must.eat.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import infobite.must.eat.R;

/**
 * Created by Dell on 12/1/2018.
 */

public class DisputesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    LinearLayout spinnerdata;
    TextView toptext,orderid,vendorname,texthelp,textshown;
    EditText edit_id,edit_vendorname;
    ImageView icon;
    Spinner spinner;
    String[] query = {"Select your Query","OTP not getting","Searching near location restroent","Restorent not found"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disputes);

        spinnerdata = (LinearLayout) findViewById(R.id.spinnerdata);

        spinner = (Spinner) findViewById(R.id.spinner);

        icon = (ImageView) findViewById(R.id.icon);

        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_vendorname = (EditText) findViewById(R.id.edit_vendorname);

        toptext = (TextView) findViewById(R.id.toptext);
        orderid = (TextView) findViewById(R.id.orderid);
        vendorname = (TextView) findViewById(R.id.vendorname);
        texthelp = (TextView) findViewById(R.id.texthelp);
        textshown = (TextView) findViewById(R.id.textshown);


        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) DisputesActivity.this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,query);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), query[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {


    }

}
