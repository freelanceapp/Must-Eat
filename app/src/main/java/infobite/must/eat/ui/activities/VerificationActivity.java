package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import infobite.must.eat.Activity.FindLocationActivity;
import infobite.must.eat.R;

/**
 * Created by Dell on 11/30/2018.
 */

public class VerificationActivity extends AppCompatActivity{
    TextView sampletext1,sampletext2,sampletext3,sampletext4,sampletext5,dontotp,dontotpreq;
    AutoCompleteTextView actvsample1,actvsample2,actvsample3,actvsample4;
    Button next;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificatoncode);




        sampletext1 = (TextView) findViewById(R.id.sampletext1);
        sampletext2 = (TextView) findViewById(R.id.sampletext2);
        sampletext3 = (TextView) findViewById(R.id.sampletext3);
        sampletext4 = (TextView) findViewById(R.id.sampletext4);
        sampletext5 = (TextView) findViewById(R.id.sampletext5);

        actvsample1 = (AutoCompleteTextView) findViewById(R.id.actvsample1);
        actvsample2 = (AutoCompleteTextView) findViewById(R.id.actvsample2);
        actvsample3 = (AutoCompleteTextView) findViewById(R.id.actvsample3);
        actvsample4 = (AutoCompleteTextView) findViewById(R.id.actvsample4);
        dontotp = (TextView) findViewById(R.id.dontotp);
        dontotpreq = (TextView) findViewById(R.id.dontotpreq);





        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VerificationActivity.this,FindLocationActivity.class);
                startActivity(intent);
            }
        });

    }
}
