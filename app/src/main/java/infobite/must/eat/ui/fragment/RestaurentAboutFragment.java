package infobite.must.eat.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import infobite.must.eat.R;
import infobite.must.eat.adapter.OpenDayAdapter;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorOpenCloseTime;
import infobite.must.eat.modal.default_modal.DayModel;
import infobite.must.eat.utils.BaseFragment;

public class RestaurentAboutFragment extends BaseFragment implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private View view;
    private RecyclerView day_list;
    private OpenDayAdapter openDayAdapter;
    private List<VendorOpenCloseTime> dayModelArrayList = new ArrayList<>();
    private double lat1,long1;
    VendorDetailMainModal mainModal;
    public RestaurentAboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurent_about, container, false);
        init();
        return view;
    }

    private void init() {
        mContext = getActivity();
        activity = getActivity();
        day_list = (RecyclerView) view.findViewById(R.id.day_list);
        if (getArguments() == null)
            return;
        Bundle bundle = getArguments();
        String strVendorDetail = bundle.getString("vendor_detail");
        Gson gson = new Gson();
        mainModal = gson.fromJson(strVendorDetail, VendorDetailMainModal.class);
        dayModelArrayList.addAll(mainModal.getVendor().getVendorOpeningClosingTime());

        lat1 = Double.parseDouble(mainModal.getVendor().getVendorLat());
        long1 = Double.parseDouble(mainModal.getVendor().getVendorLong());
        String strAddress = mainModal.getVendor().getVendorHouseNumber() + " " + mainModal.getVendor().getVendorStreet();
        ((TextView) view.findViewById(R.id.tvVendorAddress)).setText("Address:" + " " + strAddress);
        //((TextView) view.findViewById(R.id.tvVendorContact)).setText(strAddress);
        ((TextView) view.findViewById(R.id.tvVendorWebsite)).setText("Website:" + " " + mainModal.getVendor().getVendorWebsite());
        setDayRecyclerview();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

       /* mapFragment = (SupportMapFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);*/

       /* mapFragment.getMapAsync(googleMap -> {
            googleMap.clear();
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat1,long1))
                    .title("" + mainModal.getVendor().getVendorDisplayName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat1,long1), 13));
        });*/

    }

    private void setDayRecyclerview() {
        openDayAdapter = new OpenDayAdapter(dayModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        day_list.setLayoutManager(mLayoutManager);
        day_list.setItemAnimator(new DefaultItemAnimator());
        day_list.setAdapter(openDayAdapter);
        openDayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat1,long1);
        mMap.addMarker(new MarkerOptions().position(sydney).title(mainModal.getVendor().getVendorDisplayName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat1,long1), 10));


    }
}
