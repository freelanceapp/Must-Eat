package infobite.must.eat.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import infobite.must.eat.R;
import infobite.must.eat.utils.BaseActivity;

public class TrackOrderActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_track_order);
        //setContentView(R.layout.fragment_add_to_cart);
        setContentView(R.layout.activity_gift_cards);

        //init();
    }

    private void init() {
        ((LinearLayout) findViewById(R.id.ll_back)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
