package infobite.must.eat.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.ui.fragment.LoginFragment;
import infobite.must.eat.utils.BaseActivity;

public class PlaceOrderActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

    }

    @Override
    public void onClick(View v) {
        
    }
}
