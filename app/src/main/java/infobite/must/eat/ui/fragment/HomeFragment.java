package infobite.must.eat.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.FavRestaurentListAdapter;
import infobite.must.eat.adapter.RestaurentListAdapter;
import infobite.must.eat.adapter.SectionListDataAdapter;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.vendor_list.VendorList;
import infobite.must.eat.modal.api_modal.vendor_list.VendorListMainModal;
import infobite.must.eat.modal.default_modal.HomeRestaurentModel;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.activities.RestaurantsActivity;
import infobite.must.eat.ui.activities.RestaurentMenuActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.ConnectionDetector;
import retrofit2.Response;


public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private float latitude = 0.0f, longitude = 0.0f;
    private RecyclerView recommendation_list, most_list, fav_list;
    private TextView popular_btn, recommendation_btn, fav_btn;
    private List<VendorList> allVendorLists = new ArrayList<>();
    private List<VendorList> recommendVendorLists = new ArrayList<>();
    private List<VendorList> popularVendorLists = new ArrayList<>();
    private List<VendorList> favouritVendorLists = new ArrayList<>();
    private ArrayList<HomeRestaurentModel> homeRestaurentModelArrayList = new ArrayList<>();
    private RestaurentListAdapter recommendAdapter, popularAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    private void init() {
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        latitude = AppPreference.getFloatPreference(mContext, Constant.Latitude);
        longitude = AppPreference.getFloatPreference(mContext, Constant.Longitude);

        recommendation_list = view.findViewById(R.id.recommendation_list);
        most_list = view.findViewById(R.id.most_list);
        fav_list = view.findViewById(R.id.fav_list);
        popular_btn = view.findViewById(R.id.popular_btn);
        recommendation_btn = view.findViewById(R.id.recommendation_btn);
        fav_btn = view.findViewById(R.id.fav_btn);
        //tooltext.setText("Home");

        for (int i = 0; i < 16; i++) {
            HomeRestaurentModel homeRestaurentModel = new HomeRestaurentModel();
            homeRestaurentModel.setHr_img1(R.drawable.bg_food);
            homeRestaurentModel.setHr_namne("Apna");
            homeRestaurentModel.setHr_address("Vijay nagar");
            homeRestaurentModelArrayList.add(homeRestaurentModel);
        }

        recommendation_list.setHasFixedSize(true);
        recommendation_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        most_list.setHasFixedSize(true);
        most_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        FavRestaurentListAdapter favRestaurentListAdapter = new FavRestaurentListAdapter(getActivity(), homeRestaurentModelArrayList);
        fav_list.setHasFixedSize(true);
        fav_list.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));
        fav_list.setAdapter(favRestaurentListAdapter);

        popular_btn.setOnClickListener(this);
        fav_btn.setOnClickListener(this);
        recommendation_btn.setOnClickListener(this);

        restaurantListApi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommendation_btn:
            case R.id.popular_btn:
            case R.id.fav_btn:
                VendorListMainModal listMainModal = new VendorListMainModal();
                listMainModal.setVendor(allVendorLists);
                Gson gson = new GsonBuilder().setLenient().create();
                String data = gson.toJson(listMainModal);

                Intent intent = new Intent(getActivity(), RestaurantsActivity.class);
                intent.putExtra("all_vendor", data);
                startActivity(intent);
                break;
            case R.id.cardViewItem:
                int pos = Integer.parseInt(view.getTag().toString());
                VendorList vendorList = allVendorLists.get(pos);
                Intent i = new Intent(mContext, RestaurentMenuActivity.class);
                i.putExtra("vendor_id", vendorList.getVendorId());
                startActivity(i);
                break;
        }
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
                                allVendorLists.clear();
                                recommendVendorLists.clear();
                                popularVendorLists.clear();
                                favouritVendorLists.clear();
                                assert listMainModal != null;
                                if (!listMainModal.getError()) {
                                    allVendorLists.addAll(listMainModal.getVendor());
                                    separateVendor();
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

    private void separateVendor() {
        popularVendorLists = new ArrayList<>();
        recommendVendorLists = new ArrayList<>();
        for (int i = 0; i < allVendorLists.size(); i++) {
            if (allVendorLists.get(i).getVendorMostPopular()!=null)
                return;
            if (allVendorLists.get(i).getVendorMostPopular().equalsIgnoreCase("Yes")) {
                VendorList vendorList = allVendorLists.get(i);
                popularVendorLists.add(vendorList);
            }

            if (allVendorLists.get(i).getVendorRecommandation().equalsIgnoreCase("Yes")) {
                VendorList vendorList = allVendorLists.get(i);
                recommendVendorLists.add(vendorList);
            }
        }

        recommendAdapter = new RestaurentListAdapter(recommendVendorLists, mContext, this);
        popularAdapter = new RestaurentListAdapter(popularVendorLists, mContext, this);
        recommendation_list.setAdapter(recommendAdapter);
        most_list.setAdapter(popularAdapter);
        recommendAdapter.notifyDataSetChanged();
        popularAdapter.notifyDataSetChanged();
    }

}
