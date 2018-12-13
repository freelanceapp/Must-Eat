package infobite.must.eat.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.OffersAdapter;
import infobite.must.eat.adapter.ReviewAdapter;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.modal.api_modal.offer_response.Coupon;
import infobite.must.eat.modal.api_modal.offer_response.OfferMainModal;
import infobite.must.eat.modal.default_modal.OffersModel;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.fragment.VerificationFragment;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseActivity;
import infobite.must.eat.utils.ConnectionDetector;
import infobite.must.eat.utils.EmailChecker;
import retrofit2.Response;

public class OffersActivity extends BaseActivity {
    LinearLayout ll_back;
    RecyclerView offers_list;
    OffersAdapter offersAdapter;
    ArrayList<Coupon> offersModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_cards);
        mContext = OffersActivity.this;
        cd = new ConnectionDetector(mContext);
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        ll_back = (LinearLayout)findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        offers_list = (RecyclerView)findViewById(R.id.offers_list);


        loginApi();

        offersAdapter = new OffersAdapter(offersModelArrayList, OffersActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(OffersActivity.this);
        offers_list.setLayoutManager(mLayoutManager);
        offers_list.setItemAnimator(new DefaultItemAnimator());
        offers_list.setAdapter(offersAdapter);


    }

    private void loginApi() {
            if (cd.isNetworkAvailable()) {

                RetrofitService.getOfferList(new Dialog(mContext), retrofitApiClient.getOfferList(), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        OfferMainModal offerMainModal = (OfferMainModal) result.body();
                        assert offerMainModal != null;
                        offersModelArrayList.clear();
                        if (!offerMainModal.getError()) {
                            Alerts.show(mContext, offerMainModal.getMessage());
                            offersModelArrayList.addAll(offerMainModal.getCoupon());

                            offersAdapter.notifyDataSetChanged();

                        } else {
                            Alerts.show(mContext, offerMainModal.getMessage());
                            /*if (offerMainModal.getMessage().equals("User is Not Verified")) {
                               // startFragment(Constant.Verification_Fragment, new VerificationFragment(), loginModal.getUser().getPhone());
                                //activity.finish();
                            }*/
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
