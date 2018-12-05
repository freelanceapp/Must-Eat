
package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import infobite.must.eat.R;


public class FindLocationActivity extends AppCompatActivity implements View.OnClickListener {
    TextView manual_location_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);
        manual_location_btn = (TextView)findViewById(R.id.manual_location_btn);
        manual_location_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.manual_location_btn :
                Intent intent = new Intent(FindLocationActivity.this,ManualLocationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
