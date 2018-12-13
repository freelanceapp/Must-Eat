package infobite.must.eat.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.NearestRestaurentAdapter;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.vendor_list.VendorList;
import infobite.must.eat.modal.api_modal.vendor_list.VendorListMainModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseActivity;
import infobite.must.eat.utils.ConnectionDetector;
import retrofit2.Response;

public class NearRestaurantActivity extends BaseActivity implements View.OnClickListener {

    private float latitude = 0.0f, longitude = 0.0f;
    private FloatingActionMenu fam;
    private FloatingActionButton fabuser, fabcart, faboffer, fabhome;
    private RecyclerView recyclerViewVendorList;
    private List<VendorList> vendorLists = new ArrayList<>();
    private NearestRestaurentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_restaurant);
        init();
    }

    private void init() {
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();

        if (getIntent() == null) {
            latitude = AppPreference.getFloatPreference(mContext, Constant.Latitude);
            longitude = AppPreference.getFloatPreference(mContext, Constant.Longitude);
        } else {
            latitude = getIntent().getFloatExtra("latitude", 0);
            longitude = getIntent().getFloatExtra("longitude", 0);
            if (latitude == 0 || longitude == 0) {
                latitude = AppPreference.getFloatPreference(mContext, Constant.Latitude);
                longitude = AppPreference.getFloatPreference(mContext, Constant.Longitude);
            }
        }

        fabhome = findViewById(R.id.home_btn);
        faboffer = findViewById(R.id.offer_btn);
        fabcart = findViewById(R.id.cart_btn);
        fabuser = findViewById(R.id.account_btn);
        fam = findViewById(R.id.fab_menu);
        recyclerViewVendorList = findViewById(R.id.recyclerViewVendorList);
        //handling menu status (open or close)
        fam.setOnMenuToggleListener(opened -> {
            if (opened) {
                Alerts.show(mContext, "Menu is opened");
                fabhome.setVisibility(View.VISIBLE);
                faboffer.setVisibility(View.VISIBLE);
                fabcart.setVisibility(View.VISIBLE);
                fabuser.setVisibility(View.VISIBLE);
            } else {
                Alerts.show(mContext, "Menu is closed");
                fabhome.setVisibility(View.GONE);
                faboffer.setVisibility(View.GONE);
                fabcart.setVisibility(View.GONE);
                fabuser.setVisibility(View.GONE);
            }
        });

        //handling each floating action button clicked
        fabuser.setOnClickListener(this);
        fabcart.setOnClickListener(this);
        faboffer.setOnClickListener(this);
        fabhome.setOnClickListener(this);
        fam.setOnClickListener(this);

        adapter = new NearestRestaurentAdapter(vendorLists, mContext, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NearRestaurantActivity.this);
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerViewVendorList.setLayoutManager(mLayoutManager);
        recyclerViewVendorList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVendorList.setAdapter(adapter);

        restaurantListApi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn:
                Intent intent = new Intent(NearRestaurantActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.cart_btn:
                Intent intentB = new Intent(NearRestaurantActivity.this, AddtoCartActivity.class);
                startActivity(intentB);
                break;
            case R.id.offer_btn:
                Intent intentC = new Intent(NearRestaurantActivity.this, OffersActivity.class);
                startActivity(intentC);
                break;
            case R.id.account_btn:
                Intent intentD = new Intent(NearRestaurantActivity.this, AccountActivity.class);
                startActivity(intentD);
                break;
            case R.id.cardViewItem:
                int pos = Integer.parseInt(view.getTag().toString());
                VendorList vendorList = vendorLists.get(pos);
                Intent i = new Intent(mContext, RestaurentMenuActivity.class);
                i.putExtra("vendor_id", vendorList.getVendorId());
                startActivity(i);
                break;
        }
        if (fam.isOpened()) {
            fam.close(true);
        }
        //fam.close(true);
    }

    private void restaurantListApi() {
        if (latitude == 0) {
            Alerts.show(mContext, "Location empty");
        } else if (longitude == 0) {
            Alerts.show(mContext, "Location empty");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getVendorList(new Dialog(mContext), retrofitApiClient.vendorList(latitude, longitude, "500"),
                        new WebResponse() {
                            @Override
                            public void onResponseSuccess(Response<?> result) {
                                VendorListMainModal listMainModal = (VendorListMainModal) result.body();
                                vendorLists.clear();
                                assert listMainModal != null;
                                if (!listMainModal.getError()) {
                                    vendorLists.addAll(listMainModal.getVendor());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Alerts.show(mContext, listMainModal.getMessage());
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
}
