package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import infobite.must.eat.R;
import infobite.must.eat.utils.BaseActivity;

public class PaymentActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        init();
    }

    private void init() {
        ((LinearLayout) findViewById(R.id.ll_back)).setOnClickListener(this);
        ((Button) findViewById(R.id.btnBuyNow)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btnBuyNow:
                startActivity(new Intent(mContext, TrackOrderActivity.class));
                break;
        }
    }
}
