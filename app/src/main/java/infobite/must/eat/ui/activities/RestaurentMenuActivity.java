package infobite.must.eat.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.ui.fragment.RestaurentAboutFragment;
import infobite.must.eat.ui.fragment.RestaurentMenuFragment;
import infobite.must.eat.ui.fragment.RestaurentReviewFragment;

public class RestaurentMenuActivity extends AppCompatActivity implements View.OnClickListener {
    TextView manu_btn, about_btn, review_btn;
    RelativeLayout manu_line, about_line, review_line;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_menu);
        manu_btn = findViewById(R.id.menu_btn);
        about_btn = findViewById(R.id.about_btn);
        review_btn = findViewById(R.id.review_btn);

        manu_line = findViewById(R.id.menu_line);
        about_line = findViewById(R.id.about_line);
        review_line = findViewById(R.id.review_line);

        fragmentManager = getSupportFragmentManager();
        // If saved instance state is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.resturent_menu_frame, new RestaurentMenuFragment(),
                            Constant.RestaurentMenuFragment).commit();
        }

        manu_btn.setOnClickListener(this);
        about_btn.setOnClickListener(this);
        review_btn.setOnClickListener(this);
        manu_line.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_btn:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.resturent_menu_frame, new RestaurentMenuFragment(),
                                Constant.RestaurentMenuFragment).commit();
                manu_btn.setTextColor(getResources().getColor(R.color.colorRed));
                about_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                review_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                manu_line.setVisibility(View.VISIBLE);
                about_line.setVisibility(View.GONE);
                review_line.setVisibility(View.GONE);

                break;
            case R.id.about_btn:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.resturent_menu_frame, new RestaurentAboutFragment(),
                                Constant.RestaurentAboutFragment).commit();

                manu_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                about_btn.setTextColor(getResources().getColor(R.color.colorRed));
                review_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                manu_line.setVisibility(View.GONE);
                about_line.setVisibility(View.VISIBLE);
                review_line.setVisibility(View.GONE);
                break;
            case R.id.review_btn:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.resturent_menu_frame, new RestaurentReviewFragment(),
                                Constant.RestaurentReviewFragment).commit();

                manu_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                about_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                review_btn.setTextColor(getResources().getColor(R.color.colorRed));

                manu_line.setVisibility(View.GONE);
                about_line.setVisibility(View.GONE);
                review_line.setVisibility(View.VISIBLE);

                break;
        }
    }
}
