package infobite.must.eat.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.FavRestaurentListAdapter;
import infobite.must.eat.adapter.RecyclerViewDataAdapter;
import infobite.must.eat.adapter.SectionListDataAdapter;
import infobite.must.eat.modal.HomeRestaurentModel;
import infobite.must.eat.modal.SectionDataModel;


public class HomeFragment extends Fragment {
    public View view;
    RecyclerView recommendation_list,most_list,fav_list;
    ArrayList<HomeRestaurentModel> homeRestaurentModelArrayList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recommendation_list = (RecyclerView)view.findViewById(R.id.recommendation_list);
        most_list = (RecyclerView)view.findViewById(R.id.most_list);
        fav_list = (RecyclerView)view.findViewById(R.id.fav_list);
        //tooltext.setText("Home");

        for (int i = 0 ; i < 16 ; i++) {
            HomeRestaurentModel homeRestaurentModel = new HomeRestaurentModel();
            homeRestaurentModel.setHr_img1(R.drawable.bg_food);
            homeRestaurentModel.setHr_namne("Apna");
            homeRestaurentModel.setHr_address("Vijay nagar");
            homeRestaurentModelArrayList.add(homeRestaurentModel);
        }

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(getActivity(), homeRestaurentModelArrayList);
        recommendation_list.setHasFixedSize(true);
        recommendation_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recommendation_list.setAdapter(itemListDataAdapter);

        most_list.setHasFixedSize(true);
        most_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        most_list.setAdapter(itemListDataAdapter);

        FavRestaurentListAdapter favRestaurentListAdapter = new FavRestaurentListAdapter(getActivity(), homeRestaurentModelArrayList);
        fav_list.setHasFixedSize(true);
        fav_list.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));
        fav_list.setAdapter(favRestaurentListAdapter);






        return view;
    }



}
