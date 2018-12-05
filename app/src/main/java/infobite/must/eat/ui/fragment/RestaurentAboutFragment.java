package infobite.must.eat.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.HistoryShowAdapter;
import infobite.must.eat.adapter.OpenDayAdapter;
import infobite.must.eat.modal.DayModel;

public class RestaurentAboutFragment extends Fragment {
    RecyclerView day_list;
    OpenDayAdapter openDayAdapter;
    ArrayList<DayModel> dayModelArrayList = new ArrayList<>();

    public RestaurentAboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurent_about, container, false);
        day_list = (RecyclerView)view.findViewById(R.id.day_list);

        for (int i = 0 ; i < 7 ; i++) {
            DayModel dayModel = new DayModel();
            dayModel.setDay("Monday");
            dayModel.setTime("10:00 - 20:00");
            dayModelArrayList.add(dayModel);
        }

        openDayAdapter = new OpenDayAdapter(dayModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        day_list.setLayoutManager(mLayoutManager);
        day_list.setItemAnimator(new DefaultItemAnimator());
        day_list.setAdapter(openDayAdapter);

        return view;
    }


}
