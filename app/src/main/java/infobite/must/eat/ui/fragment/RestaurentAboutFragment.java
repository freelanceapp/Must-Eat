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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.OpenDayAdapter;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorOpenCloseTime;
import infobite.must.eat.modal.default_modal.DayModel;

public class RestaurentAboutFragment extends Fragment {

    private View view;
    private RecyclerView day_list;
    private OpenDayAdapter openDayAdapter;
    private List<VendorOpenCloseTime> dayModelArrayList = new ArrayList<>();

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
        day_list = (RecyclerView) view.findViewById(R.id.day_list);
        if (getArguments() == null)
            return;
        Bundle bundle = getArguments();
        String strVendorDetail = bundle.getString("vendor_detail");
        Gson gson = new Gson();
        VendorDetailMainModal mainModal = gson.fromJson(strVendorDetail, VendorDetailMainModal.class);
        dayModelArrayList.addAll(mainModal.getVendor().getVendorOpeningClosingTime());

        String strAddress = mainModal.getVendor().getVendorHouseNumber() + " " + mainModal.getVendor().getVendorStreet();
        ((TextView) view.findViewById(R.id.tvVendorAddress)).setText("Address:" + " " + strAddress);
        //((TextView) view.findViewById(R.id.tvVendorContact)).setText(strAddress);
        ((TextView) view.findViewById(R.id.tvVendorWebsite)).setText("Website:" + " " + mainModal.getVendor().getVendorWebsite());
        setDayRecyclerview();
    }

    private void setDayRecyclerview() {
        openDayAdapter = new OpenDayAdapter(dayModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        day_list.setLayoutManager(mLayoutManager);
        day_list.setItemAnimator(new DefaultItemAnimator());
        day_list.setAdapter(openDayAdapter);
        openDayAdapter.notifyDataSetChanged();
    }
}
