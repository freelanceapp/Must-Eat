package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.ui.fragment.LoginFragment;
import infobite.must.eat.utils.BaseActivity;

public class PlaceOrderActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        init();
    }

    private void init() {
        ((LinearLayout) findViewById(R.id.ll_back)).setOnClickListener(this);
        ((Button) findViewById(R.id.btnPlaceOrder)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btnPlaceOrder:
                startActivity(new Intent(mContext, PaymentActivity.class));
                break;
        }
    }
}
