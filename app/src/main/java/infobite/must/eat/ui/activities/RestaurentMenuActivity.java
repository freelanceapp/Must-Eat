package infobite.must.eat.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.ui.fragment.HomeFragment;
import infobite.must.eat.ui.fragment.RestaurentAboutFragment;
import infobite.must.eat.ui.fragment.RestaurentMenuFragment;
import infobite.must.eat.ui.fragment.RestaurentReviewFragment;

public class RestaurentMenuActivity extends AppCompatActivity implements View.OnClickListener {
    TextView manu_btn,about_btn,review_btn;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_menu);
        manu_btn = (TextView)findViewById(R.id.menu_btn);
        about_btn = (TextView)findViewById(R.id.about_btn);
        review_btn = (TextView)findViewById(R.id.review_btn);

        fragmentManager = getSupportFragmentManager();
        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.resturent_menu_frame, new RestaurentMenuFragment(),
                            Constant.RestaurentMenuFragment).commit();
        }

        manu_btn.setOnClickListener(this);
        about_btn.setOnClickListener(this);
        review_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.menu_btn :
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.resturent_menu_frame, new RestaurentMenuFragment(),
                                Constant.RestaurentMenuFragment).commit();
                manu_btn.setTextColor(getResources().getColor(R.color.colorRed));
                about_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                review_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));


                break;
            case R.id.about_btn :
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.resturent_menu_frame, new RestaurentAboutFragment(),
                                Constant.RestaurentAboutFragment).commit();

                manu_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                about_btn.setTextColor(getResources().getColor(R.color.colorRed));
                review_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));

                break;
            case R.id.review_btn :
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.resturent_menu_frame, new RestaurentReviewFragment(),
                                Constant.RestaurentReviewFragment).commit();

                manu_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                about_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                review_btn.setTextColor(getResources().getColor(R.color.colorRed));
                break;
        }
    }
}
