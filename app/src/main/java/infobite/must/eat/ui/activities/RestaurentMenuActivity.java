package infobite.must.eat.ui.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorOpenCloseTime;
import infobite.must.eat.modal.api_modal.vendor_list.VendorOpeningClosingTime;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.fragment.RestaurentAboutFragment;
import infobite.must.eat.ui.fragment.RestaurentMenuFragment;
import infobite.must.eat.ui.fragment.RestaurentReviewFragment;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseActivity;
import retrofit2.Response;

public class RestaurentMenuActivity extends BaseActivity implements View.OnClickListener {

    private Bundle bundle;
    private VendorDetailMainModal mainModal;
    private String strRestaurantId = "";
    private TextView manu_btn, about_btn, review_btn;
    private RelativeLayout manu_line, about_line, review_line;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_menu);
        init();
    }

    private void init() {
        mContext = this;
        retrofitApiClient = RetrofitService.getRetrofit();
        if (getIntent() == null)
            return;
        strRestaurantId = getIntent().getStringExtra("vendor_id");

        manu_btn = findViewById(R.id.menu_btn);
        about_btn = findViewById(R.id.about_btn);
        review_btn = findViewById(R.id.review_btn);

        manu_line = findViewById(R.id.menu_line);
        about_line = findViewById(R.id.about_line);
        review_line = findViewById(R.id.review_line);

        fragmentManager = getSupportFragmentManager();
        // If saved instance state is null then replace login fragment

        manu_btn.setOnClickListener(this);
        about_btn.setOnClickListener(this);
        review_btn.setOnClickListener(this);
        manu_line.setVisibility(View.VISIBLE);
        getRestaurantDetail();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_btn:
                RestaurentMenuFragment restaurentMenuFragment = new RestaurentMenuFragment();
                restaurentMenuFragment.setArguments(bundle);
                changeFragment(restaurentMenuFragment, Constant.RestaurentMenuFragment);
                manu_btn.setTextColor(getResources().getColor(R.color.colorRed));
                about_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                review_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                manu_line.setVisibility(View.VISIBLE);
                about_line.setVisibility(View.GONE);
                review_line.setVisibility(View.GONE);
                break;
            case R.id.about_btn:
                RestaurentAboutFragment aboutFragment = new RestaurentAboutFragment();
                aboutFragment.setArguments(bundle);
                changeFragment(aboutFragment, Constant.RestaurentAboutFragment);
                manu_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                about_btn.setTextColor(getResources().getColor(R.color.colorRed));
                review_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                manu_line.setVisibility(View.GONE);
                about_line.setVisibility(View.VISIBLE);
                review_line.setVisibility(View.GONE);
                break;
            case R.id.review_btn:
                RestaurentReviewFragment reviewFragment = new RestaurentReviewFragment();
                reviewFragment.setArguments(bundle);
                changeFragment(reviewFragment, Constant.RestaurentReviewFragment);
                manu_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                about_btn.setTextColor(getResources().getColor(R.color.colorDarkGray));
                review_btn.setTextColor(getResources().getColor(R.color.colorRed));
                manu_line.setVisibility(View.GONE);
                about_line.setVisibility(View.GONE);
                review_line.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void changeFragment(Fragment fragment, String strTag) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.resturent_menu_frame, fragment,
                        strTag).commit();
    }

    private void getRestaurantDetail() {
        if (strRestaurantId.isEmpty()) {
            Alerts.show(mContext, "Restaurant id empty");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getVendorDetailsData(new Dialog(mContext), retrofitApiClient.vendorDetail(strRestaurantId),
                        new WebResponse() {
                            @Override
                            public void onResponseSuccess(Response<?> result) {
                                mainModal = (VendorDetailMainModal) result.body();
                                if (!mainModal.getError()) {
                                    RestaurentMenuFragment restaurentMenuFragment = new RestaurentMenuFragment();
                                    bundle = new Bundle();
                                    Gson gson = new GsonBuilder().setLenient().create();
                                    String strCategory = gson.toJson(mainModal);
                                    bundle.putString("vendor_detail", strCategory);
                                    restaurentMenuFragment.setArguments(bundle);

                                    String strAddress = mainModal.getVendor().getVendorHouseNumber() + " , " +
                                            mainModal.getVendor().getVendorStreet() + " , " + mainModal.getVendor().getVendorTown() +
                                            " , " + mainModal.getVendor().getVendorCounty();
                                    ((TextView) findViewById(R.id.tv_restaurant_name)).setText(mainModal.getVendor().getVendorName());
                                    ((TextView) findViewById(R.id.tv_restaurant_address)).setText(strAddress);

                                    openCloseFunction(mainModal.getVendor().getVendorOpeningClosingTime());
                                    changeFragment(restaurentMenuFragment, Constant.RestaurentMenuFragment);
                                } else {
                                    Alerts.show(mContext, mainModal.getMessage());
                                }
                            }

                            @Override
                            public void onResponseFailed(String error) {
                                Alerts.show(mContext, error);
                            }
                        });
            } else {
                cd.show(mContext);
            }
        }
    }

    /*
     * Open close functions
     * */
    private void openCloseFunction(List<VendorOpenCloseTime> times) {
        String strDay = getCurrentDay();
        int hour = getHour(getTime());
        for (int i = 0; i < times.size(); i++) {
            if (strDay.equalsIgnoreCase(times.get(i).getWeek())) {
                int startHourB = getHour(times.get(i).getStart());
                int endHourB = getHour(times.get(i).getEnd());

                if (hour >= startHourB && hour <= endHourB) {
                    ((TextView) findViewById(R.id.tvOpenClose)).setText("OPEN");
                    ((TextView) findViewById(R.id.tvOpenClose)).setBackgroundColor(mContext.getResources().getColor(R.color.colorGreen));
                } else {
                    ((TextView) findViewById(R.id.tvOpenClose)).setText("CLOSE");
                    ((TextView) findViewById(R.id.tvOpenClose)).setBackgroundColor(mContext.getResources().getColor(R.color.colorRed));
                }
            }
        }
    }

    private int getHour(String strTime) {
        String[] strH = strTime.split(":");
        if (strH[0].isEmpty()) {
            strH[0] = "0";
        }
        return Integer.parseInt(strH[0]);
    }

    @SuppressLint("SimpleDateFormat")
    private String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        return sdf.format(d);
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }
}
